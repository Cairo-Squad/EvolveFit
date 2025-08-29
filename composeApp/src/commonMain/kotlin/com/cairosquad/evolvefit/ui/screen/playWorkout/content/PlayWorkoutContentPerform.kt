package com.cairosquad.evolvefit.ui.screen.playWorkout.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.component.appbar.IndicatorBar
import com.cairosquad.evolvefit.design_system.component.clockTimer.ClockTimer
import com.cairosquad.evolvefit.design_system.component.clockTimer.rememberClockTimerState
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.screen.playWorkout.content.component.ExerciseNameAndInfoIcon
import com.cairosquad.evolvefit.ui.util.ScreenSize
import com.cairosquad.evolvefit.viewmodel.play_workout.PlayWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.play_workout.PlayWorkoutScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back_button
import evolvefit.composeapp.generated.resources.exercise
import evolvefit.composeapp.generated.resources.ic_app_logo
import evolvefit.composeapp.generated.resources.ic_check_mark
import evolvefit.composeapp.generated.resources.ic_cross
import evolvefit.composeapp.generated.resources.ic_next_arrow
import evolvefit.composeapp.generated.resources.ic_pause
import evolvefit.composeapp.generated.resources.ic_play
import evolvefit.composeapp.generated.resources.ic_previous_arrow
import evolvefit.composeapp.generated.resources.next
import evolvefit.composeapp.generated.resources.primary_button
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun PlayWorkoutContentPerform(
    screenState: PlayWorkoutScreenState,
    listener: PlayWorkoutInteractionListener
) {

    val pagerState = rememberPagerState(
        pageCount = { screenState.workout.exercises.size },
        initialPage = screenState.currentStep - 1
    )

    LaunchedEffect(screenState.currentStep) {
        try {
            delay(200)
            pagerState.animateScrollToPage(screenState.currentStep - 1)
        } catch (_: Exception) {
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp)
            .systemBarsPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IndicatorBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            currentStep = screenState.currentStep,
            totalSteps = screenState.workout.exercises.size,
            onClickLeadingIcon = listener::onCancelWorkoutClicked,
            isStepsCountVisible = false,
            leadingIcon = painterResource(Res.drawable.ic_cross),
        )
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) { pageIndex ->
            val imageHeightDp = maxOf(ScreenSize.heightDp - 440, 360f)
            val imageWidthDp = minOf(ScreenSize.widthDp - 16, imageHeightDp)

            NetworkImage(
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .aspectRatio(imageWidthDp / imageHeightDp, matchHeightConstraintsFirst = true)
                    .clip(RoundedCornerShape(8.dp)),
                model = screenState.workout.exercises[pageIndex].imageUrls.firstOrNull() ?: "",
                contentDescription = screenState.workout.exercises[pageIndex].name,
                defaultImage = painterResource(Res.drawable.ic_app_logo),
                placeholderImageSize = DpSize(imageWidthDp.dp / 2, imageHeightDp.dp / 2),
                loadingPlaceHolder = painterResource(Res.drawable.ic_app_logo)
            )
        }
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            text = stringResource(Res.string.exercise) + " ${pagerState.currentPage + 1}/${screenState.workout.exercises.size}",
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.outline,
        )
        ExerciseNameAndInfoIcon(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp),
            exerciseName = screenState.workout.exercises[pagerState.currentPage].name,
            onClickInfo = { listener.onExerciseInfoClicked(screenState.workout.exercises[pagerState.currentPage].id) },
            textStyle = Theme.textStyle.display.largeBold20
        )
        BottomSection(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .navigationBarsPadding(),
            exerciseSpec = screenState.workout.exercises[pagerState.currentPage].exerciseSpec,
            onFinishExercise = listener::onFinishExercise,
            onClickForward = listener::onForwardClicked,
            onClickBack = listener::onBackClicked,
            isForwardButtonEnabled = pagerState.currentPage + 1 < screenState.workout.exercises.size,
            isBackButtonEnabled = pagerState.currentPage > 0,
        )

    }
}

