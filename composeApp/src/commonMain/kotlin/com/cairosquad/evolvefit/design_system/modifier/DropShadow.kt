//package com.cairosquad.evolvefit.design_system.modifier
//
//import androidx.compose.runtime.Stable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.drawBehind
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Paint
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.graphics.drawOutline
//import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import android.graphics.BlurMaskFilter
//@Stable
//fun Modifier.dropShadow(
//    shape: Shape,
//    color: Color,
//    alpha: Float = 0.32f,
//    blur: Dp = 4.dp,
//    offsetY: Dp = 0.dp,
//    offsetX: Dp = (-15).dp,
//    spread: Dp = 0.dp
//) = this.drawBehind {
//    this.drawIntoCanvas {
//        val shadowSize = Size(size.width + spread.toPx(), size.height + spread.toPx())
//        val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)
//
//        val paint = Paint()
//        paint.color = color.copy(alpha)
//
//        if (blur.toPx() > 0) {
//            paint.asFrameworkPaint().apply {
//                maskFilter = BlurMaskFilter((blur * 2).toPx(), BlurMaskFilter.Blur.NORMAL)
//            }
//        }
//        drawIntoCanvas { canvas ->
//            canvas.save()
//            canvas.translate(offsetX.toPx(), offsetY.toPx())
//            canvas.drawOutline(shadowOutline, paint)
//            canvas.restore()
//        }
//    }
//}