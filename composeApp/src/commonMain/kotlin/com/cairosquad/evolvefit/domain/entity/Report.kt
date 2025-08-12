package com.cairosquad.evolvefit.domain.entity

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.model.WeekDay

data class Report(
    val timeSpend: Long,
    val totalWorkouts: Int,
    val takenCalories: Int,
    val expectedCalories: Int,
    val waterTakenInLiter: Float,
    val focusedAreas: List<Pair<FocusArea, Int>>,
    val timeSpendPerWeek: List<Pair<WeekDay, Long>>,
    val workoutsPerWeek: List<Pair<WeekDay, Int>>
)
