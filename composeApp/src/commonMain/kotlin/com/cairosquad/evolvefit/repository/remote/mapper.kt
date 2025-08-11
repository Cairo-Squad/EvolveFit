package com.cairosquad.evolvefit.repository.remote

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.repository.remote.auth.EquipmentDto
import com.cairosquad.evolvefit.repository.remote.workout.ExerciseDto

fun EquipmentDto.toDomain(): Equipment {
    return Equipment(
        id = this.id.toLong(),
        name = this.name
    )
}

fun Equipment.toDto(): EquipmentDto {
    return EquipmentDto(
        id = this.id.toString(),
        name = this.name
    )
}

fun Exercise.toDto(): ExerciseDto {
    return ExerciseDto(
        name = this.name,
        imageUrl = this.imageUrl,
        equipmentIds = this.equipmentIds,
        measurementType = this.measurementType.name,
        measurementValue = this.measurementValue,
        focusAreas = this.focusAreas.map { it.name },
        description = this.description,
        id = this.id,
    )
}
