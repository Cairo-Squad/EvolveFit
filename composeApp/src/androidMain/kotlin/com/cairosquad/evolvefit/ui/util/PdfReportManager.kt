package com.cairosquad.evolvefit.ui.util

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.scale
import com.cairosquad.evolvefit.EvolveFitApp
import com.cairosquad.evolvefit.viewmodel.report.ReportScreenState
import com.cairosquad.evolvefit.viewmodel.utils.formatDateDayMonth
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
actual object PdfReportManager {

    actual fun generateAndShareReport(
        report: ReportScreenState.ReportUiState,
        name: String,
        startDate: String,
        endDate: String
    ) {
        val start = formatDateDayMonth(startDate)
        val end = formatDateDayMonth(endDate)
        val context = EvolveFitApp.appContext
        val logoBytes = loadLogoBytes(context, "logo")
        val pdfPath = createPDF(context, report, name, start, end, logoBytes)
        val file = File(pdfPath)

        val uri: Uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        val chooser = Intent.createChooser(shareIntent, "Share Report").apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(chooser)

    }

    actual fun createPDF(
        platformContext: Any?,
        report: ReportScreenState.ReportUiState,
        name: String,
        startDate: String,
        endDate: String,
        logoBytes: ByteArray
    ): String {

        val context = platformContext as? Context
            ?: throw IllegalArgumentException("Android createPDF requires an Android Context as platformContext")

        val logoBitmap = if (logoBytes.isNotEmpty()) BitmapFactory.decodeByteArray(logoBytes, 0, logoBytes.size) else null

        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 points
        var page = document.startPage(pageInfo)
        var canvas = page.canvas

        val paint = Paint().apply { textSize = 14f }
        val boldPaint = Paint().apply { textSize = 16f; isFakeBoldText = true }

        var y = 40f

        canvas.drawText("Name: $name", 40f, y, boldPaint); y += 22f
        canvas.drawText("Start date: $startDate", 40f, y, paint); y += 20f
        canvas.drawText("End date: $endDate", 40f, y, paint)

        logoBitmap?.let {
            val scaledLogo = it.scale(60, 60, true)
            canvas.drawBitmap(scaledLogo, (pageInfo.pageWidth - 100).toFloat(), 20f, null)
        }

        y += 30f
        canvas.drawLine(40f, y, (pageInfo.pageWidth - 45).toFloat(), y, paint)
        y += 50f

        canvas.drawText("Weekly report for the duration of $startDate to $endDate.", 40f, y, paint); y += 28f

        canvas.drawText("Total time spent training: ${report.timeSpent}", 40f, y, paint); y += 20f
        canvas.drawText("Total workouts made: ${report.totalWorkouts}", 40f, y, paint); y += 20f
        canvas.drawText("Total calories taken: ${report.takenCaloriesInKcal} kcal", 40f, y, paint); y += 20f
        canvas.drawText("Expected calories: ${report.expectedCalories} kcal", 40f, y, paint); y += 20f
        canvas.drawText("Total water drank: ${report.waterConsumed} liters", 40f, y, paint); y += 30f

        canvas.drawText("Workouts per week:", 40f, y, boldPaint); y += 24f
        val workoutsCounts = report.workoutPerWeek.workoutsCount
        val workoutDays =  report.workoutPerWeek.day

        val timeSecondsList = report.timeSpentPerWeek.timeInSeconds
        val timeDays = report.timeSpentPerWeek.day

        val itemCount = maxOf(
            workoutsCounts.size,
            workoutDays.size,
            timeSecondsList.size,
            timeDays.size
        )

        for (i in 0 until itemCount) {
            val dayName = workoutDays.getOrNull(i)?.toString() ?: timeDays.getOrNull(i)?.toString() ?: "Day ${i + 1}"
            val count = workoutsCounts.getOrNull(i) ?: 0
            val seconds = timeSecondsList.getOrNull(i) ?: 0L
            val minutes = seconds / 60
            val line = "- ${dayName.capitalize(Locale.ROOT)}: Workouts = $count, Time spent = $minutes minutes"
            canvas.drawText(line, 60f, y, paint)
            y += 18f

            // page break
            if (y > pageInfo.pageHeight - 80) {
                document.finishPage(page)
                page = document.startPage(pageInfo)
                canvas = page.canvas
                y = 40f
            }
        }
        y += 20f

        canvas.drawText("Most trained muscles:", 40f, y, boldPaint); y += 26f
        val muscles = report.mostTrainedMuscles.muscle
        val percents = report.mostTrainedMuscles.percentage
        val muscleCount = maxOf(muscles.size, percents.size)
        for (i in 0 until muscleCount) {
            val muscleName = muscles.getOrNull(i)?.toString() ?: "Muscle ${i + 1}"
            val pct = (percents.getOrNull(i) ?: 0f) * 100f
            val pctInt = pct.toInt()
            canvas.drawText("- ${muscleName.capitalize(Locale.ROOT)}: $pctInt%", 60f, y, paint)
            y += 18f
            if (y > pageInfo.pageHeight - 80) {
                document.finishPage(page)
                page = document.startPage(pageInfo)
                canvas = page.canvas
                y = 40f
            }
        }

        document.finishPage(page)

        val file = File(context.cacheDir, "WeeklyReport.pdf")
        FileOutputStream(file).use { out -> document.writeTo(out) }
        document.close()

        return file.absolutePath
    }

    actual fun loadLogoBytes(platformContext: Any?, resourceName: String): ByteArray {
        val context = platformContext as? Context
            ?: throw IllegalArgumentException("Android: platformContext (Context) required")

        val resId = context.resources.getIdentifier(resourceName, "drawable", context.packageName)
        if (resId == 0) throw IllegalArgumentException("Drawable '$resourceName' not found")

        val drawable = AppCompatResources.getDrawable(context, resId)
            ?: throw IllegalStateException("Unable to load drawable $resourceName")

        val width = if (drawable.intrinsicWidth > 0) drawable.intrinsicWidth else 256
        val height = if (drawable.intrinsicHeight > 0) drawable.intrinsicHeight else 256
        val bitmap = drawable.toBitmap(width, height, android.graphics.Bitmap.Config.ARGB_8888)

        val baos = ByteArrayOutputStream()
        bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }
}