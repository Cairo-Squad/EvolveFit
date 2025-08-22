package com.cairosquad.evolvefit.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.repository.authentication.local.AuthenticationPreferences
import com.cairosquad.evolvefit.ui.navigation.navBar.navigateToNavBarRoute
import com.cairosquad.evolvefit.ui.screen.communityWorkout.CommunityWorkoutScreen
import com.cairosquad.evolvefit.ui.screen.createExercise.CreateExerciseScreen
import com.cairosquad.evolvefit.ui.screen.createWorkout.CreateWorkoutScreen
import com.cairosquad.evolvefit.ui.screen.editProfile.EditProfileScreen
import com.cairosquad.evolvefit.ui.screen.favorites.FavoritesScreen
import com.cairosquad.evolvefit.ui.screen.home.HomeScreen
import com.cairosquad.evolvefit.ui.screen.login.LoginScreen
import com.cairosquad.evolvefit.ui.screen.mealDetails.MealDetailsScreen
import com.cairosquad.evolvefit.ui.screen.mealsHistory.MealsHistoryScreen
import com.cairosquad.evolvefit.ui.screen.more.MoreScreen
import com.cairosquad.evolvefit.ui.screen.nutrition.NutritionScreen
import com.cairosquad.evolvefit.ui.screen.onboarding.OnboardingScreen
import com.cairosquad.evolvefit.ui.screen.playWorkout.PlayWorkoutScreen
import com.cairosquad.evolvefit.ui.screen.register.RegisterScreen
import com.cairosquad.evolvefit.ui.screen.report.ReportScreen
import com.cairosquad.evolvefit.ui.screen.suggestedMeals.SuggestedMealsScreen
import com.cairosquad.evolvefit.ui.screen.workout.WorkoutScreen
import com.cairosquad.evolvefit.ui.screen.workoutDetails.WorkoutDetailsScreen
import com.cairosquad.evolvefit.ui.screen.workoutHistory.WorkoutHistoryScreen
import org.koin.compose.koinInject

