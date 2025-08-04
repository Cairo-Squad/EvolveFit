package com.cairosquad.evolvefit.ui.screen.suggestedMeals

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.MealCard
import com.cairosquad.evolvefit.design_system.theme.Theme

@Composable
fun SuggestedMealsScreen(
    navigateBack: () -> Unit,
    navigateToMealDetails: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            modifier = Modifier.padding(12.dp),
            text = "Suggested Meals Screen", color = Theme.color.surfaces.onSurface
        )

        Button(
            onClick = navigateBack
        ) {
            Text("navigate back")
        }

        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ){
            items(20) { index ->
                MealCard(
                    modifier = Modifier
                        .width(158.dp)
                        .clickable(onClick = { navigateToMealDetails((index + 1).toLong()) })
                    ,
                    title = "Meal ${index + 1}",
                    mealType = "Lunch",
                    calories = 350,
                    model = ""
                )
            }
        }
    }
}