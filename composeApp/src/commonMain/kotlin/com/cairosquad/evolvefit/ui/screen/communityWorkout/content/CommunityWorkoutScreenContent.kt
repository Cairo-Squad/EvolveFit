package com.cairosquad.evolvefit.ui.screen.communityWorkout.content

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.min
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.WorkoutCard
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.ui.screen.communityWorkout.content.component.CommunityWorkoutAppBar
import com.cairosquad.evolvefit.ui.screen.workout.content.WorkoutsErrorScreen
import com.cairosquad.evolvefit.ui.screen.workout.content.WorkoutsLoadingScreen
import com.cairosquad.evolvefit.viewmodel.community_workout.CommunityWorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import org.jetbrains.compose.resources.stringResource

@Composable
fun CommunityWorkoutsScreenContent(
    state: WorkoutScreenState,
    listener: CommunityWorkoutInteractionListener,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .statusBarsPadding()
    ) {
        CommunityWorkoutAppBar(navigateBack)

        RefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = listener::onRefresh,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CommunityFocusAreaFilter(
                    focusArea = WorkoutScreenState.FocusAreaUiState.entries,
                    selectedFocusArea = state.selectedFocusArea,
                    onSelectFocusArea = listener::onFocusAreaSelected
                )

                Crossfade(
                    targetState = state.screenStatus,
                    animationSpec = tween(
                        durationMillis = 400,
                        easing = FastOutSlowInEasing
                    )
                ) { status ->
                    when (status) {
                        WorkoutScreenState.ScreenStatus.SUCCESS -> {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                modifier = Modifier.padding(vertical = 12.dp)
                            ) {
                                state.allWorkouts.forEach { workout ->
                                    val displayArea =
                                        if (state.selectedFocusArea == WorkoutScreenState.FocusAreaUiState.ALL)
                                            workout.focusArea
                                        else
                                            state.selectedFocusArea

                                    WorkoutCard(
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .clickable { listener.onWorkoutClicked(workout.id) },
                                        title = workout.title,
                                        duration = workout.durationInMinutes + " " +
                                                stringResource(Res.string.min),
                                        focusArea = stringResource(displayArea.nameResId),
                                        model = workout.imageUrl,
                                    )
                                }
                            }
                        }

                        WorkoutScreenState.ScreenStatus.LOADING -> {
                            WorkoutsLoadingScreen(false)
                        }

                        WorkoutScreenState.ScreenStatus.FAIL -> {
                            WorkoutsErrorScreen(
                                message = state.errorMessage?.let { stringResource(it) },
                                onRetry = listener::onRetryClicked
                            )
                        }

                        WorkoutScreenState.ScreenStatus.EMPTY -> {

                        }
                    }
                }
            }
        }
    }
}