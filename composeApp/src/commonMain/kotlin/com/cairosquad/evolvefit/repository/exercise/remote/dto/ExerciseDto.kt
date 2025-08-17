package com.cairosquad.evolvefit.repository.exercise.remote.dto

import com.cairosquad.evolvefit.repository.equipment.remot.dto.EquipmentDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseDto(
    @SerialName("name")
    val name: String = "",

    @SerialName("gymEquipments")
    val gymEquipments: List<Int> = emptyList(),

    @SerialName("instructions")
    val instructions: List<String> = emptyList(),

    @SerialName("focusArea")
    val focusArea: List<String> = emptyList(),

    @SerialName("exerciseType")
    val exerciseType: String = "",

    @SerialName("reps")
    val reps: Int = 0,

    @SerialName("durationSeconds")
    val durationSeconds: Int = 0
)

@Serializable
data class ExerciseResponseDto(
    @SerialName("id")
    val id: String = "",

    @SerialName("name")
    val name: String = "",

    @SerialName("instructions")
    val instructions: List<String> = emptyList(),

    @SerialName("images")
    val images: List<String> = emptyList(),

    @SerialName("gymEquipments")
    val gymEquipments: List<EquipmentDto> = emptyList(),

    @SerialName("focusArea")
    val focusArea: List<String> = emptyList(),

    @SerialName("exerciseType")
    val exerciseType: String = "",

    @SerialName("durationSeconds")
    val durationSeconds: Int? = null,

    @SerialName("reps")
    val reps: Int? = null
)


