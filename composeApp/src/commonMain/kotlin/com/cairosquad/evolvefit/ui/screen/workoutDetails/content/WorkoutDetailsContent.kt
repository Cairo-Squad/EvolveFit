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
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.DetailsCardsRow
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.Exercises
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.WorkoutDetailsText
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsInteractionListener
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_bookmark
import evolvefit.composeapp.generated.resources.ic_share
import evolvefit.composeapp.generated.resources.start_workout
import evolvefit.composeapp.generated.resources.workouts
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun WorkoutDetailsContent(
    workoutId: String,
    state: WorkoutDetailsScreenState,
    listener: WorkoutDetailsInteractionListener
) {
    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
    ) {
        CustomAppBar(
            modifier = Modifier.align(Alignment.TopCenter),
            title = "",
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Theme.color.surfaces.onSurface,
                    onClick = listener::onBackClick
                )
            },
            tail = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_bookmark),
                    contentDescription = "Bookmark",
                    tint = Theme.color.surfaces.onSurface,
                    onClick = { listener.onAddToFavoriteClick(workoutId) }
                )
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_share),
                    contentDescription = "Share",
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
                model = state.workoutImage,
                contentDescription = stringResource(Res.string.workouts),
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )

            WorkoutDetailsText(
                title = state.workoutTitle,
                description = state.workoutDescription,
                modifier = Modifier.padding(top = 16.dp),
            )

            DetailsCardsRow(
                modifier = Modifier.padding(top = 24.dp, bottom = 32.dp),
                level = state.level,
                exercisesNumber = state.exercises.size,
                estimatedTimeInSeconds = state.estimatedTimeInSeconds,
            )

            Exercises(
                exercises = state.exercises,
                onExerciseClick = { exercise ->
                    listener.onExerciseClick(exercise)
                }
            )

            BottomSheet(
                isVisible = state.selectedExercise != null,
                onDismiss = listener::onExerciseBottomSheetDismiss
            ) {
                ExerciseBottomSheetContent(
                    exercise = state.selectedExercise,
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
            onClick = { listener.onStartWorkoutClick(workoutId) },
            isEnabled = true
        )
    }
}

