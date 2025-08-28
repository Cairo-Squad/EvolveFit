package com.cairosquad.evolvefit.ui.screen.workout

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.navigation.NavBarRoute
import com.cairosquad.evolvefit.ui.navigation.navBar.Scaffold
import com.cairosquad.evolvefit.ui.screen.workout.content.WorkoutsScreenContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutEffect
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.create_workout_title_
import evolvefit.composeapp.generated.resources.ic_plus
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WorkoutScreen(
    navigateToCreateWorkout: () -> Unit,
    navigateToCommunityWorkout: () -> Unit,
    navigateToWorkoutDetails: (String) -> Unit,
    onSelectNavBarRoute: (navBarRoute: NavBarRoute) -> Unit,
    viewModel: WorkoutViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is WorkoutEffect.NavigateToWorkoutDetails -> navigateToWorkoutDetails(effect.workoutId)
            WorkoutEffect.NavigateToCommunityWorkout -> navigateToCommunityWorkout()
            WorkoutEffect.NavigateToCreateWorkout -> navigateToCreateWorkout()
        }
    }

    Scaffold(
        currentRoute = NavBarRoute.Workout,
        onSelectNavBarRoute = onSelectNavBarRoute
    ) {
        WorkoutsScreenContent(
            state = state,
            listener = viewModel
        )
    }
}

@Composable
fun AddWorkoutBtn(
    modifier: Modifier = Modifier,
    onClickAddWorkout: () -> Unit
) {
    FloatingActionButton(
        onClick = onClickAddWorkout,
        modifier = modifier,
        containerColor = Theme.color.brand.primary,
        shape = CircleShape,
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_plus),
            contentDescription = stringResource(Res.string.create_workout_title_),
            modifier = Modifier.padding(12.dp).size(24.dp)
        )
    }
}

@Preview
@Composable
private fun WorkoutsScreenPreview() {
    AppTheme(isDarkTheme = true) {
        WorkoutScreen(
            navigateToCreateWorkout = {},
            navigateToCommunityWorkout = {},
            navigateToWorkoutDetails = {},
            onSelectNavBarRoute = {}
        )
    }
}