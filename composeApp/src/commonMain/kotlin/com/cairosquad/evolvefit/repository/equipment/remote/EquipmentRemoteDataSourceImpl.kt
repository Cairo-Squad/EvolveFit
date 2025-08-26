package com.cairosquad.evolvefit.repository.equipment.remote

import com.cairosquad.evolvefit.repository.equipment.remote.dto.GymEquipmentDto
import com.cairosquad.evolvefit.repository.execption.callApi
import com.cairosquad.evolvefit.repository.utils.HttpClientHolder

class EquipmentRemoteDataSourceImpl(private val client: HttpClientHolder) : EquipmentsRemoteDataSource {
    override suspend fun getEquipments(): List<GymEquipmentDto> {
        return callApi { client.get("public/equipments") }
    }
}