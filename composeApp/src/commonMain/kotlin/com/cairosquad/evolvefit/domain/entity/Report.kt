package com.cairosquad.evolvefit.domain.entity

import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.model.WeekDay

data class Report(
    val timeSpentInMilliSeconds: Long,
    val totalWorkouts: Int,
    val takenCaloriesInKcal: Int,
    val expectedCalories: Int,
    val waterTakenInLiter: Float,
    val focusedAreas: List<Pair<FocusArea, Int>>,
    val timeSpentPerWeek: List<Pair<WeekDay, Long>>,
    val workoutsPerWeek: List<Pair<WeekDay, Int>>
)
