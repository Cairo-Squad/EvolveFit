package com.cairosquad.evolvefit.ui.screen.editProfile.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.domain.entity.Profile
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.choose_your_main_goal
import evolvefit.composeapp.generated.resources.confirm
import evolvefit.composeapp.generated.resources.goal_gain_weight
import evolvefit.composeapp.generated.resources.goal_lose_weight
import evolvefit.composeapp.generated.resources.goal_stay_in_shape
import evolvefit.composeapp.generated.resources.save
import evolvefit.composeapp.generated.resources.select_goal_description
import evolvefit.composeapp.generated.resources.select_goal_title
import org.jetbrains.compose.resources.stringResource
@Composable
fun MainGoalBottomSheet(
    selectedGoal: Profile.FitnessGoal,
    isGoalBottomSheetOpen: Boolean = true,
    onGoalBottomSheetDismiss: () -> Unit,
    onGoalChange: ( Profile.FitnessGoal) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomSheet(
        isVisible = isGoalBottomSheetOpen,
        onDismiss = onGoalBottomSheetDismiss,
        modifier = modifier.fillMaxWidth()
    ) {
        MainGoalBottomSheetContent(
            selectedGoal = selectedGoal,
            onGoalChange = onGoalChange,
            onGoalBottomSheetDismiss = onGoalBottomSheetDismiss,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun MainGoalBottomSheetContent(
    selectedGoal: Profile.FitnessGoal,
    onGoalChange:  (Profile.FitnessGoal) -> Unit,
    onGoalBottomSheetDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        RegisterHeader(
            modifier = Modifier.padding(bottom = 16.dp),
            title = stringResource(Res.string.choose_your_main_goal),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Profile.FitnessGoal.entries.forEach { goal ->
                val goalText = when (goal) {
                    Profile.FitnessGoal.LOSE_WEIGHT -> stringResource(Res.string.goal_lose_weight)
                    Profile.FitnessGoal.GAIN_WEIGHT -> stringResource(Res.string.goal_gain_weight)
                    Profile.FitnessGoal.STAY_IN_SHAPE -> stringResource(Res.string.goal_stay_in_shape)
                }
                CheckboxItem(
                    text = goalText,
                    isChecked = selectedGoal == goal,
                    onCheckedChange = { onGoalChange(goal) },
                )
            }
        }

        PrimaryButton(
            text = stringResource(Res.string.confirm),
            onClick = onGoalBottomSheetDismiss
        )
    }
}
