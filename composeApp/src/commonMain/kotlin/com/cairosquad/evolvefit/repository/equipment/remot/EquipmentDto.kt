package com.cairosquad.evolvefit.repository.equipment.remot

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EquipmentDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)
