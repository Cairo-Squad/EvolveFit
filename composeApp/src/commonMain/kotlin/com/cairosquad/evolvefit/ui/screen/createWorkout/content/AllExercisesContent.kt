package com.cairosquad.evolvefit.ui.screen.createWorkout.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.ExerciseCard
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.viewmodel.createWorkOut.CreateWorkOutInteractionListener
import com.cairosquad.evolvefit.viewmodel.createWorkOut.CreateWorkOutScreenState
import com.cairosquad.evolvefit.viewmodel.createWorkOut.CreateWorkOutScreenState.ScreenStatus
import com.cairosquad.evolvefit.viewmodel.onboarding.models.UiImage
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_exercise_button_
import evolvefit.composeapp.generated.resources.add_exercise_button_with_count_
import evolvefit.composeapp.generated.resources.add_exercise_title_
import evolvefit.composeapp.generated.resources.add_exercises_button_with_count_
import evolvefit.composeapp.generated.resources.add_icon_desc_
import evolvefit.composeapp.generated.resources.all_exercises_title_
import evolvefit.composeapp.generated.resources.back_icon_desc_
import evolvefit.composeapp.generated.resources.ic_addcircle
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_empty_workout
import evolvefit.composeapp.generated.resources.ic_error
import evolvefit.composeapp.generated.resources.ic_search
import evolvefit.composeapp.generated.resources.im_no_internet
import evolvefit.composeapp.generated.resources.search_exercise_placeholder_
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun AllExercisesContent(
    state: CreateWorkOutScreenState,
    listener: CreateWorkOutInteractionListener,
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
                title = stringResource(Res.string.add_exercise_title_),
                header = {
                    ActionIconButton(
                        icon = painterResource(Res.drawable.ic_back),
                        contentDescription = stringResource(Res.string.back_icon_desc_),
                        tint = Theme.color.surfaces.onSurface,
                        onClick = listener::onBackClicked
                    )
                },
                tail = {
                    ActionIconButton(
                        icon = painterResource(Res.drawable.ic_addcircle),
                        contentDescription = stringResource(Res.string.add_icon_desc_),
                        tint = Theme.color.surfaces.onSurface,
                        onClick = listener::onAddClicked
                    )
                },
            )

            InputField(
                value = state.searchQuery,
                onValueChange = listener::onSearchQueryChanged,
                placeholder = stringResource(Res.string.search_exercise_placeholder_),
                leadingIcon = Res.drawable.ic_search,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            BasicText(
                text = stringResource(Res.string.all_exercises_title_),
                style = Theme.textStyle.label.smallRegular14.copy(
                    color = Theme.color.surfaces.onSurfaceVariant
                ),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Box() {
                when (state.status) {
                    ScreenStatus.LOADING -> {
                        // Loading indicator
                    }
                    ScreenStatus.EMPTY -> {
                        StateMessage(
                            image = painterResource(Res.drawable.im_no_internet),
                            title = "No exercises found",
                            description = "Try adjusting your search terms or make sure the exercise name is spelled correctly.",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    ScreenStatus.SUCCESS -> {
                        LazyColumn(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        ) {
                            items(state.filteredExercises) { exercise ->
                                val isChecked = state.selectedExercises.any { it.id == exercise.id }

                                ExerciseCard(
                                    title = exercise.name,
                                    model = exercise.imageUrls.toString(),
                                    time = "0"
//                                    timeInSeconds = when (exercise.specification) {
//                                        is Exercise.Specification.Reps -> null
//                                        is Exercise.Specification.Time -> exercise.specification.timeInSeconds
//                                    },
//                                    reps = when (exercise.specification) {
//                                        is Exercise.Specification.Reps -> exercise.specification.reps
//                                        is Exercise.Specification.Time -> null
//                                    },
//                                    sets = null,
//                                    isChecked = isChecked,
//                                    onCheckedChange = {
//                                        listener.onExerciseCheckedChanged(exercise)
//                                    },
                                )
                            }
                        }
                    }
                    ScreenStatus.ERROR -> {
                        // Error UI
                    }
                }
            }
        }
        PrimaryButton(
            text = when {
                state.exerciseCount > 1 -> stringResource(Res.string.add_exercises_button_with_count_, state.exerciseCount)
                state.exerciseCount == 1 -> stringResource(Res.string.add_exercise_button_with_count_, state.exerciseCount)
                else -> stringResource(Res.string.add_exercise_button_)
            },
            onClick = { listener.onAddWorkoutClicked() },
            isEnabled = state.exerciseCount > 0,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .padding(bottom = 24.dp)
        )
    }
}

@Preview()
@Composable
fun PreviewAllExercisesContent_Empty() {
    AllExercisesContent(
        state = CreateWorkOutScreenState(
            searchQuery = "",
            filteredExercises = emptyList(),
            selectedExercises = emptyList(),
            exerciseCount = 0,
            status = ScreenStatus.EMPTY
        ),
        listener = object : CreateWorkOutInteractionListener {
            override fun onNameChanged(newName: String) {
                TODO("Not yet implemented")
            }

            override fun onGoalSelected(goal: String) {
                TODO("Not yet implemented")
            }

            override fun onDescriptionChanged(desc: String) {
                TODO("Not yet implemented")
            }

            override fun onImageClicked() {
                TODO("Not yet implemented")
            }

            override fun onNextClicked() {
                TODO("Not yet implemented")
            }

            override fun onBackClicked() {}
            override fun onAddClicked() {}
            override fun onSearchQueryChanged(query: String) {}
            override fun onExerciseCheckedChanged(exercise: Exercise) {}
            override fun onAddWorkoutClicked() {}
            override fun onImageSelected(image: UiImage) {
                TODO("Not yet implemented")
            }

            override fun onImagePickerDismiss() {
                TODO("Not yet implemented")
            }
        }
    )
}

@Preview()
@Composable
fun PreviewAllExercisesContent_Success() {
    AllExercisesContent(
        state = CreateWorkOutScreenState(
            searchQuery = "",
            filteredExercises = listOf(
                Exercise(
                    id = "1",
                    name = "Push Ups",
                    imageUrls = listOf("https://via.placeholder.com/150"),
                    specification = Exercise.Specification.Reps(reps = 10),
                    instructions = TODO(),
                    equipment = TODO(),
                    focusAreas = TODO(),
                    estimatedTimeInSeconds = TODO()
                ),
                Exercise(
                    id = "2",
                    name = "Plank",
                    imageUrls = listOf("https://via.placeholder.com/150"),
                    specification = Exercise.Specification.Time(timeInSeconds = 60),
                    instructions = TODO(),
                    equipment = TODO(),
                    focusAreas = TODO(),
                    estimatedTimeInSeconds = TODO()
                )
            ),
            selectedExercises = emptyList(),
            exerciseCount = 0,
            status = ScreenStatus.SUCCESS
        ),
        listener = object : CreateWorkOutInteractionListener {
            override fun onNameChanged(newName: String) {
            }

            override fun onGoalSelected(goal: String) {
            }

            override fun onDescriptionChanged(desc: String) {
            }

            override fun onImageClicked() {}

            override fun onNextClicked() {}

            override fun onBackClicked() {}
            override fun onAddClicked() {}
            override fun onSearchQueryChanged(query: String) {}
            override fun onExerciseCheckedChanged(exercise: Exercise) {}
            override fun onAddWorkoutClicked() {}
            override fun onImageSelected(image: UiImage) {}

            override fun onImagePickerDismiss() {}
        }
    )
}
