package com.cairosquad.evolvefit.ui.screen.createWorkout.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.InputField
import com.cairosquad.evolvefit.design_system.component.StateMessage
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.component.RefreshBox
import com.cairosquad.evolvefit.ui.screen.createWorkout.content.component.AddExerciseButton
import com.cairosquad.evolvefit.ui.screen.createWorkout.content.component.ExerciseCardWithTick
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutInteractionListener
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutScreenState
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutScreenState.ScreenStatus
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.add_circle
import evolvefit.composeapp.generated.resources.add_exercise_title_
import evolvefit.composeapp.generated.resources.add_icon_desc_
import evolvefit.composeapp.generated.resources.all_exercises_title_
import evolvefit.composeapp.generated.resources.back_icon_desc_
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_search
import evolvefit.composeapp.generated.resources.im_no_internet
import evolvefit.composeapp.generated.resources.no_exercises_description
import evolvefit.composeapp.generated.resources.no_exercises_title
import evolvefit.composeapp.generated.resources.search_exercise_placeholder_
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AllExercisesContent(
    state: CreateWorkOutScreenState,
    listener: CreateWorkOutInteractionListener,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .statusBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
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
                        icon = painterResource(Res.drawable.add_circle),
                        contentDescription = stringResource(Res.string.add_icon_desc_),
                        tint = Theme.color.surfaces.onSurfaceVariant,
                        onClick = listener::onAddClicked
                    )
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                InputField(
                    value = state.searchQuery,
                    verticalPadding = 14.dp,
                    onValueChange = listener::onSearchQueryChanged,
                    placeholder = stringResource(Res.string.search_exercise_placeholder_),
                    leadingIcon = Res.drawable.ic_search,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                )

                RefreshBox(
                    isRefreshing = state.isRefreshing,
                    onRefresh = { listener.onRefresh() }
                ) {
                    Crossfade(
                        targetState = state.status,
                        animationSpec = tween(
                            durationMillis = 400,
                            easing = FastOutSlowInEasing
                        )
                    ) { status ->
                        when (status) {
                            ScreenStatus.LOADING -> {
                                CreateWorkoutLoadingScreen()
                            }

                            ScreenStatus.EMPTY -> {
                                StateMessage(
                                    image = painterResource(Res.drawable.im_no_internet),
                                    title = stringResource(Res.string.no_exercises_title),
                                    description = stringResource(Res.string.no_exercises_description),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }

                            ScreenStatus.SUCCESS -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                        .padding(
                                            bottom = 12.dp + 24.dp + 48.dp +
                                                    WindowInsets.navigationBars
                                                        .asPaddingValues().calculateBottomPadding()
                                        )
                                ) {
                                    BasicText(
                                        text = stringResource(Res.string.all_exercises_title_),
                                        style = Theme.textStyle.label.smallRegular14.copy(
                                            color = Theme.color.surfaces.onSurfaceVariant
                                        ),
                                        modifier = Modifier.padding(bottom = 6.dp)
                                    )
                                    state.filteredExercises.forEach { exercise ->
                                        ExerciseCardWithTick(
                                            title = exercise.name,
                                            time = "",
                                            model = exercise.images.firstOrNull() ?: "",
                                            isChecked = state.selectedExercises.any { it.id == exercise.id },
                                            onCheckedChange = {
                                                listener.onExerciseCheckedChanged(exercise)
                                            },
                                            measurementContent = { MeasurementRow(exercise.type) }
                                        )
                                    }
                                }
                            }

                            ScreenStatus.ERROR -> {
                                CreateWorkoutErrorScreen { listener.onRetryClicked() }
                            }
                        }
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = state.status == ScreenStatus.SUCCESS,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            AddExerciseButton(
                state = state,
                listener = listener
            )
        }
    }
}

