package com.cairosquad.evolvefit.ui.screen.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.SnackBar
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.favorites.content.componants.TabsWithPager
import com.cairosquad.evolvefit.ui.screen.favorites.content.componants.dummyWorkouts
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.favorites.FavoritesEffect
import com.cairosquad.evolvefit.viewmodel.favorites.FavoritesInteractionListener
import com.cairosquad.evolvefit.viewmodel.favorites.FavoritesState
import com.cairosquad.evolvefit.viewmodel.favorites.FavoritesViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arrow_back_description
import evolvefit.composeapp.generated.resources.favorites_title
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_warning
import evolvefit.composeapp.generated.resources.remove_from_favorites
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
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        FavoritesScreenContent(
            state = state,
            listener = favoritesViewModel
        )
        DeleteFavoritesSnackBar(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .align(Alignment.BottomCenter),
            isVisible = state.isSnackBarVisible,
            onUndoClicked = { favoritesViewModel.onUndoClicked() }
        )

    }
}

@Composable
fun FavoritesScreenContent(
    state: FavoritesState,
    listener: FavoritesInteractionListener
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .windowInsetsPadding(WindowInsets.systemBars),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomAppBar(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = stringResource(Res.string.favorites_title),
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
            workouts = state.workoutsList,
            state = state,
            listener = listener
        )
    }
}

@Composable
private fun DeleteFavoritesSnackBar(
    isVisible: Boolean,
    modifier: Modifier = Modifier,
    onUndoClicked: () -> Unit
) {
    println("asdasd $isVisible")
    SnackBar(
        modifier = modifier,
        text = stringResource(Res.string.remove_from_favorites),
        icon = painterResource(Res.drawable.ic_warning),
        isVisible = isVisible,
        isUndo = true,
        onUndoClicked = onUndoClicked
    )
}

@Preview
@Composable
private fun FavoritesScreenContentPreview() {

    AppTheme(isDarkTheme = true) {
        FavoritesScreenContent(
            state = FavoritesState(
                isLoading = false,
                workoutsList = dummyWorkouts,
                mealsList = emptyList(),
                isWorkoutTabSelected = true,
                isMealTabSelected = false
            ),
            listener = object : FavoritesInteractionListener {
                override fun onMealTabSelected() {}
                override fun onWorkoutTabSelected() {}
                override fun onBackClicked() {}
                override fun onUndoClicked() {}
                override fun deleteMeal(mealId: String) {}
                override fun deleteWorkout(workoutId: String) {}
            }
        )
    }
}

