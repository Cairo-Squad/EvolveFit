package com.cairosquad.evolvefit.repository.equipment

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.repository.EquipmentRepository
import com.cairosquad.evolvefit.repository.equipment.remot.EquipmentsRemoteDataSource
import com.cairosquad.evolvefit.repository.equipment.remot.toDomain
import com.cairosquad.evolvefit.repository.execption.callDataSource

class EquipmentRepositoryImpl(
    private val remote: EquipmentsRemoteDataSource
): EquipmentRepository {
    override suspend fun getAllEquipments(): Set<Equipment> = callDataSource{
        print("EquipmentRepositoryImpl:: getAllEquipments ${remote.getEquipments().map { it.toDomain() }}")
         remote.getEquipments().map { it.toDomain() }.toSet()
    }

    override suspend fun getUserEquipments(): Set<Equipment> {
        return emptySet()
    }

    override suspend fun editUserEquipments() {

    }
}