package com.cairosquad.evolvefit.ui.screen.nutrition

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.SnackBar
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.ui.navigation.NavBarRoute
import com.cairosquad.evolvefit.ui.navigation.navBar.Scaffold
import com.cairosquad.evolvefit.ui.screen.nutrition.component.MealAddedSnackBar
import com.cairosquad.evolvefit.ui.screen.nutrition.component.MealCantAddSnackBar
import com.cairosquad.evolvefit.ui.screen.nutrition.content.MealHistoryItem
import com.cairosquad.evolvefit.ui.screen.nutrition.component.MealTypeDropdownMenu
import com.cairosquad.evolvefit.ui.screen.nutrition.component.NutritionHeader
import com.cairosquad.evolvefit.ui.screen.nutrition.content.NutritionLoadingScreen
import com.cairosquad.evolvefit.ui.screen.nutrition.component.NutritionSummaryCard
import com.cairosquad.evolvefit.ui.screen.nutrition.content.NutritionContent
import com.cairosquad.evolvefit.ui.screen.nutrition.content.SeeAll
import com.cairosquad.evolvefit.ui.screen.nutrition.content.SuggestedMeals
import com.cairosquad.evolvefit.ui.screen.nutrition.content.TodayMealsSummary
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionEffect
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionInteractionListener
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_button
import evolvefit.composeapp.generated.resources.enter_water_intake
import evolvefit.composeapp.generated.resources.ic_info
import evolvefit.composeapp.generated.resources.ic_no_internet_light
import evolvefit.composeapp.generated.resources.ic_no_meals_light
import evolvefit.composeapp.generated.resources.ic_water_drop
import evolvefit.composeapp.generated.resources.im_no_internet
import evolvefit.composeapp.generated.resources.im_no_meals_recorded
import evolvefit.composeapp.generated.resources.internet_is_not_available
import evolvefit.composeapp.generated.resources.meal_added_snackbar
import evolvefit.composeapp.generated.resources.meal_history
import evolvefit.composeapp.generated.resources.no_meals_description
import evolvefit.composeapp.generated.resources.no_meals_title
import evolvefit.composeapp.generated.resources.please_make_sure_you_are_connected_to_the_internet_and_try_again
import evolvefit.composeapp.generated.resources.track_water_intake
import evolvefit.composeapp.generated.resources.try_again_button
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NutritionScreen(
    navigateToSuggestedMeals: () -> Unit,
    navigateToMealDetails: (String) -> Unit,
    navigateToMealsHistory: () -> Unit,
    onSelectNavBarRoute: (navBarRoute: NavBarRoute) -> Unit,
    nutritionViewModel: NutritionViewModel = koinViewModel()
) {
    val state by nutritionViewModel.screenState.collectAsState()

    ObserveAsEffect(nutritionViewModel.effect) { effect ->
        when (effect) {
            NutritionEffect.NavigateToMealHistory -> navigateToMealsHistory()
            is NutritionEffect.NavigateToSuggestedMealDetails -> navigateToMealDetails(effect.mealId)
            NutritionEffect.NavigateToSuggestedMeals -> navigateToSuggestedMeals()
        }
    }

    Scaffold(
        currentRoute = NavBarRoute.Nutrition,
        onSelectNavBarRoute = onSelectNavBarRoute
    ) {
        NutritionContent(
            state = state,
            listener = nutritionViewModel,
        )
    }
}
