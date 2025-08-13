package com.cairosquad.evolvefit.domain.repository

import com.cairosquad.evolvefit.domain.entity.Equipment

interface EquipmentRepository {
    suspend fun getAllEquipments(): Set<Equipment>
    suspend fun getUserEquipments(): Set<Equipment>
    suspend fun editUserEquipments(equipments:Set<Equipment>)
}