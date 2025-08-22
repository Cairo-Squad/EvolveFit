package com.cairosquad.evolvefit.repository.profile.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileRequest(
    @SerialName("fullName")
    val name: String = "",
    @SerialName("birthDate")
    val birthDate: String = "",
    @SerialName("gender")
    val gender: String = "",
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