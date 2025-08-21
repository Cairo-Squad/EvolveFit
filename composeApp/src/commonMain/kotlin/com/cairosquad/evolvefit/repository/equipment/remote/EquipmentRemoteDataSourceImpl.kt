package com.cairosquad.evolvefit.repository.equipment.remote

import com.cairosquad.evolvefit.repository.equipment.remote.dto.GymEquipmentDto
import com.cairosquad.evolvefit.repository.execption.callApi
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class EquipmentRemoteDataSourceImpl(private val client: HttpClient) : EquipmentsRemoteDataSource {
    override suspend fun getEquipments(): List<GymEquipmentDto> {
        return callApi { client.get("public/equipments") }
    }
}