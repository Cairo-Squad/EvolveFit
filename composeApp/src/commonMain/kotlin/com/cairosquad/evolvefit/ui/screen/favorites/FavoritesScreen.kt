package com.cairosquad.evolvefit.ui.screen.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.favorites.componants.FavoritesCard
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.favorites.FavoritesEffect
import com.cairosquad.evolvefit.viewmodel.favorites.FavoritesInteractionListener
import com.cairosquad.evolvefit.viewmodel.favorites.FavoritesState
import com.cairosquad.evolvefit.viewmodel.favorites.FavoritesViewModel
import com.cairosquad.evolvefit.viewmodel.favorites.MealType
import com.cairosquad.evolvefit.viewmodel.favorites.MealsUiModel
import com.cairosquad.evolvefit.viewmodel.favorites.WorkoutsUiModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arrow_back_description
import evolvefit.composeapp.generated.resources.ic_back
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun FavoritesScreen(
    navigateBack: () -> Unit,
    favoritesViewModel: FavoritesViewModel = koinViewModel()
) {
    val state by favoritesViewModel.screenState.collectAsState()
    ObserveAsEffect(favoritesViewModel.effect) { effect ->
        when (effect) {
            FavoritesEffect.NavigateBack -> {
                navigateBack()
            }
        }
    }
    FavoritesScreenContent(
        state = state,
        listener = favoritesViewModel
    )
}

@Composable
fun FavoritesScreenContent(
    state: FavoritesState,
    listener: FavoritesInteractionListener
) {
    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.systemBars)
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomAppBar(
            title = "Favorites",
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.arrow_back_description),
                    tint = Theme.color.surfaces.onSurface,
                    onClick = listener::onBackClicked
                )
            }
        )
        TabsWithPager(
            meals = state.mealsList,
            workouts = state.workoutsList
        )

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsWithPager(
    meals: List<MealsUiModel>,
    workouts: List<WorkoutsUiModel>
) {
    val tabs = listOf("Workouts", "Meals")
    val pagerState = rememberPagerState(pageCount = { 2 })

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, title ->
                if (index == 0) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 6.dp)
                            .clickable {
                                CoroutineScope(Dispatchers.Main).launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 16.dp, horizontal = 12.dp),
                            text = title,
                            color = Theme.color.brand.primary,
                            style = Theme.textStyle.body.mediumMedium14
                        )
                        if (pagerState.currentPage == index) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(color = Theme.color.brand.primary)
                            )
                        } else {
                            Spacer(modifier = Modifier.height(1.dp))
                        }
                    }
                }

                if (index == 1) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 6.dp)
                            .clickable {
                                CoroutineScope(Dispatchers.Main).launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 16.dp, horizontal = 12.dp),
                            text = title,
                            color = Theme.color.brand.primary,
                            style = Theme.textStyle.body.mediumMedium14
                        )
                        if (pagerState.currentPage == index) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(color = Theme.color.brand.primary)
                            )
                        } else {
                            Spacer(modifier = Modifier.height(1.dp))
                        }
                    }
                }
            }
        }


        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> {
                    WorkoutList(workouts = workouts)
                }

                1 -> {
                    MealsList(meals = meals)
                }
            }
        }
    }
}

@Composable
fun WorkoutList(workouts: List<WorkoutsUiModel>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(workouts) { workout ->
            FavoritesCard(
                title = workout.name,
                subtitle = workout.estimatedTimeInSeconds.toString(),
                info = workout.focusArea,
                model = workout.imageUrl
            )
        }
    }
}

@Composable
fun MealsList(meals: List<MealsUiModel>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(meals) { meal ->
            FavoritesCard(
                title = meal.name,
                subtitle = meal.type.toString(),
                info = meal.nutrition.caloriesInKcal.toString(),
                model = meal.imageUrl
            )
        }
    }
}

