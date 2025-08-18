package com.cairosquad.evolvefit.repository.profile.dto

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.model.WeekDay
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate
@Serializable
data class ProfileDto(
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
    val gymEquipments: List<String> = emptyList(),
    @SerialName("workoutDays")
    val workoutDays: List<String?> = emptyList()
) {
    fun toEntity(): Profile {
        return Profile(
            name = name,
            email = email,
            imageUrl = imageUrl,
            height = height.toFloat(),
            weight = weight.toFloat(),
            goal = getFitnessGoal(goal),
            dateOfBirth = LocalDate.parse(birthDate),
            gender = getGender(gender),
            preferredMeasurementStandard = getMeasurementStandard(measurementType),
            preferredLanguage = Language.ENGLISH,
            equipments = gymEquipments.toSet(),
            workoutDays = workoutDays.filterNotNull().map { WeekDay.valueOf(it) }.toSet()
        )
    }
}