package com.cairosquad.evolvefit.ui.navigation.navBar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.cairosquad.evolvefit.ui.navigation.CommunityWorkoutRoute
import com.cairosquad.evolvefit.ui.navigation.CreateWorkoutRoute
import com.cairosquad.evolvefit.ui.navigation.EditProfileRoute
import com.cairosquad.evolvefit.ui.navigation.FavoritesScreenRoute
import com.cairosquad.evolvefit.ui.navigation.LoginRoute
import com.cairosquad.evolvefit.ui.navigation.MealDetailsRoute
import com.cairosquad.evolvefit.ui.navigation.MealsHistoryRoute
import com.cairosquad.evolvefit.ui.navigation.NavBarRoute
import com.cairosquad.evolvefit.ui.navigation.SuggestedMealsRoute
import com.cairosquad.evolvefit.ui.navigation.WorkoutDetailsRoute
import com.cairosquad.evolvefit.ui.navigation.WorkoutHistoryRoute
import com.cairosquad.evolvefit.ui.navigation.saveInSavedState
import com.cairosquad.evolvefit.ui.screen.home.HomeScreen
import com.cairosquad.evolvefit.ui.screen.more.MoreScreen
import com.cairosquad.evolvefit.ui.screen.nutrition.NutritionScreen
import com.cairosquad.evolvefit.ui.screen.report.ReportScreen
import com.cairosquad.evolvefit.ui.screen.workout.WorkoutScreen
import com.cairosquad.evolvefit.ui.util.PlatformBackHandler
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState

@Composable
fun NavBarScreesContainer(
    navController: NavHostController,
    onLanguageChange: (String) -> Unit,
    onThemeChange: (MoreScreenState.Theme) -> Unit,
) {
    var navBarRouteIndex by rememberSaveable { mutableStateOf(NavBarRoute.Home.index) }

    PlatformBackHandler {
        if (navBarRouteIndex == 0) navController.popBackStack()
        else navBarRouteIndex--
    }

    AnimatedContent(
        targetState = NavBarRoute.fromIndex(navBarRouteIndex),
        transitionSpec = {
            fadeIn(animationSpec = tween(220))
                .togetherWith(fadeOut(animationSpec = tween(90, delayMillis = 220)))
        }
    ) { navBarRoute ->
        when (navBarRoute) {
            NavBarRoute.Home -> HomeScreen(
                navigateToWorkout = { workoutId, onNavigateBack ->
                    navController.navigate(WorkoutDetailsRoute(workoutId))
                    navController.saveInSavedState(value = onNavigateBack)
                },
                onSelectNavBarRoute = { navBarRouteIndex = it.index }
            )

            NavBarRoute.Nutrition -> NutritionScreen(
                navigateToSuggestedMeals = { navController.navigate(SuggestedMealsRoute) },
                navigateToMealDetails = { mealId, onNavigateBack ->
                    navController.navigate(MealDetailsRoute(mealId))
                    navController.saveInSavedState(value = onNavigateBack)
                },
                navigateToMealsHistory = { navController.navigate(MealsHistoryRoute) },
                onSelectNavBarRoute = { navBarRouteIndex = it.index }
            )

            NavBarRoute.Workout -> WorkoutScreen(
                navigateToCreateWorkout = { navController.navigate(CreateWorkoutRoute) },
                navigateToCommunityWorkout = { navController.navigate(CommunityWorkoutRoute) },
                navigateToWorkoutDetails = { workoutId ->
                    navController.navigate(
                        WorkoutDetailsRoute(
                            workoutId
                        )
                    )
                },
                onSelectNavBarRoute = { navBarRouteIndex = it.index }
            )

            NavBarRoute.Report -> ReportScreen(
                navigateToWorkoutHistory = { navController.navigate(WorkoutHistoryRoute) },
                onSelectNavBarRoute = { navBarRouteIndex = it.index }
            )

            NavBarRoute.More -> MoreScreen(
                navigateToFavorites = { navController.navigate(FavoritesScreenRoute) },
                navigateToNotificationSettings = { },
                onLogout = {
                    navController.navigate(LoginRoute) {
                        popUpTo(NavBarRoute.Home) { inclusive = true }
                        launchSingleTop = true
                        restoreState = false
                    }
                },
                navigateToEditProfile = { navController.navigate(EditProfileRoute) },
                onSelectNavBarRoute = { navBarRouteIndex = it.index },
                onLanguageChange = onLanguageChange,
                onThemeChanged = onThemeChange,
            )
        }
    }
}