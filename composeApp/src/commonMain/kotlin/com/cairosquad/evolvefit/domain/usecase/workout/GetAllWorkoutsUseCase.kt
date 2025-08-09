package com.cairosquad.evolvefit.domain.usecase.workout

import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.domain.model.WorkoutModel

class GetAllWorkoutsUseCase(
    private val repository: WorkoutRepository
) {
    suspend operator fun invoke(): List<WorkoutModel> = repository.getAllWorkouts()
}