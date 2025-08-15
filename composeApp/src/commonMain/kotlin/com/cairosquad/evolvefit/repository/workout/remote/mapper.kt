package com.cairosquad.evolvefit.repository.workout.remote

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.repository.workout.remote.remote.WorkoutDto

fun Workout.toDto(): WorkoutDto {
    return WorkoutDto(
        durationSeconds = estimatedTimeInSeconds,
        imageUrl = imageUrl,
        name = name,
        workoutId = id,
        focusArea = listOf()
    )
}

fun WorkoutDto.toDomain():Workout{
    return Workout(
        id = workoutId,
        name = name,
        description = "",
        imageUrl = imageUrl,
        level = Workout.WorkoutLevel.BEGINNER,
        estimatedTimeInSeconds = durationSeconds,
        exercises = emptyList()
    )
}