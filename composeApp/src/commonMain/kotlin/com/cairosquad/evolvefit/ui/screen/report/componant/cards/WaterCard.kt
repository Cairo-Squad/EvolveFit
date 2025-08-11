package com.cairosquad.evolvefit.ui.screen.report.componant.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.report.componant.CardHeaderSection
import com.cairosquad.evolvefit.ui.screen.report.componant.animatedMeter.RisingWaveAnimation
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_water_drop
import evolvefit.composeapp.generated.resources.liters
import evolvefit.composeapp.generated.resources.water
import evolvefit.composeapp.generated.resources.water_drops
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WaterCard(
    waterConsumed: String,
    isAnimationStarted: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(horizontal = 12.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardHeaderSection(
            painter = painterResource(Res.drawable.ic_water_drop),
            title = stringResource(Res.string.water),
            tint = Theme.color.system.info
        )
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .width(134.dp)
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .border(
                        width = 5.dp,
                        shape = CircleShape,
                        color = Theme.color.system.info
                    )
            ) {
                Image(
                    modifier = Modifier.padding(8.dp),
                    painter = painterResource(Res.drawable.water_drops),
                    contentDescription = stringResource(Res.string.water_drops)
                )
                RisingWaveAnimation(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .scale(scaleX = -1f, scaleY = 1f),
                    fillPercent = 0.5f,
                    isAnimationStarted = isAnimationStarted
                )
            }
            Row(
                modifier = Modifier.align(Alignment.Center)
                    .offset(y = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = waterConsumed,
                    color = Theme.color.surfaces.textColor,
                    style = Theme.textStyle.display.largeBold24
                )
                Text(
                    text = "/${stringResource(Res.string.liters)}",
                    color = Theme.color.surfaces.textColor,
                    style = Theme.textStyle.label.smallRegular14
                )
            }
        }
    }
}

@Preview
@Composable
private fun WaterCardPreview() {
    WaterCard(
        modifier = Modifier
            .width(160.dp)
            .height(214.dp),
        waterConsumed = "06",
        isAnimationStarted = true
    )
}

@Preview
@Composable
private fun WaterCardLongPreview() {
    WaterCard(
        modifier = Modifier
            .width(160.dp)
            .height(260.dp),
        waterConsumed = "08",
        isAnimationStarted = true
    )
}