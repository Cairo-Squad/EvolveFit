package com.cairosquad.evolvefit.viewmodel.playWorkout

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.playWorkout.PlayWorkoutScreenState.Stage
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
class PlayWorkoutViewModel(
    workoutId: String
) : PlayWorkoutInteractionListener,
    BaseViewModel<PlayWorkoutScreenState, PlayWorkoutEffect>(PlayWorkoutScreenState()) {

    private var startTimeMilli: Long = 0

    init {
        loadData(workoutId)
    }

    private fun loadData(workoutID: String) {
        updateState {
            it.copy(
                workout = PlayWorkoutScreenState.WorkoutUiState(
                    name = "Upper Body",
                    imageUrl = "https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg",
                    level = PlayWorkoutScreenState.WorkoutLevelUiState.BEGINNER,
                    exercises = listOf(
                        PlayWorkoutScreenState.ExerciseUiState(
                            name = "Push-up",
                            exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                            imageUrls = listOf("https://images.ctfassets.net/6ilvqec50fal/JdeBsAsNI2XepyM4IDL1U/ef2c96e26f7c3af5bce6db428cd1237f/Screenshot_2024-03-21_at_12.36.05_PM.png")
                        ),
                        PlayWorkoutScreenState.ExerciseUiState(
                            name = "Running - Treadmill",
                            exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                            imageUrls = listOf("https://mrtreadmill.com.au/wp-content/uploads/shutterstock_1495412588-1.jpg")
                        ),
                        PlayWorkoutScreenState.ExerciseUiState(
                            name = "Push-up",
                            exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                            imageUrls = listOf("https://images.ctfassets.net/6ilvqec50fal/JdeBsAsNI2XepyM4IDL1U/ef2c96e26f7c3af5bce6db428cd1237f/Screenshot_2024-03-21_at_12.36.05_PM.png")
                        ),
                        PlayWorkoutScreenState.ExerciseUiState(
                            name = "Running - Treadmill",
                            exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                            imageUrls = listOf("https://mrtreadmill.com.au/wp-content/uploads/shutterstock_1495412588-1.jpg")
                        ),
                        PlayWorkoutScreenState.ExerciseUiState(
                            name = "Push-up",
                            exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Reps(10),
                            imageUrls = listOf("https://images.ctfassets.net/6ilvqec50fal/JdeBsAsNI2XepyM4IDL1U/ef2c96e26f7c3af5bce6db428cd1237f/Screenshot_2024-03-21_at_12.36.05_PM.png")
                        ),
                        PlayWorkoutScreenState.ExerciseUiState(
                            name = "Running - Treadmill",
                            exerciseSpec = PlayWorkoutScreenState.ExerciseSpecUiState.Time(30),
                            imageUrls = listOf("https://mrtreadmill.com.au/wp-content/uploads/shutterstock_1495412588-1.jpg")
                        ),
                    )
                ),
                stage = Stage.GET_READY
            )
        }
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