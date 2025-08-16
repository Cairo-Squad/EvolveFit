package com.cairosquad.evolvefit.viewmodel.meal_details

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.meal_type_breakfast
import evolvefit.composeapp.generated.resources.meal_type_dinner
import evolvefit.composeapp.generated.resources.meal_type_lunch
import evolvefit.composeapp.generated.resources.meal_type_snacks
import org.jetbrains.compose.resources.StringResource

data class MealDetailsScreenState(
    val mealDetails: MealDetailsUiState = MealDetailsUiState(),
    val errorMessage: String? = null,
    val screenStatus: ScreenStatus = ScreenStatus.LOADING,
    val showSaveMealSnackBar : Boolean = false
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
        val ingredients : List<String> = emptyList()

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


}
