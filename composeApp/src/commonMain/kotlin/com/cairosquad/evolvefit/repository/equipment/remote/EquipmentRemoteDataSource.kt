package com.cairosquad.evolvefit.repository.equipment.remote

import com.cairosquad.evolvefit.repository.equipment.remote.dto.GymEquipmentDto

interface EquipmentsRemoteDataSource {
    suspend fun getEquipments(): List<GymEquipmentDto>
}