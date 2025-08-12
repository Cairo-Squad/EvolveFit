package com.cairosquad.evolvefit.domain.entity

import com.cairosquad.evolvefit.domain.model.FocusArea

data class Exercise(
    val id: String,
    val name: String,
    val instructions: List<String>,
    val imageUrls: List<String>,
    val equipment: Equipment,
    val specification: Specification,
    val focusAreas: Set<FocusArea>,
    val estimatedTimeSeconds: Int,
) {
    sealed class Specification {
        class Reps(val reps: Int) : Specification()
        class Time(val timeSeconds: Int) : Specification()
    }
}