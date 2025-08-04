package com.cairosquad.evolvefit.ui.screen.onBoarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun NotificationSettingsContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        var isChecked by remember { mutableStateOf(false) }
        OnboardingHeader(
            title = "Notification Settings",
            description = "Select the notifications you want to receive"
        )
        Column(modifier = Modifier.padding(top = 16.dp).padding(horizontal = 16.dp)) {
            CheckboxItem(
                text = "Workout Reminder",
                description = "A reminder for your next workout session.",
                isChecked = !isChecked,
                onCheckedChange = { isChecked = !isChecked },
                style = CheckboxStyle.Switch
            )
            CheckboxItem(
                modifier = Modifier.padding(top = 16.dp),
                text = "Water Reminder",
                description = "A reminder to drink water regularly.",
                isChecked = !isChecked,
                onCheckedChange = { isChecked = !isChecked },
                style = CheckboxStyle.Switch
            )
            CheckboxItem(
                modifier = Modifier.padding(top = 16.dp),
                text = "Body Weight Reminder",
                description = "A reminder to log your weight weekly.",
                isChecked = !isChecked,
                onCheckedChange = { isChecked = !isChecked },
                style = CheckboxStyle.Switch
            )
            CheckboxItem(
                modifier = Modifier.padding(top = 16.dp),
                text = "Achievements & Challenges",
                description = "About new achievements and challenges.",
                isChecked = !isChecked,
                onCheckedChange = { isChecked = !isChecked },
                style = CheckboxStyle.Switch
            )
        }
    }
}

@Preview
@Composable
fun NotificationSettingsContentPreview() {
    AppTheme(isDarkTheme = true) {
        NotificationSettingsContent()
    }
}