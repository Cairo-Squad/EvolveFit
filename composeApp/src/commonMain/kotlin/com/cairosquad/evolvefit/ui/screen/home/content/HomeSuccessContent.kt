package com.cairosquad.evolvefit.ui.screen.home.content

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.WorkoutCard
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.ui.component.CaloriesNutritionCard
import com.cairosquad.evolvefit.ui.component.WaterNutritionCard
import com.cairosquad.evolvefit.ui.screen.home.content.component.HomeProgressBox
import com.cairosquad.evolvefit.ui.screen.home.content.component.SaveButton

import com.cairosquad.evolvefit.viewmodel.home.HomeInteractionListener
import com.cairosquad.evolvefit.viewmodel.home.HomeScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.hello_user
import evolvefit.composeapp.generated.resources.just_for_you
import evolvefit.composeapp.generated.resources.profile_picture
import evolvefit.composeapp.generated.resources.ready_text_female
import evolvefit.composeapp.generated.resources.ready_text_male
import evolvefit.composeapp.generated.resources.today_nutrition
import org.jetbrains.compose.resources.stringResource


@Composable
 fun HomeSuccessContent(
    state: HomeScreenState,
    listener: HomeInteractionListener,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Theme.color.surfaces.surface)
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp)
    ) {
        HomeUserHeader(
            user = state.user,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp)
        )

        HomeProgressBox(
            progress = state.weeklyProgress,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp)
        )

        HomeSection(
            title = stringResource(Res.string.today_nutrition),
            visibilityKey = state.nutritionVisibility,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp)
        ) {
            SimpleNutritionRow(
                caloriesValue = state.caloriesCount,
                caloriesGoal = state.caloriesGoal,
                waterValue = state.waterCount,
                waterGoal = state.waterGoal
            )
        }

        HomeSection(
            title = stringResource(Res.string.just_for_you),
            visibilityKey = state.personalizedWorkouts.isNotEmpty(),
            modifier = Modifier
                .padding(bottom = 32.dp),
            isPaddedStart = true
        ) {
            PersonalizedWorkouts(
                workouts = state.personalizedWorkouts,
                onWorkoutClick = listener::onWorkoutClicked,
                onSavedWorkoutClick = listener::onSavedWorkoutClicked
            )
        }
    }
}

@Composable
private fun SimpleNutritionRow(
    caloriesValue: UInt,
    caloriesGoal: UInt,
    waterValue: Float,
    waterGoal: Float,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CaloriesNutritionCard(
            value = caloriesValue,
            goal = caloriesGoal,
            modifier = Modifier
                .weight(1f)
        )

        WaterNutritionCard(
            value = waterValue,
            goal = waterGoal,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
private fun PersonalizedWorkouts(
    workouts: List<HomeScreenState.HomeWorkoutUiState>,
    onWorkoutClick: (String) -> Unit,
    onSavedWorkoutClick: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp
        )
    ) {
        items(workouts) { workout ->
            Box(
                modifier = Modifier
                    .width(268.dp)
                    .height(172.dp)
                    .clickable(onClick = { onWorkoutClick(workout.id) })
            ) {
                WorkoutCard(
                    title = workout.name,
                    duration = "${workout.durationInMins} Min", // TODO: convert to string resources
                    focusArea = workout.type,
                    model = workout.imageUrl,
                    modifier = Modifier
                        .fillMaxSize()
                )

                SaveButton(
                    isSaved = workout.isSaved,
                    onClick = { onSavedWorkoutClick(workout.id, workout.isSaved) },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                )
            }
        }
    }
}


@Composable
private fun HomeUserHeader(
    user: HomeScreenState.HomeUserUiState?,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(user != null) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                model = user?.profilePictureUrl ?: "",
                contentDescription = stringResource(Res.string.profile_picture),
                contentScale = ContentScale.Crop,
                placeholderImageSize = DpSize(40.dp, 40.dp),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(Res.string.hello_user, user?.name ?: ""),
                    style = Theme.textStyle.body.mediumMedium12,
                    color = Theme.color.surfaces.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = stringResource(
                        resource = if (user?.gender == Profile.Gender.FEMALE) {
                            Res.string.ready_text_female
                        } else {
                            Res.string.ready_text_male
                        }
                    ),
                    style = Theme.textStyle.body.mediumMedium12,
                    color = Theme.color.surfaces.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
