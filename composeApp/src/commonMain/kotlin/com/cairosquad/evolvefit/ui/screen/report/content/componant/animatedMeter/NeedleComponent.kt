package com.cairosquad.evolvefit.ui.screen.report.content.componant.animatedMeter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_meter
import evolvefit.composeapp.generated.resources.needle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NeedleComponent(
    backgroundColor: Color,
    sweepAngle: Float,
    rotationState: Float,
    offsetX: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(backgroundColor, CircleShape)
        )
        Image(
            painter = painterResource(Res.drawable.ic_meter),
            contentDescription = stringResource(Res.string.needle),
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopCenter).graphicsLayer {
                    rotationZ = sweepAngle - rotationState
                    transformOrigin =
                        TransformOrigin(pivotFractionX = 0.5f, pivotFractionY = 1f)
                }
                .offset(y = 3.25.dp, x = offsetX.dp),
            colorFilter = ColorFilter.tint(Color.White)
        )
        Box(
            modifier = Modifier
                .size(4.dp)
                .background(backgroundColor, CircleShape)
        )
    }
}

@Preview
@Composable
private fun NeedleComponentPreview() {
    NeedleComponent(
        backgroundColor = Theme.color.surfaces.outlineVariant,
        sweepAngle = 180f * 0.5f,
        rotationState = 90f,
        offsetX = 0f,
    )
}