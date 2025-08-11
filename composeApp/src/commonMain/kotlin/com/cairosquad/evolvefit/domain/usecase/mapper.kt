package com.cairosquad.evolvefit.domain.usecase

import com.cairosquad.evolvefit.entity.Exercise
import com.cairosquad.evolvefit.entity.Tool
import com.cairosquad.evolvefit.repository.remote.auth.EquipmentDto
import com.cairosquad.evolvefit.repository.remote.workOut.ExerciseDto

fun EquipmentDto.toDomain(): Tool {
    return Tool(
        id = this.id.toLong(),
        name = this.name
    )
}

fun Tool.toDto(): EquipmentDto {
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
