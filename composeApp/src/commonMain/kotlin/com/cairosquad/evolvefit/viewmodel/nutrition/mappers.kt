package com.cairosquad.evolvefit.viewmodel.nutrition

import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_coffee
import evolvefit.composeapp.generated.resources.ic_donuts
import evolvefit.composeapp.generated.resources.ic_launch
import evolvefit.composeapp.generated.resources.ic_pizza_slice
import org.jetbrains.compose.resources.DrawableResource

fun NutritionScreenState.MealType.toMealIcon(): DrawableResource {
   return when(this) {
        NutritionScreenState.MealType.Breakfast -> Res.drawable.ic_coffee
        NutritionScreenState.MealType.Lunch -> Res.drawable.ic_launch
        NutritionScreenState.MealType.Dinner -> Res.drawable.ic_pizza_slice
        NutritionScreenState.MealType.Snacks -> Res.drawable.ic_donuts
    }
}