package com.cairosquad.evolvefit.repository.remote

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.repository.remote.authentication.EquipmentDto

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
