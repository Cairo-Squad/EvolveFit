package com.cairosquad.evolvefit.design_system.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_green_check_circle
import org.jetbrains.compose.resources.painterResource


@Composable
fun CheckboxItem(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    description: String? = null,
    style: CheckboxStyle = CheckboxStyle.Tick
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(color = Theme.color.surfaces.surfaceContainer )
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.
            clip(RoundedCornerShape(8.dp))
                . background(color = Theme.color.surfaces.surfaceContainer )
                .weight(1f)
        ) {
            Text(
                text = text,
                style = Theme.textStyle.body.mediumMedium14,
                color = Theme.color.surfaces.onSurface
            )
            description?.let { desc ->
                Text(
                    text = desc,
                    style = Theme.textStyle.label.smallRegular12,
                    color = Theme.color.surfaces.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
        when (style) {
            CheckboxStyle.Tick -> {
                CustomTick(isChecked = isChecked,onCheckedChange =onCheckedChange)
            }
            CheckboxStyle.Switch -> {
                CustomSwitch(
                    isChecked = isChecked,
                    onCheckedChange = onCheckedChange
                )
            }
        }
    }
}
enum class CheckboxStyle {
    Tick,
    Switch
}
@Composable
fun CustomTick(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        targetValue =
            if (isChecked) Theme.color.surfaces.surfaceContainer
            else Theme.color.surfaces.surfaceContainer.copy(alpha = 0.0f),
        animationSpec = tween(300)
    )
    val boxModifier = modifier
        .size(24.dp)
        .clip(CircleShape)
        .background(
            color = backgroundColor,
            shape = CircleShape
        )
        .then(
            if (!isChecked) {
                Modifier.border(
                    width = 1.dp,
                    color = Theme.color.surfaces.outlineVariant,
                    shape = CircleShape
                )
            } else Modifier
        )
        .clickable { onCheckedChange(!isChecked) }
    Box(
        modifier = boxModifier,
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isChecked,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_green_check_circle),
                contentDescription = null,
                tint = Theme.color.brand.primary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
@Composable
fun CustomSwitch(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val trackWidth = 48.dp
    val trackHeight = 28.dp
    val thumbSize = 20.dp
    val padding = 4.dp
    val transition = updateTransition(targetState = isChecked, label = "SwitchTransition")
    val thumbOffsetX by transition.animateDp(
        label = "ThumbOffset"
    ) { isChecked ->
        if (isChecked) trackWidth - thumbSize - padding else padding
    }
    val trackColor by transition.animateColor(
        label = "TrackColor"
    ) { isChecked ->
        if (isChecked) Theme.color.brand.primary else Theme.color.surfaces.outlineVariant
    }
    val thumbColor = Theme.color.surfaces.surface
    Box(
        modifier = modifier
            .size(trackWidth, trackHeight)
            .clip(CircleShape)
            .background(color = trackColor)
            .clickable(enabled = enabled) { onCheckedChange(!isChecked) },
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffsetX)
                .size(thumbSize)
                .background(color = thumbColor, shape = CircleShape)
        )
    }
}