package com.cairosquad.evolvefit.domain.usecase.equipment

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.repository.EquipmentRepository

class ManageEquipmentUseCase(
    private val equipmentRepository: EquipmentRepository
) {
    suspend fun getAllEquipments(): Set<Equipment> {
        return equipmentRepository.getAllEquipments()
    }
    suspend fun getUserEquipments(): Set<Equipment> {
        return equipmentRepository.getUserEquipments()
    }
}