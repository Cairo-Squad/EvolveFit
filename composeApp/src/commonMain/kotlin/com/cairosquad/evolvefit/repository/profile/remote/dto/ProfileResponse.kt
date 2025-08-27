package com.cairosquad.evolvefit.repository.profile.remote.dto

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.repository.equipment.remote.dto.GymEquipmentDto
import com.cairosquad.evolvefit.repository.equipment.remote.toDomain
import com.cairosquad.evolvefit.repository.profile.remote.getFitnessGoal
import com.cairosquad.evolvefit.repository.profile.remote.getGender
import com.cairosquad.evolvefit.repository.profile.remote.getMeasurementStandard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class ProfileResponse(
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
    val gymEquipments: List<GymEquipmentDto> = emptyList(),
    @SerialName("workoutDays")
    val workoutDays: List<String> = emptyList()
) {
    fun toEntity(): Profile {
        val entity =  Profile(
            name = name,
            email = email,
            imageUrl = imageUrl,
            height = height
                .toFloat()
                .let {
                    if (measurementType == MeasurementStandard.IMPERIAL.name) it / CM_PER_FT else it
                },
            weight = weight
                .toFloat()
                .let {
                    if (measurementType == MeasurementStandard.IMPERIAL.name) it / KG_PER_LB else it
                },
            goal = getFitnessGoal(goal),
            dateOfBirth = LocalDate.parse(birthDate),
            gender = getGender(gender),
            preferredMeasurementStandard = getMeasurementStandard(measurementType),
            equipments = gymEquipments.map{it.toDomain()}.toSet(),
            workoutDays = workoutDays.map { WeekDay.valueOf(it) }.toSet()
        )
        return entity
    }
}

private const val CM_PER_FT = 30.48f
private const val KG_PER_LB = 0.453592f