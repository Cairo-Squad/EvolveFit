package com.cairosquad.evolvefit.domain.usecase.utils

import com.cairosquad.evolvefit.domain.exception.InvalidNumberFormatException
import com.cairosquad.evolvefit.domain.model.FieldType

fun validateNumberInput(input: String, field: FieldType) {
    if (!input.matches(Regex("^\\d+(\\.\\d+)?\$"))) {
        throw InvalidNumberFormatException(field)
    }
}