@Composable
private fun BottomSection(
    exerciseSpec: PlayWorkoutScreenState.ExerciseSpecUiState,
    onFinishExercise: () -> Unit,
    onClickForward: () -> Unit,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
    isForwardButtonEnabled: Boolean = true,
    isBackButtonEnabled: Boolean = true,
) {
    when (exerciseSpec) {
        is PlayWorkoutScreenState.ExerciseSpecUiState.Reps -> {
            BottomSectionReps(
                modifier = modifier,
                reps = exerciseSpec.reps,
                onClickDone = onFinishExercise,
                onClickForward = onClickForward,
                onClickBack = onClickBack,
                isForwardButtonEnabled = isForwardButtonEnabled,
                isBackButtonEnabled = isBackButtonEnabled,
            )
        }

        is PlayWorkoutScreenState.ExerciseSpecUiState.Time -> {
            BottomSectionTime(
                modifier = modifier,
                timeInSeconds = exerciseSpec.timeInSeconds,
                onTimeFinish = onFinishExercise,
                onClickForward = onClickForward,
                onClickBack = onClickBack,
                isForwardButtonEnabled = isForwardButtonEnabled,
                isBackButtonEnabled = isBackButtonEnabled,
            )
        }
    }
}

@Composable
private fun BottomSectionReps(
    reps: Int?,
    onClickDone: () -> Unit,
    onClickForward: () -> Unit,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
    isForwardButtonEnabled: Boolean = true,
    isBackButtonEnabled: Boolean = true,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 60.dp),
            text = "X$reps",
            style = Theme.textStyle.title.largeBold16.copy(fontSize = 48.sp),
            color = Theme.color.surfaces.onSurfaceContainer,
        )
        BottomButtons(
            onClickPrimaryButton = onClickDone,
            onClickForward = onClickForward,
            onClickBack = onClickBack,
            primaryButtonIcon = painterResource(Res.drawable.ic_check_mark),
            isForwardButtonEnabled = isForwardButtonEnabled,
            isBackButtonEnabled = isBackButtonEnabled,
        )
    }
}

@Composable
private fun BottomSectionTime(
    timeInSeconds: Int?,
    onTimeFinish: () -> Unit,
    onClickForward: () -> Unit,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
    isForwardButtonEnabled: Boolean = true,
    isBackButtonEnabled: Boolean = true,
) {

    var isPaused by remember { mutableStateOf(false) }

    val clockTimerState = rememberClockTimerState(
        totalTime = timeInSeconds,
        onFinish = onTimeFinish
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ClockTimer(
            modifier = Modifier.padding(bottom = 60.dp),
            clockTimerState = clockTimerState
        )
        BottomButtons(
            onClickPrimaryButton = {
                clockTimerState.triggerPause()
                isPaused = !isPaused
            },
            onClickForward = onClickForward,
            onClickBack = onClickBack,
            primaryButtonIcon =
                if (isPaused) painterResource(Res.drawable.ic_play)
                else painterResource(Res.drawable.ic_pause),
            isForwardButtonEnabled = isForwardButtonEnabled,
            isBackButtonEnabled = isBackButtonEnabled,
        )
    }
}


@Composable
private fun BottomButtons(
    onClickPrimaryButton: () -> Unit,
    onClickForward: () -> Unit,
    onClickBack: () -> Unit,
    primaryButtonIcon: Painter,
    modifier: Modifier = Modifier,
    isForwardButtonEnabled: Boolean = true,
    isBackButtonEnabled: Boolean = true,
) {
    val isRTL = LocalLayoutDirection.current == LayoutDirection.Rtl

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable(onClick = onClickBack, enabled = isBackButtonEnabled)
                .background(Theme.color.surfaces.surfaceContainer)
                .padding(12.dp)
                .scale(scaleX = if (isRTL) -1f else 1f, scaleY = 1f),
            painter = painterResource(Res.drawable.ic_previous_arrow),
            contentDescription = stringResource(Res.string.back_button),
            tint =
                animateColorAsState(
                    if (isBackButtonEnabled) Theme.color.surfaces.onSurfaceContainer
                    else Theme.color.surfaces.outlineVariant
                ).value,
        )
        Icon(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .clickable(onClick = onClickPrimaryButton)
                .background(Theme.color.brand.primary)
                .padding(20.dp),
            painter = primaryButtonIcon,
            contentDescription = stringResource(Res.string.primary_button),
            tint = Theme.color.brand.onPrimary,
        )
        Icon(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable(onClick = onClickForward, enabled = isForwardButtonEnabled)
                .background(Theme.color.surfaces.surfaceContainer)
                .padding(12.dp)
                .scale(scaleX = if (isRTL) -1f else 1f, scaleY = 1f),
            painter = painterResource(Res.drawable.ic_next_arrow),
            contentDescription = stringResource(Res.string.next),
            tint =
                animateColorAsState(
                    if (isForwardButtonEnabled) Theme.color.surfaces.onSurfaceContainer
                    else Theme.color.surfaces.outlineVariant
                ).value,
        )
    }
}