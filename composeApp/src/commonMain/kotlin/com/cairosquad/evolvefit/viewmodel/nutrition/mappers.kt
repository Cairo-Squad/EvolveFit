package com.cairosquad.evolvefit.viewmodel.nutrition

import com.cairosquad.evolvefit.entity.DailySummary
import com.cairosquad.evolvefit.entity.Meal
import com.cairosquad.evolvefit.entity.MealType
import com.cairosquad.evolvefit.entity.SuggestedMeal
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_coffee
import evolvefit.composeapp.generated.resources.ic_donuts
import evolvefit.composeapp.generated.resources.ic_launch
import evolvefit.composeapp.generated.resources.ic_pizza_slice
import org.jetbrains.compose.resources.DrawableResource
import kotlinx.datetime.*
import kotlin.time.*
fun NutritionScreenState.MealTypeUiState.toMealIcon(): DrawableResource {
   return when(this) {
        NutritionScreenState.MealTypeUiState.Breakfast -> Res.drawable.ic_coffee
        NutritionScreenState.MealTypeUiState.Lunch -> Res.drawable.ic_launch
        NutritionScreenState.MealTypeUiState.Dinner -> Res.drawable.ic_pizza_slice
        NutritionScreenState.MealTypeUiState.Snacks -> Res.drawable.ic_donuts
    }
}
fun NutritionScreenState.MealTypeUiState.toMealType(): MealType {
    return when (this) {
        NutritionScreenState.MealTypeUiState.Breakfast -> MealType.BREAKFAST
        NutritionScreenState.MealTypeUiState.Lunch -> MealType.LUNCH
        NutritionScreenState.MealTypeUiState.Dinner -> MealType.DINNER
        NutritionScreenState.MealTypeUiState.Snacks -> MealType.SNACK
    }
}
fun MealType.toMealUiState(): NutritionScreenState.MealTypeUiState {
    return when (this) {
        MealType.BREAKFAST -> NutritionScreenState.MealTypeUiState.Breakfast
        MealType.LUNCH ->  NutritionScreenState.MealTypeUiState.Lunch
        MealType.DINNER ->NutritionScreenState.MealTypeUiState.Dinner
        MealType.SNACK -> NutritionScreenState.MealTypeUiState.Snacks
    }
}
fun Meal.toMealHistoryUi(): NutritionScreenState.MealHistory {
    return NutritionScreenState.MealHistory(
        name = this.name,
        type = this.type.toMealUiState(),
        calories = this.calories,
        date = this.dateTime
    )
}
fun SuggestedMeal.toSuggestedMealUi(): NutritionScreenState.SuggestedMeal {
    return NutritionScreenState.SuggestedMeal(
        name = this.name,
        type = this.type.toMealUiState(),
        calories = this.calories,
        imageUrl = this.imageUrl
    )
}
fun Meal.toTodayMealUi(): NutritionScreenState.TodayMealUiState {
    return NutritionScreenState.TodayMealUiState(
        type = this.type.toMealUiState(),
        calories = this.calories.toFloat(),
        icon = this.type.toMealUiState().toMealIcon()
    )
}