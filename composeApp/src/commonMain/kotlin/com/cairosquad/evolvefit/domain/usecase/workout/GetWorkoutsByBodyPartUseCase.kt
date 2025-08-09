package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.domain.model.BodyPart
import com.cairosquad.evolvefit.domain.model.WorkoutModel

class GetWorkoutsByBodyPartUseCase(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(bodyPart: BodyPart): List<WorkoutModel> =
        repository.getWorkoutsByBodyPart(bodyPart)
}