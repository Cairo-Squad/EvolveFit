package com.cairosquad.evolvefit.repository.equipment.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GymEquipmentDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)