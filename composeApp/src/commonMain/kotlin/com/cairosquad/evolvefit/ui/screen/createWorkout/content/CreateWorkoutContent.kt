package com.cairosquad.evolvefit.ui.screen.createWorkout.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CustomDropDownMenu
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.component.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.createWorkout.content.component.WorkoutImage
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutInteractionListener
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutScreenState
import com.cairosquad.evolvefit.viewmodel.create_workout.CreateWorkOutScreenState.WorkoutLevel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.advanced
import evolvefit.composeapp.generated.resources.beginner
import evolvefit.composeapp.generated.resources.characters_left
import evolvefit.composeapp.generated.resources.choose_level
import evolvefit.composeapp.generated.resources.create_workout_subtitle_
import evolvefit.composeapp.generated.resources.create_workout_title_
import evolvefit.composeapp.generated.resources.cross_icon_desc_
import evolvefit.composeapp.generated.resources.enter_description_
import evolvefit.composeapp.generated.resources.enter_workout_name_
import evolvefit.composeapp.generated.resources.ic_arrow_down
import evolvefit.composeapp.generated.resources.ic_cross
import evolvefit.composeapp.generated.resources.intermediate
import evolvefit.composeapp.generated.resources.next_button_
import evolvefit.composeapp.generated.resources.upload_image
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CreateWorkoutContent(
    state: CreateWorkOutScreenState,
    listener: CreateWorkOutInteractionListener,
) {
    val workoutGoals = WorkoutLevel.entries.toTypedArray()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .windowInsetsPadding(WindowInsets.statusBars)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column {
            CustomAppBar(
                title = "",
                header = {
                    ActionIconButton(
                        icon = painterResource(Res.drawable.ic_cross),
                        contentDescription = stringResource(Res.string.cross_icon_desc_),
                        tint = Theme.color.surfaces.onSurface,
                        onClick = listener::onExitClicked,
                    )
                },
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .padding(horizontal = 16.dp)
            )

            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .padding(horizontal = 16.dp)
            ) {
                BasicText(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = stringResource(Res.string.create_workout_title_),
                    style = Theme.textStyle.headline.mediumMedium18.copy(
                        color = Theme.color.surfaces.onSurface
                    )
                )

                BasicText(
                    text = stringResource(Res.string.create_workout_subtitle_),
                    style = Theme.textStyle.label.smallRegular14.copy(
                        color = Theme.color.surfaces.onSurfaceVariant
                    ),
                )

            }

            WorkoutImage(
                image = state.image,
                isImagePickerOpen = state.isImagePickerOpen,
                onImagePickerDismiss = listener::onImagePickerDismiss,
                onImagePickerClick = listener::onImageClicked,
                onImageRetrieved = listener::onImageSelected,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .align(Alignment.CenterHorizontally)

            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                text = stringResource(Res.string.upload_image),
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            InputField(
                value = state.name,
                onValueChange = listener::onNameChanged,
                placeholder = stringResource(Res.string.enter_workout_name_),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            CustomDropDownMenu(
                selectedText = state.level?.let { toDisplayName(it) } ?: "",
                options = workoutGoals.map { toDisplayName(it) },
                placeholder = stringResource(Res.string.choose_level),
                iconPainter = painterResource(Res.drawable.ic_arrow_down),
                onOptionSelected = { selectedGoal ->
                    listener.onGoalSelected(selectedGoal)
                },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
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
                    placeholder = stringResource(Res.string.enter_description_),
                    modifier = Modifier
                        .height(124.dp)
                        .fillMaxWidth()
                )
                BasicText(
                    text = stringResource(
                        Res.string.characters_left,
                        3000 - state.description.trim().length
                    ),
                    style = Theme.textStyle.label.smallRegular12.copy(
                        color = Theme.color.surfaces.onSurfaceVariant
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 12.dp)
                        .padding(horizontal = 12.dp)
                )
            }
        }

        PrimaryButton(
            text = stringResource(Res.string.next_button_),
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
fun toDisplayName(level : WorkoutLevel): String {
    return when (level) {
        WorkoutLevel.BEGINNER -> stringResource(Res.string.beginner)
        WorkoutLevel.INTERMEDIATE -> stringResource(Res.string.intermediate)
        WorkoutLevel.ADVANCED ->  stringResource(Res.string.advanced)
    }
}

