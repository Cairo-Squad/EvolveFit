package com.cairosquad.evolvefit.repository.authentication.remote

import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.WeekDay
import com.cairosquad.evolvefit.repository.authentication.remote.dto.RegisterRequest
import com.cairosquad.evolvefit.repository.utils.toRemoteDto

fun Profile.toRegisterRequest(
    password: String,
    availableEquipment: Set<Int>,
    workoutDays: Set<WeekDay>
): RegisterRequest {
    return RegisterRequest(
        fullName = name,
        email = email,
        birthdate = dateOfBirth.toRemoteDto(),
        password = password,
        gender = gender.name,
        measurementType = preferredMeasurementStandard.name,
        height = height,
        weight = weight,
        goal = goal.name,
        gymEquipments = availableEquipment.toList(),
        workoutDays = workoutDays.map { it.name }
    )
}