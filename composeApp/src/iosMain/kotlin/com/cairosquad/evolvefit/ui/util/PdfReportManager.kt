package com.cairosquad.evolvefit.ui.util

import com.cairosquad.evolvefit.viewmodel.report.ReportScreenState
import com.cairosquad.evolvefit.viewmodel.utils.formateDateDayMonth
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.CoreGraphics.CGPointMake
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSURL
import platform.Foundation.create
import platform.Foundation.getBytes
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.UIGraphicsBeginPDFContextToFile
import platform.UIKit.UIGraphicsBeginPDFPageWithInfo
import platform.UIKit.UIGraphicsEndPDFContext
import platform.UIKit.UIImage
import platform.UIKit.UIImagePNGRepresentation
import platform.UIKit.UIViewController
import platform.UIKit.drawAtPoint
import kotlin.math.max

actual object PdfReportManager {
    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    actual fun createPDF(
        platformContext: Any?,
        report: ReportScreenState.ReportUiState,
        name: String,
        startDate: String,
        endDate: String,
        logoBytes: ByteArray
    ): String {
        val fileName = "WeeklyReport.pdf"
        val tmpDir = NSTemporaryDirectory() ?: "/tmp/"
        val filePath = tmpDir + fileName

        val pageWidth = 595.0
        val pageHeight = 842.0
        val pageRect = CGRectMake(0.0, 0.0, pageWidth, pageHeight)

        val logoImage: UIImage? = if (logoBytes.isNotEmpty()) {
            logoBytes.usePinned { pinned ->
                val nsData =
                    NSData.create(bytes = pinned.addressOf(0), length = logoBytes.size.toULong())
                nsData?.let { NSData -> UIImage.imageWithData(NSData) }
            }
        } else {
            null
        }

        UIGraphicsBeginPDFContextToFile(filePath, pageRect, null)
        UIGraphicsBeginPDFPageWithInfo(pageRect, null)

        var y = 40.0

        (("Name: $name") as NSString).drawAtPoint(CGPointMake(40.0, y), withAttributes = null)
        y += 22.0
        (("Start date: $startDate") as NSString).drawAtPoint(
            CGPointMake(40.0, y),
            withAttributes = null
        )
        y += 20.0
        (("End date: $endDate") as NSString).drawAtPoint(
            CGPointMake(40.0, y),
            withAttributes = null
        )

        logoImage?.let {
            val logoW = 60.0
            val logoH = 60.0
            it.drawInRect(CGRectMake(pageWidth - 95.0, 30.0, logoW, logoH))
        }

        y += 30.0

        ("——————————————————————————————————————————" as NSString).drawAtPoint(
            CGPointMake(40.0, y),
            withAttributes = null
        )
        y += 50.0

        ("Weekly report for the duration of $startDate to $endDate." as NSString)
            .drawAtPoint(CGPointMake(40.0, y), withAttributes = null)
        y += 28.0

        ("Total time spent training: ${report.timeSpent}" as NSString)
            .drawAtPoint(CGPointMake(40.0, y), withAttributes = null)
        y += 20.0
        ("Total workouts made: ${report.totalWorkouts}" as NSString)
            .drawAtPoint(CGPointMake(40.0, y), withAttributes = null)
        y += 20.0
        ("Total calories taken: ${report.takenCaloriesInKcal} kcal" as NSString)
            .drawAtPoint(CGPointMake(40.0, y), withAttributes = null)
        y += 20.0
        ("Expected calories: ${report.expectedCalories} kcal" as NSString)
            .drawAtPoint(CGPointMake(40.0, y), withAttributes = null)
        y += 20.0
        ("Total water drank: ${report.waterConsumed} liters" as NSString)
            .drawAtPoint(CGPointMake(40.0, y), withAttributes = null)
        y += 30.0

        if (report.workoutPerWeek.day.isNotEmpty()) {
            ("Workouts per week:" as NSString).drawAtPoint(
                CGPointMake(40.0, y),
                withAttributes = null
            )
            y += 24.0

            val workoutsCounts = report.workoutPerWeek.workoutsCount
            val workoutDays = report.workoutPerWeek.day
            val timeSecondsList = report.timeSpentPerWeek.timeInSeconds
            val timeDays = report.timeSpentPerWeek.day

            val itemCount = max(
                workoutsCounts.size,
                max(workoutDays.size, max(timeSecondsList.size, timeDays.size))
            )
            for (i in 0 until itemCount) {
                val dayName =
                    workoutDays.getOrNull(i)?.toString() ?: timeDays.getOrNull(i)?.toString()
                    ?: "Day ${i + 1}"
                val count = workoutsCounts.getOrNull(i) ?: 0
                val seconds = timeSecondsList.getOrNull(i) ?: 0L
                val minutes = seconds / 60
                ("- ${dayName}: Workouts = $count, Time spent = $minutes minutes" as NSString)
                    .drawAtPoint(CGPointMake(60.0, y), withAttributes = null)
                y += 18.0

                if (y > pageHeight - 80.0) {
                    UIGraphicsBeginPDFPageWithInfo(pageRect, null)
                    y = 40.0
                }
            }
            y += 20.0

            ("Most trained muscles:" as NSString).drawAtPoint(
                CGPointMake(40.0, y),
                withAttributes = null
            )
            y += 24.0
            val muscles = report.mostTrainedMuscles.muscle
            val percents = report.mostTrainedMuscles.percentage
            val muscleCount = max(muscles.size, percents.size)
            for (i in 0 until muscleCount) {
                val muscleName = muscles.getOrNull(i)?.toString() ?: "Muscle ${i + 1}"
                val pctInt = ((percents.getOrNull(i) ?: 0f) * 100).toInt()
                ("- ${muscleName}: ${pctInt}%" as NSString).drawAtPoint(
                    CGPointMake(60.0, y),
                    withAttributes = null
                )
                y += 18.0
                if (y > pageHeight - 80.0) {
                    UIGraphicsBeginPDFPageWithInfo(pageRect, null)
                    y = 40.0
                }
            }
        }
        UIGraphicsEndPDFContext()

        return filePath
    }

    actual fun generateAndShareReport(
        report: ReportScreenState.ReportUiState,
        name: String,
        startDate: String,
        endDate: String
    ) {
        val start = formateDateDayMonth(startDate)
        val end = formateDateDayMonth(endDate)
        val logoBytes = loadLogoBytes(null, "logo")
        val pdfPath = createPDF(null, report, name, start, end, logoBytes)

        val url = NSURL.fileURLWithPath(pdfPath)

        val activityVC = UIActivityViewController(
            activityItems = listOf(url),
            applicationActivities = null
        )

        val rootVC: UIViewController? =
            UIApplication.sharedApplication.keyWindow?.rootViewController

        rootVC?.presentViewController(activityVC, animated = true, completion = null)
    }

    @OptIn(ExperimentalForeignApi::class)
    actual fun loadLogoBytes(platformContext: Any?, resourceName: String): ByteArray {
        val uiImage = UIImage.imageNamed(resourceName)

        return if (uiImage != null) {
            val nsData: NSData = UIImagePNGRepresentation(uiImage)
                ?: return ByteArray(0)

            val length = nsData.length.toInt()
            val result = ByteArray(length)

            result.usePinned { pinned ->
                nsData.getBytes(pinned.addressOf(0), nsData.length)
            }

            result
        } else {
            ByteArray(0)
        }
    }
}