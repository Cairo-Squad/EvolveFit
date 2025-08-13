package com.cairosquad.evolvefit.viewmodel.editProfile

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.domain.model.MeasurementStandard
import com.cairosquad.evolvefit.domain.model.WeekDay

fun Profile.toUiState() = EditProfileScreenState.ProfileUiState(
    fullName = name,
    email = email,
    dateOfBirth = dateOfBirth,
    gender = gender.name,
    height = height,
    weight = weight,
    mainGoal = goal.name,
    imageUrl = imageUrl,
    preferredMeasurementStandard = preferredMeasurementStandard.name,
)
fun EditProfileScreenState.ProfileUiState.toDomain(existingProfile: Profile) = existingProfile.copy(
    name = fullName,
    email = email,
    dateOfBirth = dateOfBirth ?: existingProfile.dateOfBirth,
    gender = Profile.Gender.valueOf(gender),
    preferredMeasurementStandard = MeasurementStandard.valueOf(preferredMeasurementStandard),
    height = height,
    weight = weight,
    goal = Profile.FitnessGoal.valueOf(mainGoal),
    imageUrl = imageUrl
)
fun Equipment.toUiState(): EditProfileScreenState.EquipmentUiState {
    return EditProfileScreenState.EquipmentUiState(
        id = this.id,
        name = this.name
    )
}

fun EditProfileScreenState.EquipmentUiState.toDomain(): Equipment {
    return Equipment(
        id = this.id,
        name = this.name
    )
}

fun Set<Equipment>.toEquipmentUiStateSet(): Set<EditProfileScreenState.EquipmentUiState> {
    return map { it.toUiState() }
}

fun Set<EditProfileScreenState.EquipmentUiState>.toEquipmentDomainSet():Set<Equipment> {
    return map { it.toDomain() }
}



fun WeekDay.toUiState(): EditProfileScreenState.WeekDayUiState {
    return EditProfileScreenState.WeekDayUiState.valueOf(this.name)
}

fun EditProfileScreenState.WeekDayUiState.toDomain(): WeekDay {
    return WeekDay.valueOf(this.name)
}

fun Set<WeekDay>.toWeekDayUiStateSet():Set<EditProfileScreenState.WeekDayUiState> {
    return map { it.toUiState() }
}

fun Set<EditProfileScreenState.WeekDayUiState>.toWeekDayDomainSet(): Set<WeekDay> {
    return map { it.toDomain() }
}