package com.cairosquad.evolvefit.ui.screen.workout.content

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.ui.screen.workout.AddWorkoutBtn
import com.cairosquad.evolvefit.ui.screen.workout.content.compnent.WorkoutAppBar
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutInteractionListener
import com.cairosquad.evolvefit.viewmodel.workout.WorkoutScreenState
import org.jetbrains.compose.resources.stringResource

//@Composable
//fun WorkoutsScreenContent(
//    state: WorkoutScreenState,
//    listener: WorkoutInteractionListener
//) {
//    RefreshBox(
//        isRefreshing = state.isRefreshing,
//        onRefresh = listener::onRefresh
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(color = Theme.color.surfaces.surface)
//                .statusBarsPadding(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//
//            WorkoutAppBar(listener::onCommunityClicked)
//
//            WorkoutFocusAreaFilter(
//                focusArea = WorkoutScreenState.FocusAreaUiState.entries,
//                selectedFocusArea = state.selectedFocusArea,
//                onSelectFocusArea = listener::onFocusAreaSelected
//            )
//
//            Crossfade(
//                targetState = state.screenStatus,
//                animationSpec = tween(
//                    durationMillis = 400,
//                    easing = FastOutSlowInEasing
//                )
//            ) { status ->
//                when (status) {
//                    WorkoutScreenState.ScreenStatus.SUCCESS -> {
//                        Workouts(
//                            workouts = state.allWorkouts,
//                            selected = state.selectedFocusArea,
//                            onClickWorkout = listener::onWorkoutClicked
//                        )
//                    }
//
//                    WorkoutScreenState.ScreenStatus.LOADING -> {
//                        WorkoutsLoadingScreen()
//                    }
//
//                    WorkoutScreenState.ScreenStatus.FAIL -> {
//                        WorkoutsErrorScreen(
//                            message = state.errorMessage?.let { stringResource(it) },
//                            onRetry = listener::onRetryClicked
//                        )
//                    }
//
//                    WorkoutScreenState.ScreenStatus.EMPTY -> {
//
//                    }
//                }
//            }
//        }
//        if (state.screenStatus == WorkoutScreenState.ScreenStatus.SUCCESS) {
//            AddWorkoutBtn(
//                modifier = Modifier.padding(24.dp).align(Alignment.BottomEnd),
//                listener::onAddWorkoutClicked
//            )
//        }
//    }
//}
@Composable
fun WorkoutsScreenContent(
    state: WorkoutScreenState,
    listener: WorkoutInteractionListener
) {
    RefreshBox(
        isRefreshing = state.isRefreshing,
        onRefresh = listener::onRefresh
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
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
                        Column {
                            WorkoutFocusAreaFilter(
                                focusArea = WorkoutScreenState.FocusAreaUiState.entries,
                                selectedFocusArea = state.selectedFocusArea,
                                onSelectFocusArea = listener::onFocusAreaSelected
                            )
                            Workouts(
                                workouts = state.allWorkouts,
                                selected = state.selectedFocusArea,
                                onClickWorkout = listener::onWorkoutClicked
                            )
                        }
                    }

                    WorkoutScreenState.ScreenStatus.LOADING -> {
                        WorkoutsLoadingScreen() // جواها CategoriesShimmerRow
                    }

                    WorkoutScreenState.ScreenStatus.FAIL -> {
                        WorkoutsErrorScreen(
                            message = state.errorMessage?.let { stringResource(it) },
                            onRetry = listener::onRetryClicked
                        )
                    }

                    WorkoutScreenState.ScreenStatus.EMPTY -> {
                        // ممكن تضيف شاشة فارغة هنا
                    }
                }
            }
        }

        if (state.screenStatus == WorkoutScreenState.ScreenStatus.SUCCESS) {
            AddWorkoutBtn(
                modifier = Modifier.padding(24.dp).align(Alignment.BottomEnd),
                listener::onAddWorkoutClicked
            )
        }
    }
}
