package com.cairosquad.evolvefit.ui.screen.playWorkout.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.FINISH
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.GET_READY
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.PERFORM
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage.REST
import org.jetbrains.compose.ui.tooling.preview.Preview


private val fakeListener = object : PlayWorkoutInteractionListener {
    override fun onClickCancelWorkout() {
        // TODO("Not yet implemented")
    }

    override fun onGetReadyCounterFinish() {
        // TODO("Not yet implemented")
    }

    override fun onClickStart() {
        // TODO("Not yet implemented")
    }

    override fun onClickExerciseInfo(id: String) {
        // TODO("Not yet implemented")
    }

    override fun onClickRestFinish() {
        // TODO("Not yet implemented")
    }

    override fun onFinishExercise() {
        // TODO("Not yet implemented")
    }

    override fun onClickForward() {
        // TODO("Not yet implemented")
    }

    override fun onClickBack() {
        // TODO("Not yet implemented")
    }

    override fun onClickSkipRest() {
        // TODO("Not yet implemented")
    }

    override fun onClickNextToAnotherWorkout() {
        // TODO("Not yet implemented")
    }

    override fun onClickFinish() {
        // TODO("Not yet implemented")
    }

    override fun onClinkStayInWorkout() {
        // TODO("Not yet implemented")
    }

    override fun onClinkEnd() {
        // TODO("Not yet implemented")
    }

    override fun onDismissExerciseInfo() {
        // TODO("Not yet implemented")
    }

}
private val fakeScreenState = PlayWorkoutScreenState(
    workout = PlayWorkoutScreenState.WorkoutUiState(
        name = "Upper Body",
        imageUrl = "fakeUrl",
        level = PlayWorkoutScreenState.WorkoutLevelUiState.BEGINNER,
        exercises = listOf(
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Push-up",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                imageUrls = listOf("fakeUrl")
            ),
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Running",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                imageUrls = listOf("fakeUrl")
            ),
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Push-up",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                imageUrls = listOf("fakeUrl")
            ),
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Running",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                imageUrls = listOf("fakeUrl")
            ),
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Push-up",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                imageUrls = listOf("fakeUrl")
            ),
            PlayWorkoutScreenState.ExerciseUiState(
                name = "Running",
                exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                imageUrls = listOf("fakeUrl")
            ),
        )
    ),
    currentStep = 1
)

@Composable
private fun PlayWorkoutContentPreview(screenState: PlayWorkoutScreenState) {
    AppTheme(
        isDarkTheme = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
        ) {
            PlayWorkoutContent(
                screenState = screenState,
                listener = fakeListener
            )
        }
    }
}

@Preview
@Composable
private fun PlayWorkoutContentPreview1() {
    PlayWorkoutContentPreview(screenState = fakeScreenState.copy(stage = GET_READY))
}

@Preview
@Composable
private fun PlayWorkoutContentPreview2() {
    PlayWorkoutContentPreview(screenState = fakeScreenState.copy(stage = PERFORM))
}

@Preview
@Composable
private fun PlayWorkoutContentPreview7() {
    PlayWorkoutContentPreview(
        screenState = fakeScreenState.copy(
            stage = PERFORM,
            currentStep = 4
        )
    )
}

@Preview
@Composable
private fun PlayWorkoutContentPreview3() {
    PlayWorkoutContentPreview(screenState = fakeScreenState.copy(stage = REST))
}

@Preview
@Composable
private fun PlayWorkoutContentPreview4() {
    PlayWorkoutContentPreview(screenState = fakeScreenState.copy(stage = FINISH))
}

@Preview
@Composable
private fun PlayWorkoutContentPreview5() {
    PlayWorkoutContentPreview(
        screenState = fakeScreenState.copy(
            stage = GET_READY,
            haseCancelWorkoutClicked = true
        )
    )
}


@Preview
@Composable
private fun PlayWorkoutContentPreview6() {
    PlayWorkoutContentPreview(
        screenState = fakeScreenState.copy(
            stage = PERFORM,
            haseCancelWorkoutClicked = true
        )
    )
}

