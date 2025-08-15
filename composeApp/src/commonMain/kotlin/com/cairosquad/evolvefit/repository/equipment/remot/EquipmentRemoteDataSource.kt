package com.cairosquad.evolvefit.repository.equipment.remot

import com.cairosquad.evolvefit.repository.equipment.remot.dto.EquipmentDto

interface EquipmentsRemoteDataSource {
    suspend fun getEquipments(): List<EquipmentDto>
}