package com.cairosquad.evolvefit.ui.screen.workoutDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.Chip
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.UiImageDisplayer
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsEffect
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsInteractionListener
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsScreenState
import com.cairosquad.evolvefit.viewmodel.workoutDetails.WorkoutDetailsViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.close
import evolvefit.composeapp.generated.resources.ic_app_logo
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_bookmark
import evolvefit.composeapp.generated.resources.ic_check_mark
import evolvefit.composeapp.generated.resources.ic_person
import evolvefit.composeapp.generated.resources.ic_share
import evolvefit.composeapp.generated.resources.ic_time
import evolvefit.composeapp.generated.resources.start_workout
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WorkoutDetailsScreen(
    workoutId: String,
    navigateBack: () -> Unit,
    navigateToPlayWorkout: () -> Unit,
    viewModel: WorkoutDetailsViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()
    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is WorkoutDetailsEffect.NavigateToPlayWorkout -> navigateToPlayWorkout()
            is WorkoutDetailsEffect.NavigateBack -> navigateBack()
        }
    }
    WorkoutDetailsContent(state = state, listener = viewModel)
}

@Composable
private fun WorkoutDetailsContent(
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
                    onClick = listener::onAddToFavoriteClick
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
            UiImageDisplayer(
                image = state.workoutImage,
                contentDescription = "workout image",
                modifier = Modifier.fillMaxWidth().height(200.dp)
            )
            WorkoutDetailsText(
                title = state.workoutTitle,
                description = state.workoutDescription,
                modifier = Modifier.padding(top = 16.dp),
            )
            DetailsCardsRow(
                detailsCards = state.detailsCardsInfo,
                modifier = Modifier.padding(top = 24.dp, bottom = 32.dp)
            )
            Exercises(
                exercises = state.exercises,
                onExerciseClick = { exerciseName ->
                    listener.onExerciseClick(exerciseName)
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
        }
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            text = stringResource(Res.string.start_workout),
            onClick = listener::onStartWorkoutClick,
            isEnabled = true
        )
    }
}

@Composable
private fun WorkoutDetailsText(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    titleColor: Color = Theme.color.surfaces.onSurface,
    descriptionColor: Color = Theme.color.surfaces.onSurfaceVariant,
    titleStyle: TextStyle = Theme.textStyle.headline.mediumMedium18,
    descriptionStyle: TextStyle = Theme.textStyle.label.smallRegular14
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(vertical = 16.dp)
    ) {
        Text(
            text = title,
            color = titleColor,
            style = titleStyle,
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = description,
            color = descriptionColor,
            style = descriptionStyle,
        )
    }
}

@Composable
private fun DetailsCardsRow(
    detailsCards: List<WorkoutDetailsScreenState.DetailsCardInfo>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val detailsIcons = listOf(
            painterResource(Res.drawable.ic_person),
            painterResource(Res.drawable.ic_time),
            painterResource(Res.drawable.ic_check_mark)

        )
        detailsCards.forEachIndexed { index, info ->
            DetailCard(
                modifier = Modifier.weight(1f),
                icon = detailsIcons[index],
                value = info.value,
                label = info.label
            )
        }
    }
}

@Composable
private fun DetailCard(
    icon: Painter,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(93.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Theme.color.surfaces.outlineVariant,
                shape = RoundedCornerShape(8.dp)
            )
            .background(Theme.color.surfaces.surfaceContainer),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            painter = icon,
            contentDescription = "detail icon",
            tint = Theme.color.brand.primary,
            modifier = Modifier
                .size(20.dp)
                .padding(bottom = 8.dp, top = 16.dp)
        )
        Text(
            text = value,
            color = Theme.color.surfaces.onSurface,
            style = Theme.textStyle.title.mediumMedium14,
            modifier = Modifier.padding(bottom = 2.dp)
        )
        Text(
            text = label,
            color = Theme.color.surfaces.onSurfaceVariant,
            style = Theme.textStyle.label.smallRegular12,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
private fun Exercises(
    modifier: Modifier = Modifier,
    exercises: List<WorkoutDetailsScreenState.Exercise>,
    onExerciseClick: (String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Exercises ",
                color = Theme.color.surfaces.onSurface,
                style = Theme.textStyle.label.mediumMedium16
            )
            Text(
                text = "(${exercises.size})",
                color = Theme.color.surfaces.outline,
                style = Theme.textStyle.label.smallRegular12
            )
        }
        exercises.forEach { exercise ->
            ExerciseRow(
                exerciseImage = exercise.exerciseImage,
                exerciseName = exercise.name,
                exerciseMeasurement = exercise.type,
                onExerciseClick = { onExerciseClick(exercise.name) }
            )
        }
    }
}

