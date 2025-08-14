package com.cairosquad.evolvefit.ui.screen.workoutDetails.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.DetailsCardsRow
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.Exercises
import com.cairosquad.evolvefit.ui.screen.workoutDetails.component.WorkoutDetailsText
import com.cairosquad.evolvefit.ui.util.Share
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsInteractionListener
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.back
import evolvefit.composeapp.generated.resources.bookmark
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_bookmark
import evolvefit.composeapp.generated.resources.ic_bookmark_big_filled
import evolvefit.composeapp.generated.resources.ic_share
import evolvefit.composeapp.generated.resources.share
import evolvefit.composeapp.generated.resources.start_workout
import evolvefit.composeapp.generated.resources.workouts
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WorkoutDetailsContent(
    state: WorkoutDetailsScreenState,
    listener: WorkoutDetailsInteractionListener
) {
    val listState = rememberLazyListState()
    val scrollOffsetThreshold = 200
    val isScrolled by remember {
        derivedStateOf { listState.firstVisibleItemScrollOffset > scrollOffsetThreshold }
    }

    val appBarBackground by animateColorAsState(
        targetValue = if (isScrolled) Theme.color.surfaces.surface
        else Theme.color.surfaces.onSurface.copy(alpha = 0f),
        label = "appBarBackground"
    )

    val iconTint by animateColorAsState(
        targetValue = if (isScrolled) Theme.color.surfaces.onSurface
        else Theme.color.surfaces.textColor,
        label = "iconTintAnim"
    )

    var isSnackBarVisible by remember { mutableStateOf(false) }
    var snackBarMessage by remember { mutableStateOf<String?>(null) }
    val snackBarText = state.snackBarMessageId?.let { id -> stringResource(id) }

    LaunchedEffect(state.snackBarMessageId) {
        state.snackBarMessageId?.let {
            snackBarMessage = snackBarText
            isSnackBarVisible = true
            delay(2000)
            isSnackBarVisible = false
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CustomAppBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .statusBarsPadding()
                .zIndex(1f)
                .background(appBarBackground),
            title = "",
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.back),
                    tint = iconTint,
                    onClick = listener::onClickBack
                )
            },
            tail = {
                ActionIconButton(
                    icon = if (state.isFavorite) painterResource(Res.drawable.ic_bookmark_big_filled)
                    else painterResource(Res.drawable.ic_bookmark),
                    contentDescription = stringResource(Res.string.bookmark),
                    tint = iconTint,
                    onClick = { listener.onClickAddToFavorite(state.workout.workoutID) }
                )
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_share),
                    contentDescription = stringResource(Res.string.share),
                    tint = iconTint,
                    onClick = listener::onClickShare
                )
            }
        )
        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(bottom = 60.dp)
                .fillMaxSize()
                .background(color = Theme.color.surfaces.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                NetworkImage(
                    model = state.workout.workoutImage,
                    contentDescription = stringResource(Res.string.workouts),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            item {
                WorkoutDetailsText(
                    title = state.workout.workoutTitle,
                    description = state.workout.workoutDescription,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 16.dp)
                )
            }

            item {
                DetailsCardsRow(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 24.dp, bottom = 32.dp),
                    level = state.workout.level,
                    exercisesNumber = state.workout.exercises.size,
                    estimatedTimeInSeconds = state.workout.estimatedTimeInSeconds,
                )
            }

            items(state.workout.exercises) { exercise ->
                Exercises(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    exercises = listOf(exercise),
                    onExerciseClick = { listener.onClickExercise(exercise) }
                )
            }
        }

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
                onDismiss = listener::onShareBottomSheetDismiss
            ) {
                ShareBottomSheetContent(
                    onShareOptionClick = { platform ->
                        val workoutUrl = "https://evolvefit.com/workouts/${state.workout.workoutID}"
                        shareToPlatform(platform, workoutUrl, onDismiss = listener::onClickShare)
                    },
                    onCopyLinkClick = {},
                    onShareWithCommunityClick = {
                        listener.onClickShareWithCommunity(state.workout.workoutID)
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

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            text = stringResource(Res.string.start_workout),
            onClick = { listener.onClickStartWorkout(state.workout.workoutID) },
            isEnabled = true
        )
    }
}

private fun shareToPlatform(platform: String, workoutUrl: String, onDismiss: () -> Unit) {
    when (platform) {
        "Messenger" -> Share.shareOnMessenger(workoutUrl) { onDismiss }
        "WhatsApp" -> Share.shareOnWhatsApp(workoutUrl) { onDismiss }
        "Telegram" -> Share.shareOnTelegram(workoutUrl) { onDismiss }
        "Instagram" -> Share.shareOnInstagram(workoutUrl) { onDismiss }
        "Facebook" -> Share.shareOnFacebook(workoutUrl) { onDismiss }
        "X" -> Share.shareOnX(workoutUrl) { onDismiss }
    }
}

@Preview
@Composable
fun WorkoutDetailsPreview() {
    val dummyState = WorkoutDetailsScreenState(
        isLoading = false,
        workout = WorkoutDetailsScreenState.Workout(
            workoutID = "1",
            workoutImage = "https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg",
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
                        "https://res.cloudinary.com/dqd5lvkpz/image/upload/v1755011701/evolveFit/images/exercises/747060f3-dca1-46dd-816e-e308eb57eb17-1755011700.png",
                        "https://res.cloudinary.com/dqd5lvkpz/image/upload/v1755011701/evolveFit/images/exercises/747060f3-dca1-46dd-816e-e308eb57eb17-1755011700.png"
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
                        "https://res.cloudinary.com/dqd5lvkpz/image/upload/v1755011701/evolveFit/images/exercises/747060f3-dca1-46dd-816e-e308eb57eb17-1755011700.png",
                        "https://res.cloudinary.com/dqd5lvkpz/image/upload/v1755011701/evolveFit/images/exercises/747060f3-dca1-46dd-816e-e308eb57eb17-1755011700.png"
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
        override fun onClickBack() {}
        override fun onClickAddToFavorite(workoutId: String) {}
        override fun onClickShare() {}
        override fun onClickExercise(exercise: WorkoutDetailsScreenState.ExerciseUiState) {}
        override fun onExerciseBottomSheetDismiss() {}
        override fun onShareBottomSheetDismiss() {}

        override fun onClickStartWorkout(workoutId: String) {}
        override fun onClickShareWithCommunity(workoutId: String) {}
        override fun onClickCopyLink(workoutId: String) {}
    }

    AppTheme {
        WorkoutDetailsContent(
            state = dummyState,
            listener = dummyListener
        )
    }
}
