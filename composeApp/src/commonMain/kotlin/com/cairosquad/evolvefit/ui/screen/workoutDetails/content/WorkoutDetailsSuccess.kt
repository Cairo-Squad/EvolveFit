package com.cairosquad.evolvefit.ui.screen.workoutDetails.content

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.SnackBar
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.theme.UpdateStatusBarIconsForTheme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component.DetailsCardsRow
import com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component.Exercises
import com.cairosquad.evolvefit.ui.screen.workoutDetails.content.component.WorkoutDetailsText
import com.cairosquad.evolvefit.ui.util.ScreenSize
import com.cairosquad.evolvefit.ui.util.Share
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsInteractionListener
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsScreenState
import com.cairosquad.evolvefit.viewmodel.workout_details.WorkoutDetailsViewModel
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

@Composable
fun WorkoutDetailsSuccess(
    state: WorkoutDetailsScreenState,
    listener: WorkoutDetailsInteractionListener
) {
    val listState = rememberLazyListState()
    val scrollOffsetThreshold = 200
    val isScrolled by remember {
        derivedStateOf {
            val firstItemIndex = listState.firstVisibleItemIndex
            val firstItemOffset = listState.firstVisibleItemScrollOffset
            (firstItemIndex * 200 + firstItemOffset) > scrollOffsetThreshold
        }
    }

    if (Theme.isDark.not() && isScrolled) {
        UpdateStatusBarIconsForTheme(false)
    } else {
        UpdateStatusBarIconsForTheme(true)
    }

    val appBarBackground by animateColorAsState(
        targetValue =
            if (isScrolled) Theme.color.surfaces.surface
            else Theme.color.surfaces.surface.copy(alpha = 0f),
        animationSpec = tween(1000)
    )

    val iconTint by animateColorAsState(
        targetValue = if (isScrolled) Theme.color.surfaces.onSurface
        else Theme.color.surfaces.textColor,
        animationSpec = tween(1000)
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
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .background(color = Theme.color.surfaces.surface),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = 68.dp)
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
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    title = state.workout.workoutTitle,
                    description = state.workout.workoutDescription
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

            item {
                Exercises(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 24.dp)
                        .fillMaxWidth(),
                    exercises = state.workout.exercises,
                    onExerciseClick = { listener.onExerciseClicked(it) }
                )
            }
        }
        BottomSheet(
            isVisible = state.workout.selectedExercise != null,
            onDismiss = listener::onExerciseBottomSheetDismiss,
            modifier = Modifier.heightIn(max = ScreenSize.heightDp.dp * 0.95f)
                .align(Alignment.BottomCenter)
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
                    val workoutUrl =
                        WorkoutDetailsViewModel.DEEP_LINK_BASE_URL + state.workout.workoutID
                    shareToPlatform(platform, workoutUrl, onDismiss = listener::onShareClicked)
                },
                onCopyLinkClick = { listener.onCopyLinkClicked(state.workout.workoutID) }
            )
        }
        SnackBar(
            text = snackBarMessage ?: "",
            isVisible = isSnackBarVisible,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )
        CustomAppBar(
            modifier = Modifier
                .background(appBarBackground)
                .statusBarsPadding()
                .align(Alignment.TopCenter),
            title = "",
            header = {
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_back),
                    contentDescription = stringResource(Res.string.back),
                    tint = iconTint,
                    onClick = listener::onBackClicked
                )
            },
            tail = {
                ActionIconButton(
                    icon = if (state.isFavorite) painterResource(Res.drawable.ic_bookmark_big_filled)
                    else painterResource(Res.drawable.ic_bookmark),
                    contentDescription = stringResource(Res.string.bookmark),
                    tint = iconTint,
                    onClick = {
                        listener.onToggleFavoriteClicked(
                            state.workout.workoutID,
                            state.isFavorite
                        )
                    }
                )
                ActionIconButton(
                    icon = painterResource(Res.drawable.ic_share),
                    contentDescription = stringResource(Res.string.share),
                    tint = iconTint,
                    onClick = listener::onShareClicked
                )
            }
        )
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .navigationBarsPadding()
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            text = stringResource(Res.string.start_workout),
            onClick = { listener.onStartWorkoutClicked(state.workout.workoutID) },
            isEnabled = true
        )
        SnackBar(
            text = stringResource(state.snackBarState.messageRes),
            isVisible = state.snackBarState.isVisible,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            icon = painterResource(state.snackBarState.iconRes),
            iconTint = Theme.color.brand.primary,
            paddingBottom = 0.dp,
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
