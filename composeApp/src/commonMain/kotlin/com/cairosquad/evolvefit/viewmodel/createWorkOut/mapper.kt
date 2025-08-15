package com.cairosquad.evolvefit.viewmodel.createWorkOut

import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.entity.Workout.WorkoutLevel
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import io.github.vinceglb.filekit.path

fun CreateWorkOutScreenState.toDomainWorkout(exercises: List<Exercise> = emptyList()): Workout {
    val imageUrl = when (val img = this.image) {
        is UiImage.ImageUrl -> img.url
        is UiImage.ImageResource -> ""
        is UiImage.ImageFile -> img.platformFile.path
        null -> ""
    }
    return Workout(
        id = "",
        name = name,
        description = description,
        imageUrl = listOfNotNull(imageUrl.takeIf { it.isNotBlank() }).toString(),
        level = goal.toDomain(),
        estimatedTimeInSeconds = exercises.sumOf {
            when (val spec = it.specification) {
                is Exercise.Specification.Time -> spec.timeInSeconds?.toInt() ?: 0
                is Exercise.Specification.Reps -> spec.reps?.toInt() ?: 0
            }
        },
        exercises = exercises
    )
}

private fun CreateWorkOutScreenState.WorkoutLevel.toDomain(): WorkoutLevel{
    return when (this) {
        CreateWorkOutScreenState.WorkoutLevel.BEGINNER -> WorkoutLevel.BEGINNER
        CreateWorkOutScreenState.WorkoutLevel.INTERMEDIATE -> WorkoutLevel.INTERMEDIATE
        CreateWorkOutScreenState.WorkoutLevel.ADVANCED -> WorkoutLevel.ADVANCED
    }

}
