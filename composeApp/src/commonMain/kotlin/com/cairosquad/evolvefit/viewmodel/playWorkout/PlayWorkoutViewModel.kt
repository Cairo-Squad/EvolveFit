package com.cairosquad.evolvefit.viewmodel.playWorkout

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
            onSuccess = { workout ->
                updateState {
                    it.copy(
                        workout = workout.toUiState(),
                        stage = Stage.GET_READY
                    )
                }
            },
            onError = { }
        )

        startTimeMilli = Clock.System.now().toEpochMilliseconds()
    }

    override fun onClickCancelWorkout() {
        updateState { it.copy(haseCancelWorkoutClicked = true) }
    }

    override fun onGetReadyCounterFinish() {
        updateState {
            it.copy(
                stage = Stage.PERFORM,
                currentStep = 1
            )
        }
    }

    override fun onClickStart() {
        updateState {
            it.copy(
                stage = Stage.PERFORM,
                currentStep = 1
            )
        }
    }

    override fun onClickExerciseInfo(id: String) {
        updateState { it.copy(showExerciseInfo = true) }
    }

    override fun onClickRestFinish() {
        updateState {
            it.copy(
                stage = Stage.PERFORM,
            )
        }
    }

    override fun onFinishExercise() {
        if (isLastExercise) {
            updateState {
                it.copy(
                    stage = Stage.FINISH,
                    totalTimeMinutes = totalTimeSoFarMinutes
                )
            }
            return
        }
        updateState {
            it.copy(
                stage = Stage.REST,
                currentStep = nextStep
            )
        }
    }

    override fun onClickForward() {
        updateState {
            it.copy(
                currentStep = nextStep
            )
        }
    }

    override fun onClickBack() {
        updateState {
            it.copy(
                currentStep = previousStep
            )
        }
    }

    override fun onClickSkipRest() {
        updateState {
            it.copy(
                stage = Stage.PERFORM
            )
        }
    }

    override fun onClickNextToAnotherWorkout() {
        sendEffect(PlayWorkoutEffect.NavigateBackToApp)
    }

    override fun onClickFinish() {
        sendEffect(PlayWorkoutEffect.NavigateBack)
    }

    override fun onClinkStayInWorkout() {
        updateState { it.copy(haseCancelWorkoutClicked = false) }
    }

    override fun onClinkEnd() {
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
        const val GET_READY_COUNTER_SECONDS = 10
        const val REST_TIMER_SECONDS = 30
        const val REST_TIMER_TIME_INCREMENT = 15
    }
}