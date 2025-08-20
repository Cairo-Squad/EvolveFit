package com.cairosquad.evolvefit.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object OnboardingRoute

@Serializable
data object LoginRoute

@Serializable
data object RegisterRoute

//@Serializable
//data object AppRoute

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

@Serializable
data object EditProfileRoute

@Serializable
data object FavoritesScreenRoute

@Serializable
sealed class NavBarRoute(val index: Int) {
    @Serializable
    data object Home: NavBarRoute(0)
    @Serializable
    data object Nutrition: NavBarRoute(1)
    @Serializable
    data object Workout: NavBarRoute(2)
    @Serializable
    data object Report: NavBarRoute(3)
    @Serializable
    data object More: NavBarRoute(4)
    companion object {
        fun fromIndex(index: Int): NavBarRoute {
            return when (index) {
                0 -> Home
                1 -> Nutrition
                2 -> Workout
                3 -> Report
                4 -> More
                else -> throw IllegalArgumentException("Invalid index")
            }
        }
    }
}