package com.cairosquad.evolvefit.ui.screen.mealDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.screen.mealDetails.components.IngredientsList
import com.cairosquad.evolvefit.ui.screen.mealDetails.components.MealDescription
import com.cairosquad.evolvefit.ui.screen.mealDetails.components.MealDetailsAppBar
import com.cairosquad.evolvefit.ui.screen.mealDetails.components.NutritionalInfo
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.meal_details.MealDetailsEffect
import com.cairosquad.evolvefit.viewmodel.meal_details.MealDetailsInteractionListener
import com.cairosquad.evolvefit.viewmodel.meal_details.MealDetailsScreenState
import com.cairosquad.evolvefit.viewmodel.meal_details.MealDetailsViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.meal_image
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MealDetailsScreen(
    mealId: String,
    navigateBack: () -> Unit,
    viewModel: MealDetailsViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getMealDetails(mealId)
    }
    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is MealDetailsEffect.NavigateBack -> navigateBack()
        }
    }
    MealDetailsScreenContent(
        listener = viewModel,
        state = state ,
        mealId = mealId
    )
}

@Composable
private fun MealDetailsScreenContent(
    listener : MealDetailsInteractionListener,
    state : MealDetailsScreenState,
    mealId : String
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(331.dp)
        ) {
            NetworkImage(
                model = state.mealDetails.imgUrl,
                contentDescription = stringResource(Res.string.meal_image),
                modifier = Modifier
                    .fillMaxSize()
                    .height(331.dp),
                contentScale = ContentScale.Crop
            )
            MealDetailsAppBar(
                onBackClick = { listener.onBackClicked() },
                onBookmarkClick = { listener.onSaveMealClicked(mealId) }
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 307.dp)
                .background(
                    Theme.color.surfaces.surface,
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .padding(horizontal = 16.dp).padding(bottom = 16.dp)
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
}