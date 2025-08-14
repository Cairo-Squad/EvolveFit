package com.cairosquad.evolvefit.repository.equipment.remot

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.repository.equipment.remot.dto.EquipmentDto

fun Equipment.toDto(): EquipmentDto{
    return EquipmentDto(
        id = this.id,
        name = this.name
    )
}

fun EquipmentDto.toDomain(): Equipment{
    return Equipment(
        id = this.id,
        name = this.name
    )
}