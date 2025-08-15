package com.cairosquad.evolvefit.viewmodel.nutrition

import com.cairosquad.evolvefit.entity.nutrition.ConsumedMeal
import com.cairosquad.evolvefit.entity.nutrition.MealType
import com.cairosquad.evolvefit.entity.nutrition.SuggestedMeal
import com.cairosquad.evolvefit.viewmodel.utils.formatIsoToTodayTime
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_coffee
import evolvefit.composeapp.generated.resources.ic_donuts
import evolvefit.composeapp.generated.resources.ic_launch
import evolvefit.composeapp.generated.resources.ic_pizza_slice
import org.jetbrains.compose.resources.DrawableResource

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
fun ConsumedMeal.toMealHistoryUi(): NutritionScreenState.ConsumedMealUiState {
    return NutritionScreenState.ConsumedMealUiState(
        name = this.name,
        type = this.type.toMealUiState(),
        calories = this.calories,
        date = formatIsoToTodayTime(this.dateTime)
    )
}
fun SuggestedMeal.toSuggestedMealUi(): NutritionScreenState.SuggestedMealUiState {
    return NutritionScreenState.SuggestedMealUiState(
        name = this.name,
        type = this.type.toMealUiState(),
        calories = this.calories,
        imageUrl = this.imageUrl
    )
}
fun ConsumedMeal.toTodayMealUi(): NutritionScreenState.DailyMealSummaryUiState {
    return NutritionScreenState.DailyMealSummaryUiState(
        type = this.type.toMealUiState(),
        calories = this.calories.toFloat(),
        icon = this.type.toMealUiState().toMealIcon()
    )
}