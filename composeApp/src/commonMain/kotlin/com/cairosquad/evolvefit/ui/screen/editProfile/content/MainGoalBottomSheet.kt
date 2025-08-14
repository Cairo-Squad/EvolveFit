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
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.save
import evolvefit.composeapp.generated.resources.select_goal_description
import evolvefit.composeapp.generated.resources.select_goal_title
import org.jetbrains.compose.resources.stringResource
@Composable
fun MainGoalBottomSheet(
    selectedGoal:String,
    isGoalBottomSheetOpen: Boolean=true,
    onGoalBottomSheetDismiss: () -> Unit,
    onGoalChange: (String) -> Unit,
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
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun MainGoalBottomSheetContent(
    selectedGoal: String,
    onGoalChange: (String) -> Unit,
    onGoalBottomSheetDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        RegisterHeader(
            modifier = Modifier.padding(bottom = 16.dp),
            title = stringResource(Res.string.select_goal_title),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RegisterScreenState.Goal.entries.forEach { goal ->
                CheckboxItem(
                    text = goal.name,
                    description = goal.name,
                    isChecked = selectedGoal == goal.name,
                    onCheckedChange = { onGoalChange(goal.name) },
                )
            }
        }

        PrimaryButton(
            text = stringResource(Res.string.save),
            onClick = onGoalBottomSheetDismiss
        )
    }
}