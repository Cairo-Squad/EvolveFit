package com.cairosquad.evolvefit.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.app.AppScreen
import com.cairosquad.evolvefit.ui.screen.communityWorkout.CommunityWorkoutScreen
import com.cairosquad.evolvefit.ui.screen.createExercise.CreateExerciseScreen
import com.cairosquad.evolvefit.ui.screen.createWorkout.CreateWorkoutScreen
import com.cairosquad.evolvefit.ui.screen.login.LoginScreen
import com.cairosquad.evolvefit.ui.screen.mealDetails.MealDetailsScreen
import com.cairosquad.evolvefit.ui.screen.mealsHistory.MealsHistoryScreen
import com.cairosquad.evolvefit.ui.screen.onBoarding.OnboardingScreen
import com.cairosquad.evolvefit.ui.screen.playWorkout.PlayWorkoutScreen
import com.cairosquad.evolvefit.ui.screen.register.RegisterScreen
import com.cairosquad.evolvefit.ui.screen.suggestedMeals.SuggestedMealsScreen
import com.cairosquad.evolvefit.ui.screen.workoutDetails.WorkoutDetailsScreen

@Composable
fun NavigationHost() {

    val navController = rememberNavController()

    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface),
        navController = navController,
        startDestination = OnboardingRoute
    ) {
        composable<OnboardingRoute> {
            OnboardingScreen(
                navController = navController
            )
        }

        composable<LoginRoute> {
            LoginScreen(
                navigateToApp = {
                    navController.navigate(AppRoute) {
                        popUpTo(OnboardingRoute) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        composable<RegisterRoute> {
            RegisterScreen(
                navigateToApp = {
                    navController.navigate(AppRoute) {
                        popUpTo(OnboardingRoute) {
                            inclusive = true
                        }
                    }
                },
                navigateBack = navController::popBackStack
            )
        }

        composable<AppRoute> {
            AppScreen(
                navigateToCreateWorkout = { navController.navigate(CreateWorkoutRoute) },
                navigateToCommunityWorkout = { navController.navigate(CommunityWorkoutRoute) },
                navigateToWorkoutDetails = { workoutId -> navController.navigate(WorkoutDetailsRoute(workoutId)) },
                navigateToSuggestedMeals = { navController.navigate(SuggestedMealsRoute) },
                navigateToMealDetails = { mealId -> navController.navigate(MealDetailsRoute(mealId)) },
                navigateToMealsHistory = { navController.navigate(MealsHistoryRoute) }
            )
        }

        composable<CreateWorkoutRoute> {
            CreateWorkoutScreen(
                navigateBack = navController::popBackStack,
                navigateToCreateExercise = { navController.navigate(CreateExerciseRoute) }
            )
        }

        composable<CreateExerciseRoute> {
            CreateExerciseScreen(
                navigateBack = navController::popBackStack
            )
        }

        composable<CommunityWorkoutRoute> {
            CommunityWorkoutScreen(
                navigateBack = navController::popBackStack,
                navigateToWorkoutDetails = { workoutId -> navController.navigate(WorkoutDetailsRoute(workoutId)) },
                navigateToPlayWorkout = { workoutId -> navController.navigate(PlayWorkoutRoute(workoutId)) }
            )
        }

        composable<WorkoutDetailsRoute> { backStackEntry ->
            val workoutId = backStackEntry.toRoute<WorkoutDetailsRoute>().workoutId
            WorkoutDetailsScreen(
                workoutId = workoutId,
                navigateBack = navController::popBackStack,
                navigateToPlayWorkout = { navController.navigate(PlayWorkoutRoute(workoutId)) }
            )
        }

        composable<PlayWorkoutRoute> { backStackEntry ->
            PlayWorkoutScreen(
                workoutId = backStackEntry.toRoute<WorkoutDetailsRoute>().workoutId,
                navigateBack = navController::popBackStack,
                navigateBackToApp = {
                    navController.popBackStack(
                        route = AppRoute,
                        inclusive = false
                    )
                }
            )
        }

        composable<SuggestedMealsRoute> {
            SuggestedMealsScreen(
                navigateBack = navController::popBackStack,
                navigateToMealDetails = { mealId -> navController.navigate(MealDetailsRoute(mealId)) }
            )
        }

        composable<MealDetailsRoute> { backStackEntry ->
            MealDetailsScreen(
                mealId = backStackEntry.toRoute<MealDetailsRoute>().mealId,
                navigateBack = navController::popBackStack
            )
        }

        composable<MealsHistoryRoute> {
            MealsHistoryScreen(
                navigateBack = navController::popBackStack
            )
        }
    }
}