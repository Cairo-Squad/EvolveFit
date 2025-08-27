package com.cairosquad.evolvefit.ui.screen.workoutDetails.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.SnackBar
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component.DetailsCardsRow
import com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component.Exercises
import com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component.WorkoutDetailsText
import com.cairosquad.evolvefit.ui.util.ScreenSize
import com.cairosquad.evolvefit.ui.util.Share
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsInteractionListener
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsScreenState

@Composable
fun WorkoutDetailsContent(
    state: WorkoutDetailsScreenState,
    listener: WorkoutDetailsInteractionListener
) {
    when (state.screenState) {
        WorkoutDetailsScreenState.ScreenState.Loading -> {
            WorkoutDetailsLoading(listener)
        }

        WorkoutDetailsScreenState.ScreenState.Success -> {
            WorkoutDetailsSuccess(
                state = state,
                listener = listener
            )
        }
    BottomSheet(
        isVisible = state.workout.selectedExercise != null,
        onDismiss = listener::onExerciseBottomSheetDismiss,
        modifier = Modifier.heightIn(max = ScreenSize.heightDp.dp * 0.95f),
    ) {
        ExerciseBottomSheetContent(
            exercise = state.workout.selectedExercise,
            onDismissBottomSheet = listener::onExerciseBottomSheetDismiss
        )
    }

    BottomSheet(
        isVisible = state.isShareClicked,
        onDismiss = listener::onShareBottomSheetDismiss
    ) {
        ShareBottomSheetContent(
            onShareOptionClick = { platform ->
                val workoutUrl = "https://evolvefit.com/workouts/${state.workout.workoutID}"
                shareToPlatform(platform, workoutUrl, onDismiss = listener::onShareClicked)
            },
            onCopyLinkClick = {},
            onShareWithCommunityClick = {
                listener.onShareWithCommunityClicked(state.workout.workoutID)
            }
        )
    }
    SnackBar(
        text = snackBarMessage ?: "",
        isVisible = isSnackBarVisible,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 24.dp)
    )

        WorkoutDetailsScreenState.ScreenState.Error -> {
            Text("Error")
        }
    }
}