package com.cairosquad.evolvefit.repository.report.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TotalTimeSpentByDay(
    @SerialName("day")
    val day: String,
    @SerialName("value")
    val timeInSeconds: Long
)