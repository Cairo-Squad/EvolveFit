package com.cairosquad.evolvefit.repository.exercise.remot.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExerciseDto(
    @SerialName("name")
    val name: String? = null,

    @SerialName("gymEquipments")
    val gymEquipments: List<Int> = emptyList(),

    @SerialName("instructions")
    val instructions: List<String> = emptyList(),

    @SerialName("focusArea")
    val focusArea: List<String> = emptyList(),

    @SerialName("exerciseType")
    val exerciseType: String? = null,

    @SerialName("reps")
    val reps: Int? = null,

    @SerialName("durationSeconds")
    val durationSeconds: Int? = null
)