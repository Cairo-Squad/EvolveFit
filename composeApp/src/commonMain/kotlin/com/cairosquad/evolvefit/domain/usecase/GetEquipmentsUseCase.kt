package com.cairosquad.evolvefit.domain.usecase

import com.cairosquad.evolvefit.domain.AuthRepository
import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.entity.Tool

class GetEquipmentsUseCase(
    private val authRepository: AuthRepository
) {
    suspend  fun getEquipments(): List<Tool> {
        return authRepository.getEquipments().map {
            Tool(id = it.id, name = it.name)
        }
    }
}
