package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RegisterScreenContentSelectNotificationSittings(
    state: RegisterScreenState,
    listener: RegisterInteractionListener,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(top = 16.dp).fillMaxSize()) {
        OnboardingHeader(
            title = "Notification Settings",
            description = "Select the notifications you want to receive"
        )
        Column(modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)) {
            CheckboxItem(
                text = "Workout Reminder",
                description = "A reminder for your next workout session.",
                isChecked = state.isWorkoutReminderEnabled,
                onCheckedChange = { listener.onWorkoutReminderToggled(enabled = !state.isWorkoutReminderEnabled) },
                style = CheckboxStyle.Switch
            )
            CheckboxItem(
                modifier = Modifier.padding(top = 16.dp),
                text = "Water Reminder",
                description = "A reminder to drink water regularly.",
                isChecked = state.isWaterReminderEnabled,
                onCheckedChange = { listener.onWaterReminderToggled(enabled = !state.isWaterReminderEnabled) },
                style = CheckboxStyle.Switch
            )
            CheckboxItem(
                modifier = Modifier.padding(top = 16.dp),
                text = "Body Weight Reminder",
                description = "A reminder to log your weight weekly.",
                isChecked = state.isBodyWeightReminderEnabled,
                onCheckedChange = { listener.onBodyWeightReminderToggled(enabled = !state.isBodyWeightReminderEnabled) },
                style = CheckboxStyle.Switch
            )
            CheckboxItem(
                modifier = Modifier.padding(top = 16.dp),
                text = "Achievements & Challenges",
                description = "About new achievements and challenges.",
                isChecked = state.isChallengesReminderEnabled,
                onCheckedChange = { listener.onChallengesReminderToggled(enabled = !state.isChallengesReminderEnabled) },
                style = CheckboxStyle.Switch
            )
        }
    }
}