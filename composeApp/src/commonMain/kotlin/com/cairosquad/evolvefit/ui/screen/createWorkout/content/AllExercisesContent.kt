package com.cairosquad.evolvefit.ui.screen.createWorkout.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutInteractionListener
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutScreenState
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutScreenState.ScreenStatus
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_exercise_button_
import evolvefit.composeapp.generated.resources.add_exercise_button_with_count_
import evolvefit.composeapp.generated.resources.add_exercise_title_
import evolvefit.composeapp.generated.resources.add_exercises_button_with_count_
import evolvefit.composeapp.generated.resources.add_icon_desc_
import evolvefit.composeapp.generated.resources.all_exercises_title_
import evolvefit.composeapp.generated.resources.back_icon_desc_
import evolvefit.composeapp.generated.resources.exercise_image
import evolvefit.composeapp.generated.resources.ic_addcircle
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_green_check_circle
import evolvefit.composeapp.generated.resources.ic_search
import evolvefit.composeapp.generated.resources.im_no_internet
import evolvefit.composeapp.generated.resources.search_exercise_placeholder_
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
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
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
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
                            .fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        items(state.filteredExercises) { exercise ->
                            ExerciseCardWithTick(
                                title = exercise.name,
                                time = "",
                                model = exercise.images.firstOrNull() ?: "",
                                isChecked = state.selectedExercises.any { it.id == exercise.id },
                                onCheckedChange = { isChecked ->
                                    listener.onExerciseCheckedChanged(exercise)
                                },
                                measurementContent = {
                                    MeasurementRow(exercise.type)
                                }
                            )
                        }
                    }
                }

                ScreenStatus.ERROR -> {
                    // Error UI
                }
            }
        }

        PrimaryButton(
            text = when {
                state.exerciseCount > 1 -> stringResource(
                    Res.string.add_exercises_button_with_count_,
                    state.exerciseCount
                )
                state.exerciseCount == 1 -> stringResource(
                    Res.string.add_exercise_button_with_count_,
                    state.exerciseCount
                )
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

@Composable
fun ExerciseCardWithTick(
    title: String,
    time: String,
    model: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    measurementContent: @Composable () -> Unit
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NetworkImage(
            modifier = Modifier
                .size(width = 88.dp, height = 68.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = model,
            contentDescription = stringResource(Res.string.exercise_image),
        )
        Column(
            modifier = Modifier.weight(1f)
                .padding(start = 12.dp),
        ) {
            Text(
                text = title,
                style = Theme.textStyle.body.mediumMedium14,
                color = Theme.color.surfaces.onSurface
            )
            if (time.isNotEmpty()) {
                Text(
                    text = time,
                    style = Theme.textStyle.label.smallRegular12,
                    color = Theme.color.surfaces.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            measurementContent()
        }

        CreateCustomTick(
            isChecked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun CreateCustomTick(
    isChecked: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    val backgroundColor by animateColorAsState(
        targetValue =
            if (isChecked) Theme.color.surfaces.surfaceContainer
            else Theme.color.surfaces.surfaceContainer.copy(alpha = 0.0f),
        animationSpec = tween(300)
    )

    val boxModifier = modifier
        .size(24.dp)
        .clip(CircleShape)
        .background(
            color = backgroundColor,
            shape = CircleShape
        )
        .then(
            if (!isChecked) {
                Modifier.border(
                    width = 1.dp,
                    color = Theme.color.surfaces.outlineVariant,
                    shape = CircleShape
                )
            } else Modifier
        )
        .toggleable(
            value = isChecked,
            onValueChange = onCheckedChange,
            role = Role.Checkbox
        )

    Box(
        modifier = boxModifier,
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = isChecked,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_green_check_circle),
                contentDescription = null,
                tint = Theme.color.brand.primary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}