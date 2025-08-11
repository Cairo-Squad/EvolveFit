package com.cairosquad.evolvefit.ui.screen.createWorkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CustomDropDownMenu
import com.cairosquad.evolvefit.design_system.component.ExerciseCard
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.UserProfileImage
import com.cairosquad.evolvefit.viewmodel.createWorkOut.CreateWorkoutViewModel
import com.cairosquad.evolvefit.viewmodel.createWorkOut.WorkOutEffect
import com.cairosquad.evolvefit.viewmodel.createWorkOut.WorkOutInteractionListener
import com.cairosquad.evolvefit.viewmodel.createWorkOut.WorkOutScreenState
import com.cairosquad.evolvefit.viewmodel.createWorkOut.WorkOutScreenState.CreateWorkoutStep
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage.ImageResource
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_addcircle
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_cross
import evolvefit.composeapp.generated.resources.ic_image
import evolvefit.composeapp.generated.resources.ic_search
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CreateWorkoutScreen(
    viewModel: CreateWorkoutViewModel = koinViewModel(),
    navigateBack: () -> Unit,
    navigateToCreateExercise: () -> Unit,
    navigateToWorkOuts: () -> Unit,
    navigateToAllExercises : () -> Unit
) {
    val state by viewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                WorkOutEffect.NavigateBack -> navigateBack()
                WorkOutEffect.NavigateToCreateExercise -> navigateToCreateExercise()
                WorkOutEffect.NavigateToAllExercises -> navigateToAllExercises()
                WorkOutEffect.NavigateToWorkouts -> navigateToWorkOuts()
            }
        }
    }
    when (state.currentStep) {
        CreateWorkoutStep.DETAILS -> {
            CreateWorkoutContent(
                state = state,
                listener = viewModel,
            )
        }

        CreateWorkoutStep.EXERCISES -> {
            AllExercisesContent(
                state = state,
                listener = viewModel
            )
        }
    }

}


@Composable
fun CreateWorkoutContent(
    state: WorkOutScreenState,
    listener: WorkOutInteractionListener,
) {
    val goals = listOf("Lose Weight", "Gain Muscle", "Stay Fit")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .windowInsetsPadding(WindowInsets.statusBars),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column {
            CustomAppBar(
                title = "",
                header = {
                    ActionIconButton(
                        icon = painterResource(Res.drawable.ic_cross),
                        contentDescription = "Cross",
                        tint = Theme.color.surfaces.onSurface,
                        onClick = {},
                    )
                },
                modifier = Modifier.padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )

            Column(
                modifier = Modifier.padding(bottom = 24.dp)
                    .padding(horizontal = 16.dp)
            ) {
                BasicText(
                    text = "Create Workout",
                    style = Theme.textStyle.headline.mediumMedium18.copy(
                        color = Theme.color.surfaces.onSurface
                    )
                )

                BasicText(
                    text = "Plan and customize your workout to match your \n fitness  goals.",
                    style = Theme.textStyle.label.smallRegular14.copy(
                        color = Theme.color.surfaces.onSurfaceVariant
                    ),
                )

            }

            UserProfileImage(
                image = state.image ?: ImageResource(Res.drawable.ic_image),
                isImagePickerOpen = state.isImagePickerOpen,
                onImagePickerDismiss = listener::onImagePickerDismiss,
                onImagePickerClick = listener::onImageClicked,
                onImageRetrieved = listener::onImageSelected,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )

            InputField(
                value = state.name,
                onValueChange = listener::onNameChanged,
                placeholder = "Enter workout name",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            CustomDropDownMenu(
                selectedText = state.goal ?: "",
                options = goals,
                placeholder = "Choose your goal",
                iconPainter = painterResource(Res.drawable.ic_arrow_down),
                onOptionSelected = { selectedGoal ->
                    listener.onGoalSelected(selectedGoal)
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            println("Selected goal: ${state.goal}")
            Box(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    .background(
                        color = Theme.color.surfaces.surfaceContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clip(RoundedCornerShape(8.dp))
            ) {
                InputField(
                    value = state.description,
                    onValueChange = {
                        if (it.length <= 3000) listener.onDescriptionChanged(it)
                    },
                    maxCharacters = 3000,
                    isSingleLine = false,
                    placeholder = "Enter description",
                    modifier = Modifier.height(124.dp).fillMaxWidth()
                )
                BasicText(
                    text = "${3000 - state.description.length} characters left",
                    style = Theme.textStyle.label.smallRegular12.copy(
                        color = Theme.color.surfaces.onSurfaceVariant
                    ),
                    modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 12.dp)
                        .padding(horizontal = 12.dp)
                )
            }
        }

        PrimaryButton(
            text = "Next",
            onClick = listener::onNextClicked,
            isEnabled = state.isNextEnabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .padding(bottom = 24.dp)
        )
    }
}

@Composable
fun AllExercisesContent(
    state: WorkOutScreenState,
    listener: WorkOutInteractionListener,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CustomAppBar(
                title = "Add Exercise",
                header = {
                    ActionIconButton(
                        icon = painterResource(Res.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Theme.color.surfaces.onSurface,
                        onClick = listener::onBackClicked
                    )
                },
                tail = {
                    ActionIconButton(
                        icon = painterResource(Res.drawable.ic_addcircle),
                        contentDescription = "Add",
                        tint = Theme.color.surfaces.onSurface,
                        onClick = listener::onAddClicked
                    )
                },
            )

            InputField(
                value = state.searchQuery,
                onValueChange = listener::onSearchQueryChanged,
                placeholder = "Search exercise name",
                leadingIcon = Res.drawable.ic_search,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            BasicText(
                text = "All Exercises",
                style = Theme.textStyle.label.smallRegular14.copy(
                    color = Theme.color.surfaces.onSurfaceVariant
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                items(state.filteredExercises) { exercise ->
                    val isChecked = state.selectedExerciseIds.contains(exercise.id.toLong())
                    ExerciseCard(
                        title = exercise.name,
                        model = exercise.imageUrl,
                        timeInSeconds = when (exercise.duration) {
                            is WorkOutScreenState.UiExerciseDuration.Time -> exercise.duration.seconds
                            else -> null
                        },
                        reps = when (exercise.duration) {
                            is WorkOutScreenState.UiExerciseDuration.RepsSets -> exercise.duration.reps
                            else -> null
                        },
                        sets = when (exercise.duration) {
                            is WorkOutScreenState.UiExerciseDuration.RepsSets -> exercise.duration.sets
                            else -> null
                        },
                        isChecked = isChecked,
                        onCheckedChange = {
                            listener.onExerciseCheckedChanged(exercise.id)
                        },
                    )
                }
            }
        }
        PrimaryButton(
            text = if (state.exerciseCount > 0)
                "Add ${state.exerciseCount} Exercise${if (state.exerciseCount > 1) "s" else ""}"
            else
                "Add Exercise",
            onClick = { listener.onAddWorkoutClicked() },
            isEnabled = state.exerciseCount > 0,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .padding(bottom = 24.dp)
        )
    }
}