@Composable
private fun ExerciseRow(
    exerciseImage: UiImage,
    exerciseName: String,
    exerciseMeasurement: WorkoutDetailsScreenState.ExerciseType,
    onExerciseClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp)
            .clickable(onClick = onExerciseClick),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UiImageDisplayer(
            image = exerciseImage,
            contentDescription = "exercise image",
            modifier = Modifier
                .width(88.dp)
                .height(68.dp)
                .clip(shape = RoundedCornerShape(8.dp)),
        )
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = exerciseName,
                style = Theme.textStyle.body.mediumMedium14,
                color = Theme.color.surfaces.onSurfaceContainer
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                Icon(
                    painter = when (exerciseMeasurement) {
                        is WorkoutDetailsScreenState.ExerciseType.Duration -> painterResource(
                            Res.drawable.ic_time
                        )

                        is WorkoutDetailsScreenState.ExerciseType.Reps -> painterResource(Res.drawable.ic_time)
                    },
                    contentDescription = "Measurement icon",
                    tint = Theme.color.surfaces.onSurfaceVariant
                )
                Text(
                    text = when (exerciseMeasurement) {
                        is WorkoutDetailsScreenState.ExerciseType.Duration -> "${exerciseMeasurement.seconds} Second"
                        is WorkoutDetailsScreenState.ExerciseType.Reps -> "X${exerciseMeasurement.count}"
                    },
                    color = Theme.color.surfaces.onSurfaceVariant,
                    style = Theme.textStyle.label.smallRegular12
                )
            }
        }
    }
}

@Composable
private fun ExerciseBottomSheetContent(
    exercise: WorkoutDetailsScreenState.ExerciseDetails?,
    onDismissBottomSheet: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        exercise?.let {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = exercise.name,
                    style = Theme.textStyle.title.largeBold16,
                    color = Theme.color.surfaces.onSurface,
                )
                ImageCarousel(exercise.image)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Instructions",
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    exercise.instructions.forEach { instruction ->
                        BulletPointText(instruction)
                    }
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Equipment",
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    exercise.equipments.forEach { equipment ->
                        BulletPointText(equipment)
                    }
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Focus Area",
                    style = Theme.textStyle.headline.largeBold18,
                    color = Theme.color.surfaces.onSurface,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    exercise.focusAreas.forEach { area ->
                        Chip(
                            title = area
                        )
                    }
                }
            }
        }
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End)
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            text = stringResource(Res.string.close),
            onClick = onDismissBottomSheet,
            isEnabled = true
        )
    }
}

@Composable
private fun BulletPointText(instruction: String) {
    Row {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(
                    color = Theme.color.surfaces.onSurfaceVariant,
                    shape = CircleShape
                )
                .padding(top = 6.dp, end = 6.dp)
        )
        Text(
            text = instruction,
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }
}

