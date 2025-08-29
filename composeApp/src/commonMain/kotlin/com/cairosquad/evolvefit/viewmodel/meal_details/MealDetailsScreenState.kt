package com.cairosquad.evolvefit.viewmodel.meal_details

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_meal
import evolvefit.composeapp.generated.resources.ic_green_check_circle
import evolvefit.composeapp.generated.resources.meal_type_breakfast
import evolvefit.composeapp.generated.resources.meal_type_dinner
import evolvefit.composeapp.generated.resources.meal_type_lunch
import evolvefit.composeapp.generated.resources.meal_type_snacks
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class MealDetailsScreenState(
    val mealDetails: MealDetailsUiState = MealDetailsUiState(),
    val errorMessage: StringResource? = null,
    val snackBarMessage: StringResource = Res.string.add_meal,
    val snackBarIcon: DrawableResource = Res.drawable.ic_green_check_circle,
    val isSnackBarVisible: Boolean = false,
    val screenStatus: ScreenStatus = ScreenStatus.LOADING,
    val showSaveMealSuccessSnackBar : Boolean = false,
    val mealAddingStatus: MealAddingStatus = MealAddingStatus.READY,
    val isRefreshing: Boolean = false,
){
    data class MealDetailsUiState(
        val name : String = "",
        val imgUrl : String = "",
        val description : String = "",
        val mealType : MealTypeUiState = MealTypeUiState.Breakfast,
        val calories : Int = 0,
        val protein : Int = 0,
        val carbs : Int = 0,
        val fat : Int = 0,
        val ingredients : List<String> = emptyList(),
        val isFavouriteMeal : Boolean = false
    )
    enum class MealTypeUiState(val displayName: StringResource) {
        Breakfast(Res.string.meal_type_breakfast),
        Lunch(Res.string.meal_type_lunch),
        Dinner(Res.string.meal_type_dinner),
        Snacks(Res.string.meal_type_snacks)
    }
    enum class ScreenStatus {
        LOADING,
        ERROR,
        SUCCESS
    }

    enum class MealAddingStatus {
        READY,
        LOADING,
    }
}
