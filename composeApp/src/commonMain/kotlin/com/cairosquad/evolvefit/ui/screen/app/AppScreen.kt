package com.cairosquad.evolvefit.ui.screen.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.cairosquad.evolvefit.design_system.component.NavigationBar
import com.cairosquad.evolvefit.design_system.component.NavigationBarItem
import com.cairosquad.evolvefit.ui.screen.home.HomeScreen
import com.cairosquad.evolvefit.ui.screen.nutrition.NutritionScreen
import com.cairosquad.evolvefit.ui.screen.more.MoreScreen
import com.cairosquad.evolvefit.ui.screen.report.ReportScreen
import com.cairosquad.evolvefit.ui.screen.workout.WorkoutScreen
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.dashboard
import evolvefit.composeapp.generated.resources.dashboard_filled
import evolvefit.composeapp.generated.resources.more
import evolvefit.composeapp.generated.resources.more_filled
import evolvefit.composeapp.generated.resources.nutrition
import evolvefit.composeapp.generated.resources.nutrition_filled
import evolvefit.composeapp.generated.resources.reports
import evolvefit.composeapp.generated.resources.reports_filled
import evolvefit.composeapp.generated.resources.workouts
import evolvefit.composeapp.generated.resources.workouts_filled
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppScreen(
    navigateToCreateWorkout: () -> Unit,
    navigateToCommunityWorkout: () -> Unit,
    navigateToWorkoutDetails: (String) -> Unit,
    navigateToSuggestedMeals: () -> Unit,
    navigateToMealDetails: (String) -> Unit,
    navigateToMealsHistory: () -> Unit,
    navigateToWorkoutHistory: () -> Unit,
    navigateToEditProfile: () -> Unit,
    navigateToLogIn: () -> Unit
) {
    var selectedScreenIndex by rememberSaveable { mutableIntStateOf(0) }

    val navigationItemsRes = remember {
        listOf(
            NavigationItemRes(
                outlinedIconRes = Res.drawable.dashboard,
                filledIconRes = Res.drawable.dashboard_filled,
                labelRes = Res.string.dashboard
            ),
            NavigationItemRes(
                outlinedIconRes = Res.drawable.nutrition,
                filledIconRes = Res.drawable.nutrition_filled,
                labelRes = Res.string.nutrition
            ),
            NavigationItemRes(
                outlinedIconRes = Res.drawable.workouts,
                filledIconRes = Res.drawable.workouts_filled,
                labelRes = Res.string.workouts
            ),
            NavigationItemRes(
                outlinedIconRes = Res.drawable.reports,
                filledIconRes = Res.drawable.reports_filled,
                labelRes = Res.string.reports
            ),
            NavigationItemRes(
                outlinedIconRes = Res.drawable.more,
                filledIconRes = Res.drawable.more_filled,
                labelRes = Res.string.more
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            when (selectedScreenIndex) {
                0 -> HomeScreen(
                    navigateToWorkout = navigateToWorkoutDetails
                )
                1 -> NutritionScreen(
                    navigateToSuggestedMeals = navigateToSuggestedMeals,
                    navigateToMealDetails = navigateToMealDetails,
                    navigateToMealsHistory = navigateToMealsHistory
                )

                2 -> WorkoutScreen(
                    navigateToCreateWorkout = navigateToCreateWorkout,
                    navigateToCommunityWorkout = navigateToCommunityWorkout,
                    navigateToWorkoutDetails = navigateToWorkoutDetails,
                )

                3 -> ReportScreen(
                    navigateToWorkoutHistory = navigateToWorkoutHistory
                )

                4 -> MoreScreen(
                    navigateToFavorites = {  },
                    navigateToNotificationSettings = { },
                    onLanguageChanged = {  },
                    onThemeChanged = {  },
                    onLogout = navigateToLogIn,
                    navigateToEditProfile = navigateToEditProfile

                )

            }
        }

        NavigationBar(
            modifier = Modifier.navigationBarsPadding(),
            selectedItem = selectedScreenIndex,
            onItemClick = { selectedScreenIndex = it },
            navigationItems = navigationItemsRes.map { it.toNavigationBarItem() }
        )
    }
}

private data class NavigationItemRes(
    val outlinedIconRes: DrawableResource,
    val filledIconRes: DrawableResource,
    val labelRes: StringResource
) {
    @Composable
    fun toNavigationBarItem(): NavigationBarItem {
        return NavigationBarItem(
            outlinedIcon = painterResource(outlinedIconRes),
            filledIcon = painterResource(filledIconRes),
            label = stringResource(labelRes)
        )
    }
}