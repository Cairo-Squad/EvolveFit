package com.cairosquad.evolvefit.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.component.CaloriesNutritionCard
import com.cairosquad.evolvefit.ui.component.WaterNutritionCard
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.base.ErrorState
import com.cairosquad.evolvefit.viewmodel.home.HomeInteractionListener
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenEffect
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenState
import com.cairosquad.evolvefit.viewmodel.home.HomeViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.profile_picture
import evolvefit.composeapp.generated.resources.today_nutrition
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navigateToWorkout: (Long) -> Unit,
    homeViewModel: HomeViewModel = koinViewModel(),
) {
    val state by homeViewModel.screenState.collectAsState()

    ObserveAsEffect(homeViewModel.effect) { effect ->
        when (effect) {
            is HomeScreenEffect.NavigateToWorkout -> {
                navigateToWorkout(effect.workoutId)
            }

            is HomeScreenEffect.ShowErrorSnackBar -> {
                TODO()
            }
        }
    }

    if (state.isLoading) {
        HomeLoadingContent()
    } else if (state.error != null) {
        HomeErrorContent(
            error = state.error!!,
            onRetry = homeViewModel::onRetryClicked
        )
    } else {
        HomeContent(
            state = state,
            interactionListener = homeViewModel
        )
    }
}

@Composable
private fun HomeContent(
    state: HomeScreenState,
    interactionListener: HomeInteractionListener
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Theme.color.surfaces.surface)
            .verticalScroll(rememberScrollState())
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        HomeUserHeader(
            user = state.user,
            modifier = Modifier
                .padding(bottom = 24.dp)
        )

        HomeSection(
            title = stringResource(Res.string.today_nutrition),
            key = state.caloriesCount > 0.toUInt() && state.waterCount > 0
        ) {
            SimpleNutritionRow(
                caloriesValue = state.caloriesCount,
                caloriesGoal = state.caloriesGoal,
                waterValue = state.waterCount,
                waterGoal = state.waterGoal
            )
        }
    }
}

@Composable
private fun HomeUserHeader(
    user: HomeScreenState.HomeUserUiState?,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(user != null) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                model = user?.profilePictureUrl ?: "",
                contentDescription = stringResource(Res.string.profile_picture),
                contentScale = ContentScale.Crop,
                placeholderImageSize = DpSize(40.dp, 40.dp),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
        }
    }
}

@Composable
private fun HomeSection(
    title: String,
    key: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(key) {
        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = Theme.textStyle.label.mediumMedium16,
                color = Theme.color.surfaces.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            content()
        }
    }
}

@Composable
private fun SimpleNutritionRow(
    caloriesValue: UInt,
    caloriesGoal: UInt,
    waterValue: Float,
    waterGoal: Float,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CaloriesNutritionCard(
            value = caloriesValue,
            goal = caloriesGoal,
            modifier = Modifier
                .weight(1f)
        )

        WaterNutritionCard(
            value = waterValue,
            goal = waterGoal,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun HomeErrorContent(
    error: ErrorState,
    onRetry: () -> Unit
) {
    // TODO
}

@Composable
private fun HomeLoadingContent() {
    // TODO
}