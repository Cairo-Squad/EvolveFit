package com.cairosquad.evolvefit.domain.usecase

import com.cairosquad.evolvefit.entity.Tool
import com.cairosquad.evolvefit.repository.remote.auth.EquipmentDto

fun EquipmentDto.toDomain(): Tool {
    return Tool(
        id = this.id,
        name = this.name
    )
}

fun Tool.toDto(): EquipmentDto {
    return EquipmentDto(
        id = this.id,
        name = this.name
    )
}
