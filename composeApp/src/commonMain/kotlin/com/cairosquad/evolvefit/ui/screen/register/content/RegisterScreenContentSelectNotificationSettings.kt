package com.cairosquad.evolvefit.ui.screen.register.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.CheckboxStyle
import com.cairosquad.evolvefit.viewmodel.register.RegisterInteractionListener
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.challenges_reminder_desc
import evolvefit.composeapp.generated.resources.challenges_reminder_title
import evolvefit.composeapp.generated.resources.notification_settings_description
import evolvefit.composeapp.generated.resources.notification_settings_title
import evolvefit.composeapp.generated.resources.water_reminder_desc
import evolvefit.composeapp.generated.resources.water_reminder_title
import evolvefit.composeapp.generated.resources.weight_reminder_desc
import evolvefit.composeapp.generated.resources.weight_reminder_title
import evolvefit.composeapp.generated.resources.workout_reminder_desc
import evolvefit.composeapp.generated.resources.workout_reminder_title
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

//@Composable
//fun RegisterScreenContentSelectNotificationSettings(
//    state: RegisterScreenState,
//    listener: RegisterInteractionListener,
//    modifier: Modifier = Modifier
//) {
//
//    val notificationItems = remember(state) { getNotificationItems(state) }
//
//    Column(modifier = modifier.padding(horizontal = 16.dp).padding(top = 16.dp).fillMaxSize()) {
//        RegisterHeader(
//            title = stringResource(Res.string.notification_settings_title),
//            description = stringResource(Res.string.notification_settings_description)
//        )
//        notificationItems.forEachIndexed { index, item ->
//            CheckboxItem(
//                modifier = if (index == 0) Modifier.padding(top = 24.dp) else Modifier.padding(top = 16.dp),
//                text = stringResource(item.titleRes),
//                description = stringResource(item.descriptionRes),
//                isChecked = item.isChecked,
//                onCheckedChange = { listener.onNotificationToggled(item.type) },
//                style = CheckboxStyle.Switch
//            )
//        }
//    }
//}
//
//private fun getNotificationItems(
//    state: RegisterScreenState
//): List<NotificationItem> = listOf(
//    NotificationItem(
//        titleRes = Res.string.workout_reminder_title,
//        descriptionRes = Res.string.workout_reminder_desc,
//        isChecked = state.notificationSettings.isWorkoutReminderEnabled,
//        type = RegisterScreenState.NotificationType.Workout
//    ),
//    NotificationItem(
//        titleRes = Res.string.water_reminder_title,
//        descriptionRes = Res.string.water_reminder_desc,
//        isChecked = state.notificationSettings.isWaterReminderEnabled,
//        type = RegisterScreenState.NotificationType.Water
//    ),
//    NotificationItem(
//        titleRes = Res.string.weight_reminder_title,
//        descriptionRes = Res.string.weight_reminder_desc,
//        isChecked = state.notificationSettings.isBodyWeightReminderEnabled,
//        type = RegisterScreenState.NotificationType.BodyWeight
//    ),
//    NotificationItem(
//        titleRes = Res.string.challenges_reminder_title,
//        descriptionRes = Res.string.challenges_reminder_desc,
//        isChecked = state.notificationSettings.isChallengesReminderEnabled,
//        type = RegisterScreenState.NotificationType.Challenges
//    )
//)
//
//private data class NotificationItem(
//    val titleRes: StringResource,
//    val descriptionRes: StringResource,
//    val isChecked: Boolean,
//    val type: RegisterScreenState.NotificationType
//)