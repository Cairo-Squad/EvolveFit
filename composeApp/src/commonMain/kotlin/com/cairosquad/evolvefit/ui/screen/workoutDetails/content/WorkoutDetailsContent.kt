package com.cairosquad.evolvefit.ui.screen.workoutDetails.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.DetailsCardsRow
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.Exercises
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.WorkoutDetailsText
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsInteractionListener
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.bookmark
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_bookmark
import evolvefit.composeapp.generated.resources.ic_share
import evolvefit.composeapp.generated.resources.share
import evolvefit.composeapp.generated.resources.start_workout
import evolvefit.composeapp.generated.resources.workouts
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun WorkoutDetailsContent(
    state: WorkoutDetailsScreenState,
    listener: WorkoutDetailsInteractionListener
) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
    ) {
        CustomAppBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding().zIndex(1f),
            title = "",
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.back),
                    tint = Theme.color.surfaces.onSurface,
                    onClick = listener::onBackClick
                )
            },
            tail = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_bookmark),
                    contentDescription = stringResource(Res.string.bookmark),
                    tint = Theme.color.surfaces.onSurface,
                    onClick = { listener.onAddToFavoriteClick(state.workout.workoutID) }
                )
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_share),
                    contentDescription = stringResource(Res.string.share),
                    tint = Theme.color.surfaces.onSurface,
                    onClick = listener::onShareClick
                )
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            NetworkImage(
                model = state.workout.workoutImage,
                contentDescription = stringResource(Res.string.workouts),
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )

            WorkoutDetailsText(
                title = state.workout.workoutTitle,
                description = state.workout.workoutDescription,
                modifier = Modifier.fillMaxWidth().padding(top=12.dp, bottom = 16.dp),
            )

            DetailsCardsRow(
                modifier = Modifier.padding(top = 24.dp, bottom = 32.dp),
                level = state.workout.level,
                exercisesNumber = state.workout.exercises.size,
                estimatedTimeInSeconds = state.workout.estimatedTimeInSeconds,
            )
            Exercises(
                exercises = state.workout.exercises,
                onExerciseClick = { exercise ->
                    listener.onExerciseClick(exercise)
                }
            )
            BottomSheet(
                isVisible = state.workout.selectedExercise != null,
                onDismiss = listener::onExerciseBottomSheetDismiss
            ) {
                ExerciseBottomSheetContent(
                    exercise = state.workout.selectedExercise,
                    onDismissBottomSheet = listener::onExerciseBottomSheetDismiss
                )
            }
            BottomSheet(
                isVisible = state.isShareClicked,
                onDismiss = listener::onShareClick
            ) {
                ShareBottomSheetContent(
                    onShareOptionClick = {},
                    onCopyLinkClick = { },
                    onShareWithCommunityClick = { }
                )
            }
        }
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            text = stringResource(Res.string.start_workout),
            onClick = { listener.onStartWorkoutClick(state.workout.workoutID) },
            isEnabled = true
        )
    }
}
@Preview
@Composable
fun WorkoutDetailsPreview() {
    val dummyState = WorkoutDetailsScreenState(
        isLoading = false,
        workout = WorkoutDetailsScreenState.Workout(
            workoutID = "1",
            workoutImage = "https://picsum.photos/600/400",
            workoutTitle = "Full Body Workout",
            workoutDescription = "A complete workout targeting all major muscle groups.",
            level = WorkoutDetailsScreenState.WorkoutLevel.INTERMEDIATE,
            estimatedTimeInSeconds = 1800,
            exercises = listOf(
                WorkoutDetailsScreenState.ExerciseUiState(
                    name = "Push Ups",
                    instructions = listOf(
                        "Place your hands on the floor.",
                        "Lower your body until your chest nearly touches the floor.",
                        "Push yourself back up."
                    ),
                    images = listOf(
                        "https://picsum.photos/200/200",
                        "https://picsum.photos/200/201"
                    ),
                    type = WorkoutDetailsScreenState.ExerciseType.Reps(15),
                    equipment = "Bodyweight",
                    focusAreas = listOf(
                        WorkoutDetailsScreenState.FocusArea.CORE,
                        WorkoutDetailsScreenState.FocusArea.SHOULDERS
                    )
                ),
                WorkoutDetailsScreenState.ExerciseUiState(
                    name = "Plank",
                    instructions = listOf(
                        "Place your forearms on the ground.",
                        "Keep your body straight.",
                        "Hold this position."
                    ),
                    images = listOf(
                        "https://picsum.photos/200/202",
                        "https://picsum.photos/200/203"
                    ),
                    type = WorkoutDetailsScreenState.ExerciseType.Duration(60),
                    equipment = "Bodyweight",
                    focusAreas = listOf(
                        WorkoutDetailsScreenState.FocusArea.ABS,
                        WorkoutDetailsScreenState.FocusArea.CORE
                    )
                )
            )
        ),
        isShareClicked = false,
        isFavorite = true
    )

    val dummyListener = object : WorkoutDetailsInteractionListener {
        override fun onBackClick() {}
        override fun onAddToFavoriteClick(workoutId: String) {}
        override fun onShareClick() {}
        override fun onExerciseClick(exercise: WorkoutDetailsScreenState.ExerciseUiState) {}
        override fun onExerciseBottomSheetDismiss() {}
        override fun onStartWorkoutClick(workoutId: String) {}
    }

    AppTheme {
        WorkoutDetailsContent(
            state = dummyState,
            listener = dummyListener
        )
    }
}
