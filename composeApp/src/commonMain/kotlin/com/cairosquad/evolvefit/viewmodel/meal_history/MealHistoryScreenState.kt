package com.cairosquad.evolvefit.viewmodel.meal_history

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_coffee
import evolvefit.composeapp.generated.resources.ic_donuts
import evolvefit.composeapp.generated.resources.ic_launch
import evolvefit.composeapp.generated.resources.ic_pizza_slice
import evolvefit.composeapp.generated.resources.meal_type_breakfast
import evolvefit.composeapp.generated.resources.meal_type_dinner
import evolvefit.composeapp.generated.resources.meal_type_lunch
import evolvefit.composeapp.generated.resources.meal_type_snacks
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource


data class MealHistoryScreenState(
    val mealsHistories: List<DayGroupedMealsUiState> = emptyList(),
    val errorMessage: StringResource? = null,
    val screenStatus: ScreenStatus = ScreenStatus.LOADING
) {
    enum class ScreenStatus {
        LOADING,
        ERROR,
        SUCCESS
    }

    data class DayGroupedMealsUiState(
        val dayHeader: String,
        val date: String,
        val meals: List<MealHistoryUiState>
    )

    data class MealHistoryUiState(
        val name: String = "",
        val type: MealType = MealType.Breakfast,
        val calories: Int = 0,
        val date: String = "",
        val rawDate: String = "",
    )

    enum class MealType(val displayName: StringResource, val icon: DrawableResource) {
        Breakfast(Res.string.meal_type_breakfast, Res.drawable.ic_coffee),
        Lunch(Res.string.meal_type_lunch, Res.drawable.ic_launch),
        Dinner(Res.string.meal_type_dinner, Res.drawable.ic_pizza_slice),
        Snacks(Res.string.meal_type_snacks, Res.drawable.ic_donuts)
    }
}

