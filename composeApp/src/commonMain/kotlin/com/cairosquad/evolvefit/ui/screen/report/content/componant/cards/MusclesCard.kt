package com.cairosquad.evolvefit.ui.screen.report.content.componant.cards

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.most_trained_muscles
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MusclesCard(
    musclesName: List<String>,
    trainedMusclesPercentage: List<Float>,
    isAnimationStarted: Boolean,
    modifier: Modifier = Modifier,
) {
    ReportCard(
        modifier = modifier,
        title = stringResource(Res.string.most_trained_muscles),
        value = ""
    ) {
        repeat(musclesName.size) {
            MuscleItem(
                modifier = Modifier.then(
                    if (it < musclesName.size) Modifier.padding(bottom = 16.dp)
                    else Modifier
                ),
                muscleName = musclesName[it],
                percentage = trainedMusclesPercentage[it],
                isAnimationStarted = isAnimationStarted
            )
        }
    }
}


@Composable
private fun MuscleItem(
    muscleName: String,
    percentage: Float,
    modifier: Modifier = Modifier,
    isAnimationStarted: Boolean = false
) {
    val animatePercentage by animateFloatAsState(
        targetValue = if (isAnimationStarted) percentage else 0f,
        animationSpec = tween(
            durationMillis = 1200,
            easing = LinearOutSlowInEasing
        )
    )
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(height = 40.dp, width = 4.dp)
                .clip(RoundedCornerShape(topEnd = 100.dp, bottomEnd = 100.dp))
                .background(Theme.color.brand.primary)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(1f),
                    text = muscleName,
                    style = Theme.textStyle.label.smallRegular14,
                    color = Theme.color.surfaces.onSurfaceVariant
                )
                Text(
                    text = "${(animatePercentage * 100).toInt()}%",
                    style = Theme.textStyle.headline.largeBold16,
                    color = Theme.color.surfaces.onSurface
                )
            }
            Box {
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth()
                        .clip(CircleShape)
                        .background(Theme.color.surfaces.surfaceVariant)
                )
                Box(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth(animatePercentage)
                        .clip(CircleShape)
                        .background(Theme.color.brand.primary)
                )
            }
        }
    }
}

@Preview
@Composable
private fun MusclesCardPreview() {
    val musclesName = listOf("Back", "Legs", "Shoulders", "Arms", "Core")
    val musclesPercentage = listOf(0.32f, 0.24f, 0.68f, 0.49f, 0.11f)
    MusclesCard(
        musclesName = musclesName,
        trainedMusclesPercentage = musclesPercentage,
        true
    )
}