package com.cairosquad.evolvefit.ui.screen.nutrition

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.composables.CircularPercentageIndicator
import com.cairosquad.evolvefit.design_system.composables.MealCard
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_coffee
import evolvefit.composeapp.generated.resources.ic_donuts
import evolvefit.composeapp.generated.resources.ic_end_arrow
import evolvefit.composeapp.generated.resources.ic_fire
import evolvefit.composeapp.generated.resources.ic_launch
import evolvefit.composeapp.generated.resources.ic_pizza_slice
import evolvefit.composeapp.generated.resources.ic_plus
import evolvefit.composeapp.generated.resources.ic_scan
import evolvefit.composeapp.generated.resources.ic_water_drop
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NutritionScreen() {
    val meals = remember {
        listOf(
            Meal(
                "Breakfast",
                Res.drawable.ic_coffee,
                "320",
                date = "Today, 12:15 PM",
                type = MealType.Breakfast
            ),
            Meal(
                "Lunch",
                Res.drawable.ic_launch,
                "540",
                date = "Today, 12:15 PM",
                type = MealType.Breakfast
            ),
            Meal(
                "Dinner",
                Res.drawable.ic_pizza_slice,
                "320",
                date = "Today, 12:15 PM",
                type = MealType.Breakfast
            ),
            Meal(
                "Snacks",
                Res.drawable.ic_donuts,
                "150",
                date = "Today, 12:15 PM",
                type = MealType.Breakfast
            ),
        )
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
    ) {
        stickyHeader {
            Row(
                modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth()
                    .background(Theme.color.surfaces.surface),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
                        .padding(vertical = 14.5.dp),
                    text = "Nutrition",
                    style = Theme.textStyle.title.largeBold16,
                    color = Theme.color.surfaces.onSurface
                )
            }
        }
        item {
            CaloriesAndWaterBox()
            ScanMeal()
            TodayMealsHeader()
            TodayMeals(meals = meals)
            SuggestedMeals()
        }
        item {
            SeeAll(
                sectionTitle = "Meal History",
                modifier = Modifier.padding(horizontal = 16.dp).padding(top = 32.dp, bottom = 12.dp)
            )
        }
        items(meals) {
            MealHistoryItem(meal = it)
        }
    }
}

@Composable
private fun CaloriesAndWaterBox(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircularPercentageIndicator(
                modifier = Modifier.weight(1f),
                title = "Calories",
                currentValue = 1200f,
                totalValue = 2000f,
                unit = "kcal",
                icon = painterResource(Res.drawable.ic_fire),
                iconColor = Theme.color.system.success,
                progressColor = Theme.color.system.success,
                buttonClickable = false
            )
            CircularPercentageIndicator(
                modifier = Modifier.weight(1f),
                title = "Water",
                currentValue = 1.5856f,
                totalValue = 3.10f,
                unit = "L",
                icon = painterResource(Res.drawable.ic_water_drop),
                iconColor = Theme.color.system.info,
                progressColor =  Theme.color.system.info,
                buttonClickable = true
            )
        }
    }
}

@Composable
private fun ScanMeal(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Row(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_scan),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Scan Meal",
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.surfaces.onSurfaceContainer
            )
            Text(
                text = "Take a picture of your meal to count calories.",
                style = Theme.textStyle.body.mediumMedium12,
                color = Theme.color.surfaces.outline
            )
        }
        Icon(
            painter = painterResource(Res.drawable.ic_end_arrow),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@Composable
private fun TodayMeals(modifier: Modifier = Modifier, meals: List<Meal>) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Theme.color.surfaces.surfaceContainer)
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.fillMaxWidth().heightIn(max = 100.dp),
        ) {
            items(meals.size) { index ->
                MealItem(meal = meals[index])
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 8.dp)
                .fillMaxWidth()
                .height(1.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.outlineVariant)
        ) { }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Remaining",
                    style = Theme.textStyle.title.mediumMedium14,
                    color = Theme.color.surfaces.onSurfaceContainer
                )
                Text(
                    text = "2,000 kcal",
                    style = Theme.textStyle.body.mediumMedium12,
                    color = Theme.color.surfaces.outline
                )
            }
            Icon(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(20.dp),
                painter = painterResource(Res.drawable.ic_plus),
                contentDescription = null,
                tint = Theme.color.brand.primary
            )
            Text(
                text = "Add Meal",
                style = Theme.textStyle.body.mediumMedium14,
                color = Theme.color.brand.primary
            )
        }
    }
}

@Composable
private fun TodayMealsHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp, bottom = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Today's Meals",
            style = Theme.textStyle.title.largeBold16,
            color = Theme.color.surfaces.onSurface
        )
    }
}

@Composable
private fun SuggestedMeals(modifier: Modifier = Modifier) {
    SeeAll(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp, bottom = 12.dp),
        sectionTitle = "Suggested Meals",
    )
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(5) {
            MealCard("Caesar salad", "Dinner", 350)
        }
    }
}

@Composable
private fun SeeAll(sectionTitle: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            modifier = Modifier.weight(1f),
            text = sectionTitle,
            style = Theme.textStyle.label.mediumMedium16,
            color = Theme.color.surfaces.onSurface
        )
        Text(
            text = "View All",
            style = Theme.textStyle.body.mediumMedium14,
            color = Theme.color.surfaces.onSurfaceVariant
        )
        Icon(
            modifier = Modifier.padding(start = 4.dp),
            painter = painterResource(Res.drawable.ic_end_arrow),
            contentDescription = null,
            tint = Color.Unspecified
        )
    }
}

@Composable
private fun MealItem(modifier: Modifier = Modifier, meal: Meal) {
    Column(
        modifier = modifier.wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.surfaceVariant),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(meal.icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = meal.name,
            style = Theme.textStyle.title.mediumMedium14,
            color = Theme.color.surfaces.onSurfaceContainer
        )
        Text(
            text = meal.calories + "Kcal",
            style = Theme.textStyle.label.smallRegular12,
            color = Theme.color.surfaces.onSurfaceVariant
        )
    }
}

@Composable
fun MealHistoryItem(
    modifier: Modifier = Modifier,
    meal: Meal
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Theme.color.surfaces.outlineVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(meal.icon),
                contentDescription = null,
                tint = Theme.color.brand.primary,
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = meal.name,
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.surfaces.onSurface
            )
            Text(
                text = meal.date,
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${meal.calories} kcal",
                style = Theme.textStyle.title.largeBold14,
                color = Theme.color.brand.primary
            )
            Text(
                text = meal.type.displayName,
                style = Theme.textStyle.body.smallRegular10,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
    }
}

data class Meal(
    val name: String,
    val icon: DrawableResource,
    val calories: String,
    val date: String = "",
    val type: MealType
)

enum class MealType(val displayName: String) {
    Breakfast("Breakfast"),
    Lunch("Lunch"),
    Dinner("Dinner"),
    Snacks("Snacks")
}

@Preview
@Composable
fun NutritionScreenPreview() {
    AppTheme(isDarkTheme = true) {
        Column() {
            NutritionScreen()
        }
    }
}