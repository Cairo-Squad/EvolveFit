package com.cairosquad.evolvefit.repository.equipment.remot

import com.cairosquad.evolvefit.repository.equipment.remot.dto.EquipmentDto
import com.cairosquad.evolvefit.repository.execption.callApi
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class EquipmentRemoteDataSourceImpl(private val client: HttpClient) : EquipmentsRemoteDataSource {
    override suspend fun getEquipments(): List<EquipmentDto> {
        return callApi { client.get("public/equipments") }
    }
}