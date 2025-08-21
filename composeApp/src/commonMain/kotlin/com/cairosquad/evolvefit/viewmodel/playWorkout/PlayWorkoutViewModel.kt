package com.cairosquad.evolvefit.viewmodel.playWorkout

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.usecase.workout.ManageWorkoutUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class PlayWorkoutViewModel(
    workoutId: String,
    private val manageWorkoutUseCase: ManageWorkoutUseCase
) : PlayWorkoutInteractionListener,
    BaseViewModel<PlayWorkoutScreenState, PlayWorkoutEffect>(PlayWorkoutScreenState()) {

    private var startTimeMilli: Long = 0

    init {
        loadData(workoutId)
    }

    private fun loadData(workoutID: String) {
        tryToCall(
            block = { manageWorkoutUseCase.getWorkoutById(workoutID) },
            onSuccess = ::onSuccessLoadData,
            onError = { }
        )

        startTimeMilli = Clock.System.now().toEpochMilliseconds()
    }

    private fun onSuccessLoadData(workout: Workout) {
        updateState {
            it.copy(
                workout = workout.toUiState(),
                stage = Stage.GET_READY
            )
        }
    }

    override fun onCancelWorkoutClicked() {
        updateState { it.copy(haseCancelWorkoutClicked = true) }
    }

    override fun onGetReadyCounterFinish() {
        updateState { it.copy(stage = Stage.PERFORM, currentStep = 1) }
    }

    override fun onStartClicked() {
        updateState { it.copy(stage = Stage.PERFORM, currentStep = 1)}
    }

    override fun onExerciseInfoClicked(id: String) {
        updateState { it.copy(showExerciseInfo = true) }
    }

    override fun onRestFinishClicked() {
        updateState { it.copy(stage = Stage.PERFORM,) }
    }

    override fun onFinishExercise() {
        if (isLastExercise) {
            updateState { it.copy(stage = Stage.FINISH, totalTimeMinutes = totalTimeSoFarMinutes) }
            return
        }
        updateState {it.copy(stage = Stage.REST, currentStep = nextStep) }
    }

    override fun onForwardClicked() {
        updateState { it.copy(currentStep = nextStep) }
    }

    override fun onBackClicked() {
        updateState { it.copy(currentStep = previousStep) }
    }

    override fun onSkipRestClicked() {
        updateState { it.copy(stage = Stage.PERFORM) }
    }

    override fun onNextToAnotherWorkoutClicked() {
        sendEffect(PlayWorkoutEffect.NavigateBackToApp)
    }

    override fun onFinishClicked() {
        sendEffect(PlayWorkoutEffect.NavigateBack)
    }

    override fun onStayInWorkoutClinked() {
        updateState { it.copy(haseCancelWorkoutClicked = false) }
    }

    override fun onEndClinked() {
        sendEffect(PlayWorkoutEffect.NavigateBack)
    }

    override fun onDismissExerciseInfo() {
        updateState { it.copy(showExerciseInfo = false) }
    }

    private val isLastExercise: Boolean
        get() = screenState.value.currentStep == screenState.value.workout.exercises.size

    private val nextStep: Int
        get() = minOf(
            screenState.value.currentStep + 1,
            screenState.value.workout.exercises.size
        )

    private val previousStep: Int
        get() = maxOf(
            screenState.value.currentStep - 1,
            1
        )

    private val totalTimeSoFarMinutes: Int
        get() = ((Clock.System.now().toEpochMilliseconds() - startTimeMilli) / 1000 / 60).toInt()

    companion object {
        const val GET_READY_TIME_IN_SECONDS = 10
        const val REST_TIMER_IN_SECONDS = 30
        const val REST_TIMER_INCREMENT_IN_SECONDS = 15
    }
}