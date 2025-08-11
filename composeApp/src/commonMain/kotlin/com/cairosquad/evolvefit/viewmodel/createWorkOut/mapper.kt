package com.cairosquad.evolvefit.viewmodel.createWorkOut

import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.ExerciseDuration
import com.cairosquad.evolvefit.entity.Workout
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage

fun Exercise.toUiExercise(): WorkOutScreenState.UiExercise {
    return when (exerciseDuration) {
        is ExerciseDuration.Time -> WorkOutScreenState.UiExercise(
            id = id,
            name = name,
            imageUrl = imageUrl,
            duration = WorkOutScreenState.UiExerciseDuration.Time(
                seconds = (exerciseDuration as ExerciseDuration.Time).seconds
            )
        )
        is ExerciseDuration.RepsSets -> WorkOutScreenState.UiExercise(
            id = id,
            name = name,
            imageUrl = imageUrl,
            duration = WorkOutScreenState.UiExerciseDuration.RepsSets(
                reps = (exerciseDuration as ExerciseDuration.RepsSets).reps,
                sets = (exerciseDuration as ExerciseDuration.RepsSets).sets
            )
        )
    }
}

fun Workout.toUiWorkout(): WorkOutScreenState.UiWorkout {
    return WorkOutScreenState.UiWorkout(
        id = id.toString(),
        name = name,
        level = level,
        description = description,
        imageUrl = imageUrl,
        exercisesId = listOf<Long>()
    )
}

fun WorkOutScreenState.toDomainWorkout(): Workout {
    return Workout(
        id = "",
        name = this.name,
        level = this.goal.toString(),
        description = this.description,
        imageUrl = when (val img = this.image) {
            is UiImage.ImageUrl -> img.url.ifBlank { null }
            is UiImage.ImageResource -> null
            else -> null
        },
        exercisesId = this.selectedExerciseIds,
    )
}
