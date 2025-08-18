package com.cairosquad.evolvefit.repository.profile.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfilePostDto(
    @SerialName("name")
    val name: String = "",
    @SerialName("email")
    val email: String = "",
    @SerialName("birthDate")
    val birthDate: String = "",
    @SerialName("gender")
    val gender: String = "",
    @SerialName("imageUrl")
    val imageUrl: String = "",
    @SerialName("measurementType")
    val measurementType: String = "",
    @SerialName("height")
    val height: Double = 0.0,
    @SerialName("weight")
    val weight: Double = 0.0,
    @SerialName("goal")
    val goal: String = "",
    @SerialName("gymEquipments")
    val gymEquipments: List<Int> = emptyList(),
    @SerialName("workoutDays")
    val workoutDays: List<String> = emptyList()
)