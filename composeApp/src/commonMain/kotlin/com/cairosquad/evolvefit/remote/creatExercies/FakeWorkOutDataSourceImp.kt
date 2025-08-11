package com.cairosquad.evolvefit.remote.creatExercies

import com.cairosquad.evolvefit.repository.remote.auth.EquipmentDto
import com.cairosquad.evolvefit.repository.remote.workOut.ExerciseDto
import com.cairosquad.evolvefit.repository.remote.workOut.WorkOutDataSource
import io.ktor.client.HttpClient

class FakeWorkOutDataSourceImp(private val client: HttpClient) : WorkOutDataSource {
    override suspend fun getEquipments(): List<EquipmentDto> {
        return emptyList()
    }

    override suspend fun createExercise(exercise: ExerciseDto) {

    }
}