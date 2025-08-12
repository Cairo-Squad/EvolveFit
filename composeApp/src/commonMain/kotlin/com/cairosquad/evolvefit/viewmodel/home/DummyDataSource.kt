package com.cairosquad.evolvefit.viewmodel.home

import com.cairosquad.evolvefit.domain.entity.Profile


object DummyDataSource {
    val user = HomeScreenState.HomeUserUiState(
        name = "Zyad",
        gender = Profile.Gender.MALE,
        profilePictureUrl = "https://picsum.photos/200"
    )

    val weeklyProgress = HomeScreenState.WeeklyProgressUiState(
        goal = "Weight loss",
        currentWeight = 64.5f,
        weightUnit = "kg",
        activityPercentage = 30.toUInt(),
        progressDays = mapOf(
            22 to true,
            23 to true,
            24 to true,
            25 to false,
            26 to false,
            27 to false,
            28 to false,
        )
    )

    val caloriesNutrition = Pair(900.toUInt(), 2200.toUInt())
    val waterNutrition = Pair(1.8f, 2.0f)

    val personalizedWorkouts = listOf(
        HomeScreenState.HomeWorkoutUiState(
            id = "1",
            name = "Standard Push-ups",
            imageUrl = "https://picsum.photos/268/172",
            durationInMins = 25.toUInt(),
            type = "Shoulders",
            isSaved = false
        ),

        HomeScreenState.HomeWorkoutUiState(
            id = "2",
            name = "Full Body Blast",
            imageUrl = "https://picsum.photos/268/172",
            durationInMins = 20.toUInt(),
            type = "Body",
            isSaved = false
        ),
    )
}