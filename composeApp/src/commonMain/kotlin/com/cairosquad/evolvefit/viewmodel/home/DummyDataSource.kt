package com.cairosquad.evolvefit.viewmodel.home

object DummyDataSource {
    val user = HomeScreenState.HomeUserUiState(
        name = "Zyad",
        profilePictureUrl = "https://picsum.photos/200"
    )

    val weeklyProgress = HomeScreenState.WeeklyProgressUiState(
        goal = "Weight loss",
        currentWeight = 64.5f,
        weightUnit = "kg",
        activityPercentage = 30,
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

    val caloriesNutrition = Pair(1650, 2200)
    val waterNutrition = Pair(1.8f, 2.5f)

    val personalizedWorkouts = listOf(
        HomeScreenState.HomeWorkoutUiState(
            id = "1",
            name = "Standard Push-ups",
            imageUrl = "https://picsum.photos/268/172",
            durationInMins = 25,
            type = "Shoulders",
            isSaved = false
        ),

        HomeScreenState.HomeWorkoutUiState(
            id = "2",
            name = "Full Body Blast",
            imageUrl = "https://picsum.photos/268/172",
            durationInMins = 20,
            type = "Body",
            isSaved = false
        ),
    )
}