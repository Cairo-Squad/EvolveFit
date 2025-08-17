package com.cairosquad.evolvefit.ui.screen.workoutHistory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.ui.screen.workoutHistory.content.workoutHistoryScreenLoadingContent
import com.cairosquad.evolvefit.ui.screen.workoutHistory.content.workoutHistoryScreenSuccessContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.workoutHistory.WorkoutHistoryEffect
import com.cairosquad.evolvefit.viewmodel.workoutHistory.WorkoutHistoryInteractionListener
import com.cairosquad.evolvefit.viewmodel.workoutHistory.WorkoutHistoryScreenState
import com.cairosquad.evolvefit.viewmodel.workoutHistory.WorkoutHistoryViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.workout_history
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WorkoutHistoryScreen(
    navigateBack: () -> Unit,
    viewModel: WorkoutHistoryViewModel = koinViewModel()
) {

    val state by viewModel.screenState.collectAsStateWithLifecycle()

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            WorkoutHistoryEffect.NavigateBack -> {
                navigateBack()
            }
        }
    }

    WorkoutHistoryContent(
        state = state,
        listener = viewModel
    )
}

@Composable
private fun WorkoutHistoryContent(
    state: WorkoutHistoryScreenState,
    listener: WorkoutHistoryInteractionListener
) {
    Column(
        modifier = Modifier
            .background(Theme.color.surfaces.surface)
            .windowInsetsPadding(WindowInsets.systemBars)
            .fillMaxSize()
    ) {
        CustomAppBar(
            modifier = Modifier.padding(horizontal = 16.dp),
            title = stringResource(Res.string.workout_history),
            header = {
                Box(
                    modifier = Modifier.size(40.dp)
                        .clip(CircleShape)
                        .clickable(onClick = listener::onBackClicked)
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_back),
                        contentDescription = "Back Button",
                        colorFilter = ColorFilter.tint(Theme.color.surfaces.onSurface)
                    )
                }
            }
        )
        RefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = listener::onRefresh
        ) {
            LazyColumn(
                modifier = Modifier.navigationBarsPadding(),
                horizontalAlignment = Alignment.Start,
                contentPadding = PaddingValues(16.dp)
            ) {

                when (state.screenStatus) {
                    WorkoutHistoryScreenState.ScreenStatus.LOADING -> {
                        workoutHistoryScreenLoadingContent()
                    }

                    WorkoutHistoryScreenState.ScreenStatus.SUCCESS -> {
                        workoutHistoryScreenSuccessContent(state)
                    }

                    WorkoutHistoryScreenState.ScreenStatus.ERROR -> {

                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun WorkoutHistoryScreenPreview() {
    WorkoutHistoryScreen({})
}