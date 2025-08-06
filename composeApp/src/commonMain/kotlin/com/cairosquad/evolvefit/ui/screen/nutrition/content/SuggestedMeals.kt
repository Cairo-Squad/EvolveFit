package com.cairosquad.evolvefit.ui.screen.nutrition.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.MealCard
import com.cairosquad.evolvefit.ui.screen.nutrition.SeeAll
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionInteractionListener
import com.cairosquad.evolvefit.viewmodel.nutrition.NutritionScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.suggested_meals
import org.jetbrains.compose.resources.stringResource

@Composable
fun SuggestedMeals(
    state: NutritionScreenState,
    listener: NutritionInteractionListener,
    modifier: Modifier = Modifier
) {
    SeeAll(
        onViewAllClick = {
            listener.onViewAllSuggestedMealsClicked()
        },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp, bottom = 12.dp),
        sectionTitle = stringResource(Res.string.suggested_meals),
    )
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(state.suggestedMeals) {
            MealCard(
                title = it.name,
                mealType = stringResource(it.type.displayName),
                calories = it.calories,
                model = it.imageUrl
            )
        }
    }
}