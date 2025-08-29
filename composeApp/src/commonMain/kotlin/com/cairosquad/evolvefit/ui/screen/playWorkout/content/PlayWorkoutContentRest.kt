package com.cairosquad.evolvefit.ui.screen.playWorkout.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.screen.playWorkout.content.component.ColumnWithBackgroundImage
import com.cairosquad.evolvefit.ui.screen.playWorkout.content.component.ExerciseNameAndInfoIcon
import com.cairosquad.evolvefit.ui.screen.playWorkout.content.component.RestTimer
import com.cairosquad.evolvefit.viewmodel.play_workout.PlayWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.play_workout.PlayWorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.play_workout.PlayWorkoutViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.exercise_info
import evolvefit.composeapp.generated.resources.ic_app_logo
import evolvefit.composeapp.generated.resources.ic_default_image
import evolvefit.composeapp.generated.resources.ic_play
import evolvefit.composeapp.generated.resources.im_default_workout
import evolvefit.composeapp.generated.resources.next_movement
import evolvefit.composeapp.generated.resources.take_a_rest
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PlayWorkoutContentRest(
    screenState: PlayWorkoutScreenState,
    listener: PlayWorkoutInteractionListener
) {
    val currentExercise = screenState.workout.exercises
        .getOrNull(screenState.currentStep - 1)
        ?: PlayWorkoutScreenState.ExerciseUiState()

    val upperSpaceWeight = 0.546f
    val lowerSpaceWeight = 1 - upperSpaceWeight

    ColumnWithBackgroundImage(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        model = screenState.workout.imageUrl,
        contentDescription = screenState.workout.name,
        contentPadding = WindowInsets.systemBars.asPaddingValues(),
        backgroundBlurRadius = 32.dp
    ) {
        Spacer(Modifier.weight(upperSpaceWeight))
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp),
            text = stringResource(Res.string.take_a_rest),
            style = Theme.textStyle.display.largeBold20,
            color = Theme.color.surfaces.textColor,
        )
        RestTimer(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp),
            timeInSeconds = PlayWorkoutViewModel.REST_TIMER_IN_SECONDS,
            onClickSkipRest = listener::onSkipRestClicked,
            onFinish = listener::onRestFinishClicked,
            timeIncrement = PlayWorkoutViewModel.REST_TIMER_INCREMENT_IN_SECONDS,
            textColor = Theme.color.surfaces.textColor,
            textStyle = Theme.textStyle.title.largeBold16.copy(
                fontSize = 48.sp,
                fontWeight = FontWeight.Black
            )
        )
        Spacer(Modifier.weight(lowerSpaceWeight))
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp),
            text = stringResource(Res.string.next_movement) + " (${screenState.currentStep}/${screenState.workout.exercises.size})",
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.textColor,
        )
        ExerciseNameAndInfoIcon(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            exerciseName = currentExercise.name,
            onClickInfo = { listener.onExerciseInfoClicked(currentExercise.id) },
            textStyle = Theme.textStyle.headline.mediumMedium18,
            textColor = Theme.color.surfaces.textColor
        )
        NetworkImageWithPlayButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp)
                .aspectRatio(328f/360f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(8.dp))
                .clickable(onClick = { listener.onExerciseInfoClicked(currentExercise.id) }),
            model = currentExercise.imageUrls.firstOrNull() ?: "",
            contentDescription = currentExercise.name,
            defaultImage = painterResource(Res.drawable.ic_app_logo),
        )
    }
}

@Composable
private fun NetworkImageWithPlayButton(
    model: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    defaultImage: Painter = painterResource(Res.drawable.im_default_workout)
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        NetworkImage(
            modifier = Modifier.matchParentSize(),
            model = model,
            contentDescription = contentDescription,
            defaultImage = defaultImage,
            loadingPlaceHolder = painterResource(Res.drawable.ic_app_logo)
        )
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(40.dp)
                .background(Theme.color.surfaces.onSurfaceAt2, CircleShape)
                .padding(13.dp),
            painter = painterResource(Res.drawable.ic_play),
            contentDescription = stringResource(Res.string.exercise_info),
            tint = Theme.color.brand.primary
        )
    }
}