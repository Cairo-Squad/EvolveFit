package com.cairosquad.evolvefit.remote.dto.nutrition

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWaterIntakeRequest(
    @SerialName("waterIntake")
    val waterIntake: Float
)