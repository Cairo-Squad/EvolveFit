package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.gain_weight
import evolvefit.composeapp.generated.resources.gain_weight_desc
import evolvefit.composeapp.generated.resources.lose_weight
import evolvefit.composeapp.generated.resources.lose_weight_desc
import evolvefit.composeapp.generated.resources.select_goal_description
import evolvefit.composeapp.generated.resources.select_goal_title
import evolvefit.composeapp.generated.resources.stay_in_shape
import evolvefit.composeapp.generated.resources.stay_in_shape_desc
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreenContentChooseYourGoal(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().background(Theme.color.surfaces.surface)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        RegisterHeader(
            modifier = Modifier.padding(top = 16.dp),
            title = stringResource(Res.string.select_goal_title),
            description = stringResource(Res.string.select_goal_description)
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RegisterScreenState.Goal.entries.forEach { goal ->
                CheckboxItem(
                    text = determineGoal(goal),
                    description = determineGoalDescription(goal),
                    isChecked = state.selectedGoal == goal,
                    onCheckedChange = { listener.onGoalClicked(goal) },
                )
            }
        }
    }
}

@Composable
private fun determineGoal(goal: RegisterScreenState.Goal): String = when (goal) {
    RegisterScreenState.Goal.LoseWeight -> stringResource(Res.string.lose_weight)
    RegisterScreenState.Goal.GainWeight -> stringResource(Res.string.gain_weight)
    RegisterScreenState.Goal.StayInShape -> stringResource(Res.string.stay_in_shape)
}

@Composable
private fun determineGoalDescription(goal: RegisterScreenState.Goal): String = when (goal) {
    RegisterScreenState.Goal.LoseWeight -> stringResource(Res.string.lose_weight_desc)
    RegisterScreenState.Goal.GainWeight -> stringResource(Res.string.gain_weight_desc)
    RegisterScreenState.Goal.StayInShape -> stringResource(Res.string.stay_in_shape_desc)

}