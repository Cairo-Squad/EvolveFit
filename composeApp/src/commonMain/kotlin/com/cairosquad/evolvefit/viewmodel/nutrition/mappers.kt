package com.cairosquad.evolvefit.viewmodel.nutrition

import com.cairosquad.evolvefit.entity.ConsumedMeal
import com.cairosquad.evolvefit.entity.MealType
import com.cairosquad.evolvefit.entity.SuggestedMeal
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_coffee
import evolvefit.composeapp.generated.resources.ic_donuts
import evolvefit.composeapp.generated.resources.ic_launch
import evolvefit.composeapp.generated.resources.ic_pizza_slice
import org.jetbrains.compose.resources.DrawableResource

import kotlinx.datetime.*
import kotlinx.datetime.TimeZone

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
fun ConsumedMeal.toMealHistoryUi(): NutritionScreenState.MealHistory {
    return NutritionScreenState.MealHistory(
        name = this.name,
        type = this.type.toMealUiState(),
        calories = this.calories,
        date = formatDateTimeForDisplay(this.dateTime)
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
fun ConsumedMeal.toTodayMealUi(): NutritionScreenState.TodayMealUiState {
    return NutritionScreenState.TodayMealUiState(
        type = this.type.toMealUiState(),
        calories = this.calories.toFloat(),
        icon = this.type.toMealUiState().toMealIcon()
    )
}

fun formatDateTimeForDisplay(dateTimeString: String): String {
    val parsedDateTime = LocalDateTime.parse(dateTimeString)

    val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

    val date = parsedDateTime.date
    val today = now.date
    val yesterday = today.minus(DatePeriod(days = 1))

    val dayLabel = when (date) {
        today -> "Today"
        yesterday -> "Yesterday"
        else -> date.dayOfWeek.name.lowercase().replaceFirstChar { it.uppercase() } // e.g., "Wednesday"
    }
    val hour = parsedDateTime.hour
    val minute = parsedDateTime.minute
    val amPm = if (hour < 12) "AM" else "PM"
    val displayHour = if (hour % 12 == 0) 12 else hour % 12
    val displayMinute = minute.toString().padStart(2, '0')

    return "$dayLabel, $displayHour:$displayMinute $amPm"
}
 fun isToday(dateTimeString: String): Boolean {
    val parsedDateTime = LocalDateTime.parse(dateTimeString)
    val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    return parsedDateTime.date == today
}