package com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.chartComponent

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path

object PathFunctions {
    fun buildPathFromPoints(points: List<Offset>): Path = Path().apply {
        if (points.isEmpty()) return@apply
        moveTo(points.first().x, points.first().y)
        points.drop(1).forEach { lineTo(it.x, it.y) }
    }

    fun buildAreaPath(linePath: Path, anchors: List<Offset>, canvasHeight: Float): Path =
        Path().apply {
            addPath(linePath)
            if (anchors.isNotEmpty()) {
                lineTo(anchors.last().x, canvasHeight)
                lineTo(anchors.first().x, canvasHeight)
            }
            close()
        }

    /** Catmull-Rom sampler that returns a dense list (curve passes through anchors) */
    fun catmullRomSpline(points: List<Offset>, samplesPerSegment: Int = 20): List<Offset> {
        if (points.size < 2) return points
        val result = mutableListOf<Offset>()
        fun p(i: Int) = points[i.coerceIn(0, points.lastIndex)]

        for (i in 0 until points.lastIndex) {
            val p0 = p(i - 1)
            val p1 = p(i)
            val p2 = p(i + 1)
            val p3 = p(i + 2)

            for (j in 0..samplesPerSegment) {
                val t = j.toFloat() / samplesPerSegment
                val t2 = t * t
                val t3 = t2 * t

                val x = 0.5f * ((2f * p1.x) +
                        (-p0.x + p2.x) * t +
                        (2f * p0.x - 5f * p1.x + 4f * p2.x - p3.x) * t2 +
                        (-p0.x + 3f * p1.x - 3f * p2.x + p3.x) * t3)

                val y = 0.5f * ((2f * p1.y) +
                        (-p0.y + p2.y) * t +
                        (2f * p0.y - 5f * p1.y + 4f * p2.y - p3.y) * t2 +
                        (-p0.y + 3f * p1.y - 3f * p2.y + p3.y) * t3)

                result.add(Offset(x, y))
            }
        }
        result.add(points.last())
        return result
    }

}