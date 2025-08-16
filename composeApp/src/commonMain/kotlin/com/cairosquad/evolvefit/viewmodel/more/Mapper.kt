package com.cairosquad.evolvefit.viewmodel.more

import com.cairosquad.evolvefit.domain.entity.Profile
import kotlinx.datetime.toLocalDateTime

fun Profile.toUiState(): MoreScreenState.Profile {
    return MoreScreenState.Profile(
        name = this.name,
        email = this.email,
        image = this.imageUrl,
        height = this.height,
        weight = this.weight,
        age = calculateAge(this.dateOfBirth),
        preferredLanguage = this.preferredLanguage
    )
}

private fun calculateAge(dateOfBirth: kotlinx.datetime.LocalDate): Int {
    val currentDate = kotlinx.datetime.Clock.System.now()
        .toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault()).date
    var age = currentDate.year - dateOfBirth.year
    if (
        currentDate.monthNumber < dateOfBirth.monthNumber ||
        (currentDate.monthNumber == dateOfBirth.monthNumber && currentDate.dayOfMonth < dateOfBirth.dayOfMonth)
    ) {
        age--
    }
    return age
}
