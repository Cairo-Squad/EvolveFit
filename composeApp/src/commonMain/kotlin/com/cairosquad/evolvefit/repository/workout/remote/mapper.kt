package com.cairosquad.evolvefit.repository.workout.remote

import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.repository.workout.remote.dto.CreateWorkoutRequest
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDto
import com.cairosquad.evolvefit.repository.workout.remote.remote.WorkoutDto
import com.cairosquad.evolvefit.domain.entity.WorkoutSuggested
import com.cairosquad.evolvefit.repository.exercise.remote.toDomain
import com.cairosquad.evolvefit.repository.workout.remote.dto.CreateWorkoutRequest
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDetailsDto
import com.cairosquad.evolvefit.repository.workout.remote.dto.WorkoutDto


fun Workout.toDto(): WorkoutDto {
    return WorkoutDto(
        durationSeconds = estimatedTimeInSeconds,
        imageUrl = imageUrl,
        name = name,
        workoutId = id,
        focusArea = focusAreas.map { it.name }
    )
}

fun WorkoutDto.toDomain(): Workout {
    return Workout(
        id = workoutId,
        name = name,
        description = "",
        focusAreas = focusArea.mapNotNull {
            runCatching { FocusArea.valueOf(it) }.getOrNull()
        }.toSet(),
        imageUrl = imageUrl,
        level = Workout.WorkoutLevel.BEGINNER,
        estimatedTimeInSeconds = durationSeconds,
        exercises = emptyList()
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