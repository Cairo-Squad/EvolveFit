package com.cairosquad.evolvefit.repository.equipment

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.repository.EquipmentRepository

class EquipmentRepositoryImpl: EquipmentRepository {
    override suspend fun getAllEquipments(): Set<Equipment> {
        return emptySet()
    }

    override suspend fun getUserEquipments(): Set<Equipment> {
        return emptySet()
    }

    override suspend fun editUserEquipments(equipments:Set<Equipment>) {

    }
}