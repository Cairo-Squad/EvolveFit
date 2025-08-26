package com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.exercise_image
import evolvefit.composeapp.generated.resources.ic_arrow_left
import evolvefit.composeapp.generated.resources.ic_arrow_right
import evolvefit.composeapp.generated.resources.next
import evolvefit.composeapp.generated.resources.previous
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun ImageCarousel(
    images: List<String>,
    exerciseType: WorkoutDetailsScreenState.ExerciseType,
    modifier: Modifier = Modifier
) {

    val layoutDirection = LocalLayoutDirection.current
    var currentIndex by remember { mutableStateOf(0) }
    val animatedColor by animateColorAsState(
        targetValue = when (currentIndex) {
            0 -> Theme.color.surfaces.onSurfaceVariant
            else -> Theme.color.surfaces.textColor
        },
        animationSpec = tween(200),
        label = ""
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
        if (images.size > 1) {
        val (leftIcon, leftAction, leftEnabled) = if (layoutDirection == LayoutDirection.Ltr) {
            Triple(
                Res.drawable.ic_arrow_left,
                { if (currentIndex > 0) currentIndex -= 1 },
                currentIndex > 0
            )
        } else {
            Triple(
                Res.drawable.ic_arrow_right,
                { if (currentIndex < images.size - 1) currentIndex += 1 },
                currentIndex < images.size - 1
            )
        }

        Icon(
            painter = painterResource(leftIcon),
            contentDescription = stringResource(Res.string.previous),
            tint = if (!leftEnabled) Theme.color.surfaces.onSurfaceVariant else Theme.color.surfaces.textColor,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 8.dp)
                .background(Theme.color.surfaces.onSurfaceAt2, CircleShape)
                .size(32.dp)
                .padding(8.dp)
                .clip(CircleShape)
                .then(if (leftEnabled) Modifier.clickable { leftAction() } else Modifier)
        )

        val (rightIcon, rightAction, rightEnabled) = if (layoutDirection == LayoutDirection.Ltr) {
            Triple(
                Res.drawable.ic_arrow_right,
                { if (currentIndex < images.size - 1) currentIndex += 1 },
                currentIndex < images.size - 1
            )
        } else {
            Triple(
                Res.drawable.ic_arrow_left,
                { if (currentIndex > 0) currentIndex -= 1 },
                currentIndex > 0
            )
        }

        Icon(
            painter = painterResource(rightIcon),
            contentDescription = stringResource(Res.string.next),
            tint = if (!rightEnabled) Theme.color.surfaces.onSurfaceVariant else Theme.color.surfaces.textColor,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(horizontal = 8.dp)
                .background(Theme.color.surfaces.onSurfaceAt2, CircleShape)
                .size(32.dp)
                .padding(8.dp)
                .clip(CircleShape)
                .then(if (rightEnabled) Modifier.clickable { rightAction() } else Modifier)
        )
            }
        MeasurementRow(
            exerciseType = exerciseType,
            modifier = Modifier
                .align(Alignment.BottomEnd).padding(bottom = 8.dp, end = 8.dp)
                .background(Theme.color.surfaces.onSurfaceAt2, RoundedCornerShape(24.dp))
                .padding(vertical = 8.dp, horizontal = 12.dp),
            iconTint = Theme.color.surfaces.textColor,
            textColor = Theme.color.surfaces.textColor,
            textStyle = Theme.textStyle.label.mediumMedium12
        )
    }


}