package com.cairosquad.evolvefit.ui.screen.playWorkout.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cairosquad.evolvefit.design_system.component.appbar.IndicatorBar
import com.cairosquad.evolvefit.design_system.component.clockTimer.ClockTimer
import com.cairosquad.evolvefit.design_system.component.clockTimer.rememberClockTimerState
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.screen.playWorkout.component.ExerciseNameAndInfoIcon
import com.cairosquad.evolvefit.ui.util.ScreenSize
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back_button
import evolvefit.composeapp.generated.resources.exercise
import evolvefit.composeapp.generated.resources.ic_check_mark
import evolvefit.composeapp.generated.resources.ic_cross
import evolvefit.composeapp.generated.resources.ic_next_arrow
import evolvefit.composeapp.generated.resources.ic_pause
import evolvefit.composeapp.generated.resources.ic_play
import evolvefit.composeapp.generated.resources.ic_previous_arrow
import evolvefit.composeapp.generated.resources.im_default_workout
import evolvefit.composeapp.generated.resources.next
import evolvefit.composeapp.generated.resources.primary_button
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


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
            pagerState.animateScrollToPage(screenState.currentStep - 1)
        } catch (_: Exception) {
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .systemBarsPadding(),
    ) {
        IndicatorBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            currentStep = screenState.currentStep,
            totalSteps = screenState.workout.exercises.size,
            onClickLeadingIcon = listener::onClickCancelWorkout,
            isStepsCountVisible = false,
            leadingIcon = painterResource(Res.drawable.ic_cross),
        )
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            userScrollEnabled = false
        ) { pageIndex ->
            ExercisePage(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(),
                exercise = screenState.workout.exercises[pageIndex],
                listener = listener,
                currentStep = pageIndex + 1,
                totalSteps = screenState.workout.exercises.size
            )
        }
    }
}

@Composable
private fun ExercisePage(
    exercise: PlayWorkoutScreenState.ExerciseUiState,
    listener: PlayWorkoutInteractionListener,
    currentStep: Int,
    totalSteps: Int,
    modifier: Modifier = Modifier
) {
    val imageHeightDp = maxOf(ScreenSize.heightDp - 440, 360f)
    val imageWidthDp = minOf(ScreenSize.widthDp - 16, imageHeightDp)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NetworkImage(
            modifier = Modifier
                .padding(bottom = 40.dp)
                .padding(horizontal = 16.dp)
                .size(imageWidthDp.dp, imageHeightDp.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = exercise.imageUrls.firstOrNull() ?: "",
            contentDescription = exercise.name,
            defaultImage = painterResource(Res.drawable.im_default_workout)
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp),
            text = stringResource(Res.string.exercise) + " ${currentStep}/${totalSteps}",
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.outline,
        )
        ExerciseNameAndInfoIcon(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            exerciseName = exercise.name,
            onClickInfo = { listener.onClickExerciseInfo(exercise.id) }
        )
        BottomSection(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 48.dp),
            exerciseSpec = exercise.exerciseSpec,
            onFinishExercise = listener::onFinishExercise,
            onClickForward = listener::onClickForward,
            onClickBack = listener::onClickBack
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
) {
    when (exerciseSpec) {
        is PlayWorkoutScreenState.ExerciseSpecUiState.Reps -> {
            BottomSectionReps(
                modifier = modifier,
                reps = exerciseSpec.reps,
                onClickDone = onFinishExercise,
                onClickForward = onClickForward,
                onClickBack = onClickBack
            )
        }

        is PlayWorkoutScreenState.ExerciseSpecUiState.Time -> {
            BottomSectionTime(
                modifier = modifier,
                timeInSeconds = exerciseSpec.timeInSeconds,
                onTimeFinish = onFinishExercise,
                onClickForward = onClickForward,
                onClickBack = onClickBack
            )
        }
    }
}

@Composable
private fun BottomSectionReps(
    reps: Int,
    onClickDone: () -> Unit,
    onClickForward: () -> Unit,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
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
        )
    }
}

@Composable
private fun BottomSectionTime(
    timeInSeconds: Int,
    onTimeFinish: () -> Unit,
    onClickForward: () -> Unit,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier,
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
                .clickable(onClick = onClickBack)
                .background(Theme.color.surfaces.surfaceContainer)
                .padding(12.dp)
                .scale(scaleX = if (isRTL) -1f else 1f, scaleY = 1f),
            painter = painterResource(Res.drawable.ic_previous_arrow),
            contentDescription = stringResource(Res.string.back_button),
            tint = Theme.color.surfaces.onSurfaceContainer,
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
                .clickable(onClick = onClickForward)
                .background(Theme.color.surfaces.surfaceContainer)
                .padding(12.dp)
                .scale(scaleX = if (isRTL) -1f else 1f, scaleY = 1f),
            painter = painterResource(Res.drawable.ic_next_arrow),
            contentDescription = stringResource(Res.string.next),
            tint = Theme.color.surfaces.onSurfaceContainer,
        )
    }
}


@Preview
@Composable
private fun PlayWorkoutContentPerformPreview() {

    var step by remember { mutableStateOf(1) }

    AppTheme(
        isDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
        ) {
            PlayWorkoutContentPerform(
                screenState = PlayWorkoutScreenState(
                    workout = PlayWorkoutScreenState.WorkoutUiState(
                        exercises = listOf(
                            PlayWorkoutScreenState.ExerciseUiState(
                                name = "Push-up",
                                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                                imageUrls = listOf("https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg")
                            ),
                            PlayWorkoutScreenState.ExerciseUiState(
                                name = "Running",
                                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                                imageUrls = listOf("https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg")
                            ),
                            PlayWorkoutScreenState.ExerciseUiState(
                                name = "Push-up",
                                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                                imageUrls = listOf("https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg")
                            ),
                            PlayWorkoutScreenState.ExerciseUiState(
                                name = "Running",
                                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                                imageUrls = listOf("https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg")
                            ),
                            PlayWorkoutScreenState.ExerciseUiState(
                                name = "Push-up",
                                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                                imageUrls = listOf("https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg")
                            ),
                            PlayWorkoutScreenState.ExerciseUiState(
                                name = "Running",
                                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                                imageUrls = listOf("https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg")
                            ),
                        )
                    ),
                    currentStep = step
                ),
                listener = object : PlayWorkoutInteractionListener {
                    override fun onClickCancelWorkout() {
                        TODO("Not yet implemented")
                    }

                    override fun onGetReadyCounterFinish() {
                        TODO("Not yet implemented")
                    }

                    override fun onClickStart() {
                        TODO("Not yet implemented")
                    }

                    override fun onClickExerciseInfo(id: String) {
                        TODO("Not yet implemented")
                    }

                    override fun onClickRestFinish() {
                        TODO("Not yet implemented")
                    }

                    override fun onFinishExercise() {
                        step = if (step == 6) 1 else (step + 1)
                    }

                    override fun onClickForward() {
                        step = if (step == 6) 1 else (step + 1)
                    }

                    override fun onClickBack() {
                        step--
                    }

                    override fun onClickSkipRest() {
                        TODO("Not yet implemented")
                    }

                    override fun onClickNextToAnotherWorkout() {
                        TODO("Not yet implemented")
                    }

                    override fun onClickFinish() {
                        TODO("Not yet implemented")
                    }

                    override fun onClinkStayInWorkout() {
                        TODO("Not yet implemented")
                    }

                    override fun onClinkEnd() {
                        TODO("Not yet implemented")
                    }

                    override fun onDismissExerciseInfo() {
                        TODO("Not yet implemented")
                    }
                },
            )
        }
    }
}