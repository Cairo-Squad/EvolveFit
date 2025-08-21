package com.cairosquad.evolvefit.ui.screen.mealsHistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.mealsHistory.components.GroupedMealHistoryItem
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.meal_history.MealHistoryEffect
import com.cairosquad.evolvefit.viewmodel.meal_history.MealHistoryViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.meal_history
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MealsHistoryScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MealHistoryViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()
    LaunchedEffect(Unit){
        viewModel.loadMealsHistory()
    }
    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is MealHistoryEffect.NavigateBack -> navigateBack()
        }
    }
    Column {
        CustomAppBar(
            title = stringResource(Res.string.meal_history),
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 16.dp),
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.back),
                    tint = Theme.color.surfaces.onSurface,
                    onClick = {viewModel.onNavigateBackClicked()}
                )
            }

        )
        Spacer(modifier.height(16.dp))
        LazyColumn{
            items(state.mealsHistories) { meal ->
                GroupedMealHistoryItem(
                    groupedMeal = meal
                )
            }

        }
    }
}