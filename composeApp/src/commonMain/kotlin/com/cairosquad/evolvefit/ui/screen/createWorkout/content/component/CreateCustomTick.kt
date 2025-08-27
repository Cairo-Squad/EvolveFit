package com.cairosquad.evolvefit.ui.screen.createWorkout.content.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_green_check_circle
import org.jetbrains.compose.resources.painterResource

@Composable
fun CreateCustomTick(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit = {}
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
        .toggleable(
            value = isChecked,
            onValueChange = onCheckedChange,
            role = Role.Checkbox
        )

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