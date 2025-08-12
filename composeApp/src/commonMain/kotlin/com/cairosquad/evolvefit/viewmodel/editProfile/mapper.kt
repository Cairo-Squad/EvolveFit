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

fun List<Equipment>.toUiStateList(): List<EditProfileScreenState.EquipmentUiState> {
    return map { it.toUiState() }
}

fun List<EditProfileScreenState.EquipmentUiState>.toDomainList(): List<Equipment> {
    return map { it.toDomain() }
}



fun WeekDay.toUiState(): EditProfileScreenState.WeekDayUi {
    return EditProfileScreenState.WeekDayUi.valueOf(this.name)
}

fun EditProfileScreenState.WeekDayUi.toDomain(): WeekDay {
    return WeekDay.valueOf(this.name)
}

fun List<WeekDay>.toUiStateList(): List<EditProfileScreenState.WeekDayUi> {
    return map { it.toUiState() }
}

fun List<EditProfileScreenState.WeekDayUi>.toDomainList(): List<WeekDay> {
    return map { it.toDomain() }
}