@Composable
private fun ImageCarousel(
    images: List<UiImage>,
    modifier: Modifier = Modifier
) {
    var currentIndex by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(Color.Black)
    ) {
        UiImageDisplayer(
            image = images[currentIndex],
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
        )

        IconButton(
            onClick = {
                currentIndex = if (currentIndex > 0) currentIndex - 1 else images.size - 1
            },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 8.dp)
                .background(
                    Theme.color.surfaces.onSurfaceAt2,
                    CircleShape
                )
                .padding(vertical = 8.dp, horizontal = 16.dp)

        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_arrow_down),
                contentDescription = "Previous",
                tint = if (currentIndex == 0) Theme.color.surfaces.onSurfaceVariant else Theme.color.surfaces.textColor,
                modifier = Modifier.size(16.dp)
            )
        }

        IconButton(
            onClick = {
                currentIndex = if (currentIndex < images.size - 1) currentIndex + 1 else 0
            },
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 8.dp)
                .background(
                    Theme.color.surfaces.onSurfaceAt2,
                    CircleShape
                )
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_arrow_down),
                contentDescription = "Next",
                tint = Theme.color.surfaces.textColor,
                modifier = Modifier.size(16.dp)
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .background(
                    Theme.color.surfaces.onSurfaceAt2,
                    RoundedCornerShape(24.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_time),
                contentDescription = "time icon",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun WorkoutDetailsScreenPreview() {
    AppTheme {
        WorkoutDetailsContent(
            state = sampleWorkoutDetailsState(),
            listener = previewListener
        )
    }
}

@Preview
@Composable
fun WorkoutDetailsWithExerciseSelectedPreview() {
    AppTheme {
        WorkoutDetailsContent(
            state = sampleWorkoutDetailsState().copy(
                selectedExercise = sampleExerciseDetails()
            ),
            listener = previewListener
        )
    }
}

@Preview
@Composable
fun WorkoutDetailsEmptyStatePreview() {
    AppTheme(isDarkTheme = true) {
        WorkoutDetailsContent(
            state = WorkoutDetailsScreenState(
                workoutTitle = "Empty Workout",
                workoutDescription = "No exercises added yet",
                detailsCardsInfo = emptyList(),
                exercises = emptyList()
            ),
            listener = previewListener
        )
    }
}

private fun sampleWorkoutDetailsState(): WorkoutDetailsScreenState {
    return WorkoutDetailsScreenState(
        workoutImage = UiImage.ImageResource(Res.drawable.ic_app_logo),
        workoutTitle = "Full Body Strength Training",
        workoutDescription = "A comprehensive workout targeting all major muscle groups. Perfect for building strength and endurance.",
        detailsCardsInfo = listOf(
            WorkoutDetailsScreenState.DetailsCardInfo(
                value = "Beginner",
                label = "Level"
            ),
            WorkoutDetailsScreenState.DetailsCardInfo(
                value = "45 min",
                label = "Duration"
            ),
            WorkoutDetailsScreenState.DetailsCardInfo(
                value = "12",
                label = "Exercises"
            )
        ),
        exercises = sampleExercises()
    )
}

private fun sampleExercises(): List<WorkoutDetailsScreenState.Exercise> {
    return listOf(
        WorkoutDetailsScreenState.Exercise(
            name = "Push-ups",
            exerciseImage = UiImage.ImageResource(Res.drawable.ic_app_logo),
            type = WorkoutDetailsScreenState.ExerciseType.Reps(15)
        ),
        WorkoutDetailsScreenState.Exercise(
            name = "Plank",
            exerciseImage = UiImage.ImageResource(Res.drawable.ic_app_logo),
            type = WorkoutDetailsScreenState.ExerciseType.Duration(60)
        ),
        WorkoutDetailsScreenState.Exercise(
            name = "Squats",
            exerciseImage = UiImage.ImageResource(Res.drawable.ic_app_logo),
            type = WorkoutDetailsScreenState.ExerciseType.Reps(20)
        ),
        WorkoutDetailsScreenState.Exercise(
            name = "Jumping Jacks",
            exerciseImage = UiImage.ImageResource(Res.drawable.ic_app_logo),
            type = WorkoutDetailsScreenState.ExerciseType.Duration(45)
        ),
        WorkoutDetailsScreenState.Exercise(
            name = "Burpees",
            exerciseImage = UiImage.ImageResource(Res.drawable.ic_app_logo),
            type = WorkoutDetailsScreenState.ExerciseType.Reps(10)
        )
    )
}

private fun sampleExerciseDetails(): WorkoutDetailsScreenState.ExerciseDetails {
    return WorkoutDetailsScreenState.ExerciseDetails(
        name = "Push-ups",
        instructions = listOf(
            "Start in a plank position with arms straight",
            "Lower your body until chest nearly touches the floor",
            "Push back up to starting position",
            "Keep your core engaged throughout the movement",
            "Maintain a straight line from head to heels"
        ),
        image = listOf(
            UiImage.ImageResource(Res.drawable.ic_app_logo),
            UiImage.ImageResource(Res.drawable.ic_app_logo),
            UiImage.ImageResource(Res.drawable.ic_app_logo)
        ),
        duration = null,
        reps = 15,
        equipments = listOf(
            "Exercise Mat (optional)",
            "No equipment required"
        ),
        focusAreas = listOf(
            "Chest",
            "Shoulders",
            "Triceps",
            "Core"
        )
    )
}

// Preview Listener
private val previewListener = object : WorkoutDetailsInteractionListener {
    override fun onBackClick() {}
    override fun onShareClick() {}
    override fun onAddToFavoriteClick() {}
    override fun onExerciseClick(exerciseName: String) {}
    override fun onExerciseBottomSheetDismiss() {}
    override fun onStartWorkoutClick() {}
}