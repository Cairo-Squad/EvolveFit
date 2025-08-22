package com.cairosquad.evolvefit.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.Chip
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.bullet_point
import evolvefit.composeapp.generated.resources.close
import evolvefit.composeapp.generated.resources.equipment
import evolvefit.composeapp.generated.resources.exercise_image
import evolvefit.composeapp.generated.resources.focus_area
import evolvefit.composeapp.generated.resources.ic_arrow_left
import evolvefit.composeapp.generated.resources.ic_arrow_right
import evolvefit.composeapp.generated.resources.instructions
import evolvefit.composeapp.generated.resources.next
import evolvefit.composeapp.generated.resources.previous
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ExerciseDetailsBottomSheet(
    name: String,
    instructions: List<String>,
    images: List<String>,
    equipment: String,
    focusAreas: List<String>,
    isVisible: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        modifier = modifier,
        isVisible = isVisible,
        onDismiss = onDismiss,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = name,
                style = Theme.textStyle.title.largeBold16,
                color = Theme.color.surfaces.onSurface,
            )
            ImageCarousel(images)
            Text(
                modifier = Modifier.padding(top = 16.dp, bottom = 12.dp),
                text = stringResource(Res.string.instructions),
                style = Theme.textStyle.headline.largeBold18,
                color = Theme.color.surfaces.onSurface,
            )
            instructions.forEach { instruction ->
                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = stringResource(Res.string.bullet_point, instruction),
                    style = Theme.textStyle.label.smallRegular14,
                    color = Theme.color.surfaces.onSurfaceVariant,
                )
            }
            Text(
                modifier = Modifier.padding(top = 20.dp, bottom = 12.dp),
                text = stringResource(Res.string.equipment),
                style = Theme.textStyle.headline.largeBold18,
                color = Theme.color.surfaces.onSurface,
            )
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = stringResource(Res.string.bullet_point, equipment),
                style = Theme.textStyle.label.smallRegular14,
                color = Theme.color.surfaces.onSurfaceVariant,
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp),
                text = stringResource(Res.string.focus_area),
                style = Theme.textStyle.headline.largeBold18,
                color = Theme.color.surfaces.onSurface,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                focusAreas.forEach { area -> Chip(title = area) }
            }
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.End)
                    .padding(bottom = 16.dp, top = 40.dp)
                    .navigationBarsPadding(),
                text = stringResource(Res.string.close),
                onClick = onDismiss,
                isEnabled = true
            )
        }
    }
}

@Composable
private fun ImageCarousel(
    images: List<String>,
    modifier: Modifier = Modifier
) {
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    var currentIndex by remember { mutableStateOf(0) }
    val startArrowAnimatedColor by animateColorAsState(
        targetValue =
            if (currentIndex > 0) Theme.color.surfaces.textColor
            else Theme.color.surfaces.onSurfaceVariant,
        animationSpec = tween(200),
    )
    val endArrowAnimatedColor by animateColorAsState(
        targetValue =
            if (currentIndex < images.size - 1) Theme.color.surfaces.textColor
            else Theme.color.surfaces.onSurfaceVariant,
        animationSpec = tween(200),
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        NetworkImage(
            model = images.getOrNull(currentIndex) ?: "",
            contentDescription = stringResource(Res.string.exercise_image),
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp)),
        )

        Icon(
            painter = painterResource(Res.drawable.ic_arrow_left),
            contentDescription = stringResource(Res.string.previous),
            tint = startArrowAnimatedColor,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 8.dp)
                .background(Theme.color.surfaces.onSurfaceAt2, CircleShape)
                .size(32.dp)
                .clip(CircleShape)
                .clickable(onClick = { currentIndex-- }, enabled = currentIndex > 0)
                .padding(8.dp)
                .scale(scaleX = if (isRtl) -1f else 1f, scaleY = 1f)
        )

        Icon(
            painter = painterResource(Res.drawable.ic_arrow_right),
            contentDescription = stringResource(Res.string.next),
            tint = endArrowAnimatedColor,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 8.dp)
                .background(Theme.color.surfaces.onSurfaceAt2, CircleShape)
                .size(32.dp)
                .clip(CircleShape)
                .clickable(onClick = { currentIndex++ }, enabled = currentIndex < images.size - 1)
                .padding(8.dp)
                .scale(scaleX = if (isRtl) -1f else 1f, scaleY = 1f)
        )
    }
}

@Preview
@Composable
private fun ExerciseDetailsBottomSheetPreview() {
    ExerciseDetailsBottomSheet(
        name = "Squats",
        instructions = listOf(
            "Stand with your feet shoulder-width apart",
            "Lower your hips until your",
            "thighs are parallel to the floor",
            "Push through your heels to return to standing",
        ),
        images = listOf("", ""),
        equipment = "Barbell",
        focusAreas = listOf("Core", "Shoulders"),
        isVisible = true,
        onDismiss = {}
    )
}