val dummyWorkouts = listOf(
    WorkoutsUiModel(
        name = "Full Body Burn",
        estimatedTimeInSeconds = 900, // 15 minutes
        focusArea = "Full Body",
        imageUrl = "https://example.com/images/full_body.jpg"
    ),
    WorkoutsUiModel(
        name = "Abs Crusher",
        estimatedTimeInSeconds = 600, // 10 minutes
        focusArea = "Core",
        imageUrl = "https://example.com/images/abs_crusher.jpg"
    ),
    WorkoutsUiModel(
        name = "Leg Day Challenge",
        estimatedTimeInSeconds = 1200, // 20 minutes
        focusArea = "Legs",
        imageUrl = "https://example.com/images/leg_day.jpg"
    ),
    WorkoutsUiModel(
        name = "Upper Body Shred",
        estimatedTimeInSeconds = 750, // 12.5 minutes
        focusArea = "Upper Body",
        imageUrl = "https://example.com/images/upper_body.jpg"
    ),
    WorkoutsUiModel(
        name = "Upper Body Shred",
        estimatedTimeInSeconds = 750, // 12.5 minutes
        focusArea = "Upper Body",
        imageUrl = "https://example.com/images/upper_body.jpg"
    ),
    WorkoutsUiModel(
        name = "Upper Body Shred",
        estimatedTimeInSeconds = 750, // 12.5 minutes
        focusArea = "Upper Body",
        imageUrl = "https://example.com/images/upper_body.jpg"
    ),
    WorkoutsUiModel(
        name = "Upper Body Shred",
        estimatedTimeInSeconds = 750, // 12.5 minutes
        focusArea = "Upper Body",
        imageUrl = "https://example.com/images/upper_body.jpg"
    ),
    WorkoutsUiModel(
        name = "Upper Body Shred",
        estimatedTimeInSeconds = 750, // 12.5 minutes
        focusArea = "Upper Body",
        imageUrl = "https://example.com/images/upper_body.jpg"
    ),
    WorkoutsUiModel(
        name = "Upper Body Shred",
        estimatedTimeInSeconds = 750, // 12.5 minutes
        focusArea = "Upper Body",
        imageUrl = "https://example.com/images/upper_body.jpg"
    ), WorkoutsUiModel(
        name = "Upper Body Shred",
        estimatedTimeInSeconds = 750, // 12.5 minutes
        focusArea = "Upper Body",
        imageUrl = "https://example.com/images/upper_body.jpg"
    ),
    WorkoutsUiModel(
        name = "Stretch & Cool Down",
        estimatedTimeInSeconds = 300, // 5 minutes
        focusArea = "Flexibility",
        imageUrl = "https://example.com/images/stretch.jpg"
    )
)

val dummyMeals = listOf(
    MealsUiModel(
        name = "Avocado Toast",
        type = MealType.BREAKFAST,
        imageUrl = "https://example.com/images/avocado_toast.jpg",
        nutrition = MealsUiModel.Nutrition(
            caloriesInKcal = 350,
            fatInGrams = 20,
            proteinInGrams = 8,
            carbsInGrams = 30
        )
    ),
    MealsUiModel(
        name = "Grilled Chicken Salad",
        type = MealType.LUNCH,
        imageUrl = "https://example.com/images/chicken_salad.jpg",
        nutrition = MealsUiModel.Nutrition(
            caloriesInKcal = 500,
            fatInGrams = 18,
            proteinInGrams = 40,
            carbsInGrams = 20
        )
    ),
    MealsUiModel(
        name = "Beef Stir Fry",
        type = MealType.DINNER,
        imageUrl = "https://example.com/images/beef_stir_fry.jpg",
        nutrition = MealsUiModel.Nutrition(
            caloriesInKcal = 650,
            fatInGrams = 25,
            proteinInGrams = 45,
            carbsInGrams = 35
        )
    ),
    MealsUiModel(
        name = "Greek Yogurt with Berries",
        type = MealType.SNACKS,
        imageUrl = "https://example.com/images/yogurt_berries.jpg",
        nutrition = MealsUiModel.Nutrition(
            caloriesInKcal = 200,
            fatInGrams = 5,
            proteinInGrams = 12,
            carbsInGrams = 18
        )
    ),
    MealsUiModel(
        name = "Oatmeal with Banana",
        type = MealType.BREAKFAST,
        imageUrl = "https://example.com/images/oatmeal_banana.jpg",
        nutrition = MealsUiModel.Nutrition(
            caloriesInKcal = 300,
            fatInGrams = 6,
            proteinInGrams = 10,
            carbsInGrams = 50
        )
    )
)


@Preview
@Composable
private fun FavoritesScreenContentPreview() {
    AppTheme(isDarkTheme = true) {
        FavoritesScreenContent(
            state = FavoritesState(
                isLoading = false,
                workoutsList = dummyWorkouts,
                mealsList = dummyMeals,
                isWorkoutTabSelected = true,
                isMealTabSelected = false
            ),
            listener = object : FavoritesInteractionListener {
                override fun onMealTabSelected() {}
                override fun onWorkoutTabSelected() {}
                override fun onBackClicked() {}
            }
        )
    }
}

