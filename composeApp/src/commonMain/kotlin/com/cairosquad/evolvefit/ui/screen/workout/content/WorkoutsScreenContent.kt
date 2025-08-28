package com.cairosquad.evolvefit.ui.screen.workout.content

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.ui.screen.workout.AddWorkoutBtn
import com.cairosquad.evolvefit.ui.screen.workout.WorkoutsEmptyScreen
import com.cairosquad.evolvefit.ui.screen.workout.content.compnent.WorkoutAppBar
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import org.jetbrains.compose.resources.stringResource

@Composable
fun WorkoutsScreenContent(
    state: WorkoutScreenState,
    listener: WorkoutInteractionListener
) {
    val listState = rememberLazyListState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
    ) {
        RefreshBox(
            modifier = Modifier.fillMaxSize(),
            isRefreshing = state.isRefreshing,
            onRefresh = listener::onRefresh
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                WorkoutAppBar(listener::onCommunityClicked)

                Crossfade(
                    targetState = state.screenStatus,
                    animationSpec = tween(
                        durationMillis = 400,
                        easing = FastOutSlowInEasing
                    )
                ) { status ->
                    when (status) {
                        WorkoutScreenState.ScreenStatus.SUCCESS -> {
                            WorkoutsSuccessScreen(
                                lazyListState = listState,
                                state = state,
                                listener = listener
                            )
                        }

                        WorkoutScreenState.ScreenStatus.LOADING -> {
                            WorkoutsLoadingScreen(isItemsLoading = state.isItemsLoading)
                        }

                        WorkoutScreenState.ScreenStatus.FAIL -> {
                            WorkoutsErrorScreen(
                                message = state.errorMessage?.let { stringResource(it) },
                                onRetry = listener::onRetryClicked
                            )
                        }

                        WorkoutScreenState.ScreenStatus.EMPTY -> {
                            WorkoutsEmptyScreen(
                                lazyListState = listState,
                                state = state,
                                listener = listener
                            )
                        }
                    }
                }
            }
        }
        if (state.screenStatus != WorkoutScreenState.ScreenStatus.FAIL) {
            AddWorkoutBtn(
                modifier = Modifier
                    .padding(24.dp)
                    .align(Alignment.BottomEnd),
                listener::onAddWorkoutClicked
            )
        }
    }
}
