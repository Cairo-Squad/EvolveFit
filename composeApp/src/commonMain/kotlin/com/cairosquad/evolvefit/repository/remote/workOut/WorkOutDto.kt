package com.cairosquad.evolvefit.repository.remote.workOut

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseDto(
    val id: Long,
    val name: String,
    val imageUrl: String?,
    val equipmentIds: List<Long>,
    val measurementType: String,
    val measurementValue: Int,
    val focusAreas: List<String>,
    val description: String
)