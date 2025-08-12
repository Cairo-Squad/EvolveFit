package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.WorkOutRepository
import com.cairosquad.evolvefit.domain.usecase.toDomain
import com.cairosquad.evolvefit.domain.usecase.toDto
import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.Tool
import com.cairosquad.evolvefit.remote.utils.safeApiCall
import com.cairosquad.evolvefit.repository.remote.workOut.WorkOutDataSource

class WorkOutRepositoryImpl(
    private val workOutDataSource: WorkOutDataSource,
) : WorkOutRepository {

    override suspend fun getEquipments(): List<Tool>  {
        return safeApiCall {  workOutDataSource.getEquipments().map { it.toDomain() } }
    }

    override suspend fun createExercise(exercise: Exercise) = safeApiCall{
        workOutDataSource.createExercise(exercise.toDto())
    }
}