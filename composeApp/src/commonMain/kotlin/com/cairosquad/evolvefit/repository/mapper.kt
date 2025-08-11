package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.Workout
import com.cairosquad.evolvefit.remote.model.ExerciseDto
import com.cairosquad.evolvefit.remote.model.WorkoutDto
import com.cairosquad.evolvefit.entity.ExerciseDuration

fun ExerciseDto.toDomain(): Exercise {
    val duration = when (durationType.lowercase()) {
        "time" -> ExerciseDuration.Time(seconds ?: 0)
        "reps_sets" -> ExerciseDuration.RepsSets(
            reps = reps ?: 0,
            sets = sets ?: 0
        )
        else -> throw IllegalArgumentException("Unknown duration type: $durationType")
    }

    return Exercise(
        id = id,
        name = name,
        imageUrl = image,
        exerciseDuration = duration
    )
}

fun WorkoutDto.toDomain(): Workout {
    return Workout(
        id = id ?: "",
        name = name,
        level = goal,
        description = description,
        imageUrl = image,
        exercisesId = exercisesId
    )
}

