package com.cairosquad.evolvefit.Repository

import com.cairosquad.evolvefit.remote.model.EquipmentDto
import com.cairosquad.evolvefit.remote.model.ExerciseDto
import com.cairosquad.evolvefit.remote.model.WorkoutDto
import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.ExerciseDuration
import com.cairosquad.evolvefit.entity.Tool
import com.cairosquad.evolvefit.entity.Workout

fun ExerciseDto.toDomain(): Exercise {
    val duration = when (durationType.uppercase()) {
        "TIME" -> ExerciseDuration.Time(seconds ?: 0)
        "REPS_SETS" -> ExerciseDuration.RepsSets(reps ?: 0, sets ?: 0)
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
        id = id.toString(),
        name = name,
        level = goal,
        description = description,
        imageUrl = image,
        exercises = exercises.map { it.toDomain() }
    )
}

fun EquipmentDto.toDomain(): Tool {
    return Tool(
        id = id,
        name = name
    )
}
