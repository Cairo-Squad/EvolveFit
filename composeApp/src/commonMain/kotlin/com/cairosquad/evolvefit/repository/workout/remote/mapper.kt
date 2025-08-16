package com.cairosquad.evolvefit.repository.workout.remote

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.repository.workout.remote.dto.CreateWorkoutRequest
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDto


fun Workout.toDto(): WorkoutDto {
    return WorkoutDto(
        durationSeconds = estimatedTimeInSeconds,
        imageUrl = imageUrl,
        name = name,
        id = id,
        focusArea = focusAreas.toList()
    )
}

fun Workout.toCreateRequest(): CreateWorkoutRequest {
    return CreateWorkoutRequest(
        name = this.name,
        description = this.description,
        level = this.level.name,
        createdBy = "USER",
        exercises = this.exercises.map { it.id }
    )
}

fun WorkoutDto.toDomain(): WorkoutSuggested {
    return WorkoutSuggested(
        id = id,
        name = name,
        durationSeconds = durationSeconds,
        imageUrl = imageUrl,
        focusArea = focusArea
    )
}
fun WorkoutDetailsDto.toDomain(): Workout {
    return Workout(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        level = when (level.uppercase()) {
            "BEGINNER" -> Workout.WorkoutLevel.BEGINNER
            "INTERMEDIATE" -> Workout.WorkoutLevel.INTERMEDIATE
            "ADVANCED" -> Workout.WorkoutLevel.ADVANCED
            else -> Workout.WorkoutLevel.BEGINNER
        },
        estimatedTimeInSeconds = durationSeconds,
        exercises = exercises.map { it.toDomain() }
    )
}



