package com.cairosquad.evolvefit.ui.screen.mealDetails.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.screen.mealDetails.content.components.IngredientsList
import com.cairosquad.evolvefit.ui.screen.mealDetails.content.components.MealDescription
import com.cairosquad.evolvefit.ui.screen.mealDetails.content.components.MealDetailsAppBar
import com.cairosquad.evolvefit.ui.screen.mealDetails.content.components.NutritionalInfo
import com.cairosquad.evolvefit.ui.screen.mealDetails.content.components.SaveMealSuccessSnackBar
import com.cairosquad.evolvefit.viewmodel.meal_details.MealDetailsInteractionListener
import com.cairosquad.evolvefit.viewmodel.meal_details.MealDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.meal_image
import org.jetbrains.compose.resources.stringResource

@Composable
fun MealDetailsSuccessScreen(
    listener: MealDetailsInteractionListener,
    state: MealDetailsScreenState,
    mealId: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(331.dp)
        ) {
            NetworkImage(
                model = state.mealDetails.imgUrl,
                contentDescription = stringResource(Res.string.meal_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .height(331.dp),
            )
            MealDetailsAppBar(
                state = state,
                onBackClick = { listener.onBackClicked() },
                onBookmarkClick = { listener.onSaveMealClicked(mealId) }
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 307.dp)
                .fillMaxSize()
                .background(
                    Theme.color.surfaces.surface,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Column {
                MealDescription(
                    state.mealDetails.name,
                    state.mealDetails.mealType.name,
                    state.mealDetails.description
                )
                NutritionalInfo(
                    modifier = Modifier.padding(top = 12.dp),
                    calories = state.mealDetails.calories,
                    carbs = state.mealDetails.carbs,
                    protein = state.mealDetails.protein,
                    fat = state.mealDetails.fat
                )
                Spacer(modifier = Modifier.height(32.dp))
                IngredientsList(
                    ingredients = state.mealDetails.ingredients
                )
            }
        }
    }
    if (state.showSaveMealSuccessSnackBar) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            SaveMealSuccessSnackBar(
                isVisible = state.showSaveMealSuccessSnackBar
            )
        }
    }
}