@Composable
fun NavigationHost(
    authenticationPreferences: AuthenticationPreferences = koinInject(), // TODO: Replace with isUserLoggedIn: Boolean
    deepLinkRoute: Any? = null
) {

    val isUserLoggedIn = authenticationPreferences.getAccessToken().isNullOrBlank().not()
    val startDestination = if (isUserLoggedIn) NavBarRoute.Home else OnboardingRoute

    val navController = rememberNavController()

    LaunchedEffect(deepLinkRoute, isUserLoggedIn) {
        if (deepLinkRoute != null && isUserLoggedIn) {
            navController.navigate(deepLinkRoute)
        }
    }

    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface),
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
        exitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, delayMillis = 300)) },
        popEnterTransition = { fadeIn(animationSpec = tween(durationMillis = 300)) },
        popExitTransition = { fadeOut(animationSpec = tween(durationMillis = 300, delayMillis = 300)) },
    ) {
        composable<OnboardingRoute> {
            OnboardingScreen(
                navigateToLogin = { navController.navigate(LoginRoute) },
                navigateToRegister = { navController.navigate(RegisterRoute) },
            )
        }

        composable<LoginRoute> {
            LoginScreen(
                navigateToRegister = { navController.navigate(RegisterRoute) },
                navigateBack = navController::popBackStack,
                navigateToApp = {
                    navController.navigate(NavBarRoute.Home) {
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
                    navController.navigate(NavBarRoute.Home) {
                        popUpTo(OnboardingRoute) {
                            inclusive = true
                        }
                    }
                },
                navigateBack = navController::popBackStack
            )
        }

        composable<NavBarRoute.Home> {
            HomeScreen(
                navigateToWorkout = { workoutId ->
                    navController.navigate(
                        WorkoutDetailsRoute(
                            workoutId
                        )
                    )
                },
                onSelectNavBarRoute = navController::navigateToNavBarRoute
            )
        }

        composable<NavBarRoute.Nutrition> {
            NutritionScreen(
                navigateToSuggestedMeals = { navController.navigate(SuggestedMealsRoute) },
                navigateToMealDetails = { mealId -> navController.navigate(MealDetailsRoute(mealId)) },
                navigateToMealsHistory = { navController.navigate(MealsHistoryRoute) },
                onSelectNavBarRoute = navController::navigateToNavBarRoute
            )
        }

        composable<NavBarRoute.Workout> {
            WorkoutScreen(
                navigateToCreateWorkout = { navController.navigate(CreateWorkoutRoute) },
                navigateToCommunityWorkout = { navController.navigate(CommunityWorkoutRoute) },
                navigateToWorkoutDetails = { workoutId ->
                    navController.navigate(
                        WorkoutDetailsRoute(
                            workoutId
                        )
                    )
                },
                onSelectNavBarRoute = navController::navigateToNavBarRoute
            )
        }

        composable<NavBarRoute.Report> {
            ReportScreen(
                navigateToWorkoutHistory = { navController.navigate(WorkoutHistoryRoute) },
                onSelectNavBarRoute = navController::navigateToNavBarRoute
            )
        }

        composable<NavBarRoute.More> {
            MoreScreen(
                navigateToFavorites = { navController.navigate(FavoritesScreenRoute) },
                navigateToNotificationSettings = { },
                onLogout = {
                    navController.navigate(LoginRoute) {
                        popUpTo(OnboardingRoute) { inclusive = true }
                        launchSingleTop = true
                        restoreState = false
                    }
                },
                navigateToEditProfile = { navController.navigate(EditProfileRoute) },
                onSelectNavBarRoute = navController::navigateToNavBarRoute
            )
        }

        composable<CreateWorkoutRoute> {
            CreateWorkoutScreen(
                navigateBack = navController::popBackStack,
                navigateToCreateExercise = { onExerciseCreationSuccess ->
                    navController.navigate(CreateExerciseRoute)
                    navController.saveInSavedState(onExerciseCreationSuccess)
                },
                navigateToWorkOuts = navController::popBackStack,
                navigateToAllExercises = {}
            )
        }

        composable<CreateExerciseRoute> {

            val onExerciseCreationSuccess: (() -> Unit)? = navController.getFromSavedState()

            CreateExerciseScreen(
                navigateBack = navController::popBackStack,
                onExerciseCreationSuccess =
                    onExerciseCreationSuccess?.clearSavedStateAfterInvoke(navController)
            )
        }

        composable<CommunityWorkoutRoute> {
            CommunityWorkoutScreen(
                navigateBack = navController::popBackStack,
                navigateToWorkoutDetails = { workoutId ->
                    navController.navigate(
                        WorkoutDetailsRoute(
                            workoutId
                        )
                    )
                }
            )
        }

        composable<WorkoutDetailsRoute> { backStackEntry ->
            val workoutId = backStackEntry.toRoute<WorkoutDetailsRoute>().workoutId
            WorkoutDetailsScreen(
                workoutId = workoutId,
                navigateBack = navController::popBackStack,
                navigateToPlayWorkout = { navController.navigate(PlayWorkoutRoute(workoutId)) },
                navigateToShareWithCommunity = { },
            )
        }

        composable<PlayWorkoutRoute> { backStackEntry ->
            PlayWorkoutScreen(
                workoutId = backStackEntry.toRoute<WorkoutDetailsRoute>().workoutId,
                navigateBack = navController::popBackStack,
                navigateBackToApp = {
                    navController.popBackStack(
                        route = NavBarRoute.Workout,
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

        composable<WorkoutHistoryRoute> {
            WorkoutHistoryScreen(
                navigateBack = navController::popBackStack
            )
        }
        composable<EditProfileRoute> {
            EditProfileScreen(
                navigateBack = navController::popBackStack
            )
        }
        composable<FavoritesScreenRoute> {
            FavoritesScreen(
                navigateBack = navController::popBackStack
            )
        }
    }
}