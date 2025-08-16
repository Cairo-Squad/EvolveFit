package com.cairosquad.evolvefit.repository.equipment.remote

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.repository.equipment.remote.dto.GymEquipmentDto

fun GymEquipmentDto.toDomain(): Equipment {
    return Equipment(
        id = id,
        name = name
    )
}