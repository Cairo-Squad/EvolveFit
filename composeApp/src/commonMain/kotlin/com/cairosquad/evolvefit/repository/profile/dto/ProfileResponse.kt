package com.cairosquad.evolvefit.repository.profile.dto


import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.Language
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    @SerialName("name")
    val name: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("birthDate")
    val birthDate: String? = null,
    @SerialName("gender")
    val gender: String? = null,
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    @SerialName("measurementType")
    val measurementType: String? = null,
    @SerialName("height")
    val height: Double? = null,
    @SerialName("weight")
    val weight: Double? = null,
    @SerialName("goal")
    val goal: String? = null,
    @SerialName("gymEquipments")
    val gymEquipments: List<String?>? = null,
    @SerialName("workoutDays")
    val workoutDays: List<String?>? = null
) {
    fun toEntity(): Profile {
        return Profile(
            name = name ?: "",
            email = email ?: "",
            imageUrl = imageUrl ?: "",
            height = height?.toFloat() ?: 0f,
            weight = weight?.toFloat() ?: 0f,
            goal = getFitnessGoal(goal ?: ""),
            dateOfBirth = kotlinx.datetime.LocalDate.parse(birthDate ?: ""),
            gender = getGender(gender ?: "MALE"),
            preferredMeasurementStandard = getMeasurementStandard(measurementType ?: "METRIC"),
            preferredLanguage = Language.ENGLISH
        )
    }
}