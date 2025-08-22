package com.cairosquad.evolvefit.repository.equipment

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.repository.EquipmentRepository
import com.cairosquad.evolvefit.repository.equipment.remote.EquipmentsRemoteDataSource
import com.cairosquad.evolvefit.repository.equipment.remote.toDomain
import com.cairosquad.evolvefit.repository.execption.callDataSource

class EquipmentRepositoryImpl(
    private val remote: EquipmentsRemoteDataSource
): EquipmentRepository {
    override suspend fun getAllEquipments(): Set<Equipment> = callDataSource{
         remote.getEquipments().map { it.toDomain() }.toSet()
    }

    override suspend fun getUserEquipments(): Set<Equipment> {
        return emptySet()
    }

    override suspend fun editUserEquipments(equipments:Set<Equipment>) {}
}