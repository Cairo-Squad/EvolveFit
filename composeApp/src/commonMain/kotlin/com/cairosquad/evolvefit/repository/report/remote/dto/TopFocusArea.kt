package com.cairosquad.evolvefit.repository.report.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopFocusArea(
    @SerialName("area")
    val area: String,
    @SerialName("percentage")
    val percentage: Double
)