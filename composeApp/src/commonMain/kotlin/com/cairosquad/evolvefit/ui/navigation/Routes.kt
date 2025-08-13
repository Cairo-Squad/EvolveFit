package com.cairosquad.evolvefit.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object OnboardingRoute
@Serializable
data object LoginRoute
@Serializable
data object RegisterRoute
@Serializable
data object AppRoute
@Serializable
data object CreateWorkoutRoute
@Serializable
data object CreateExerciseRoute
@Serializable
data object CommunityWorkoutRoute
@Serializable
data class WorkoutDetailsRoute(
    val workoutId: String
)
@Serializable
data class PlayWorkoutRoute(
    val workoutId: String
)
@Serializable
data object SuggestedMealsRoute
@Serializable
data class MealDetailsRoute(
    val mealId: String
)
@Serializable
data object MealsHistoryRoute

@Serializable
data object WorkoutHistoryRoute