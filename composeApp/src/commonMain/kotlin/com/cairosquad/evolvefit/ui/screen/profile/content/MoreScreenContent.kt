package com.cairosquad.evolvefit.ui.screen.profile.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.component.CheckboxItem
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.viewmodel.profile.MoreInteractionListener
import com.cairosquad.evolvefit.viewmodel.profile.MoreScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.dashboard
import evolvefit.composeapp.generated.resources.ic_arrow_right
import evolvefit.composeapp.generated.resources.ic_bell
import evolvefit.composeapp.generated.resources.ic_bookmark_big
import evolvefit.composeapp.generated.resources.ic_count
import evolvefit.composeapp.generated.resources.ic_cross
import evolvefit.composeapp.generated.resources.ic_person
import evolvefit.composeapp.generated.resources.ic_ruler
import evolvefit.composeapp.generated.resources.ic_workout
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun MoreScreenContent(
    state: MoreScreenState,
    listener: MoreInteractionListener
) {
    Column(
        modifier = Modifier.fillMaxSize().background(color = Theme.color.surfaces.surface)
            .systemBarsPadding().padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProfileInfo(
            state.profile.image,
            state.profile.name,
            state.profile.email,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        PersonInfo(
            weight = state.profile.weight,
            height = state.profile.height,
            age = state.profile.age
        )
        Text(
            text = "Account",
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.onSurfaceVariant
        )
        AccountRow(
            modifier = Modifier.clickable { listener.onClickPersonInformation() },
            icon = Res.drawable.ic_person,
            title = "Personal Information"
        )
        AccountRow(
            modifier = Modifier.clickable { listener.onClickFavorites() },
            icon = Res.drawable.ic_bookmark_big,
            title = "Favorites"
        )
        AccountRow(
            modifier = Modifier.clickable { listener.onClickNotification() },
            icon = Res.drawable.ic_bell,
            title = "Notification Settings"
        )
        AccountRow(
            modifier = Modifier.clickable { listener.onClickTheme() },
            icon = Res.drawable.ic_cross,
            title = "Theme"
        )
        AccountRow(
            modifier = Modifier.clickable { listener.onClickLanguage() },
            icon = Res.drawable.ic_person,
            title = "Language"
        )
        AccountRow(
            modifier = Modifier.clickable { listener.onClickLogout() },
            icon = Res.drawable.ic_person,
            title = "Logout"
        )
        BottomSheet(
            isVisible = state.isThemeBottomSheetEnabled,
            onDismiss = listener::onDismissThemeBottomSheet,
        ) {
            ThemeBottomSheetContent(
                state = state,
                onThemeSelected = { selectedTheme ->
                    listener.onChangeTheme(selectedTheme)
                },
                onConfirm = { listener.onDismissThemeBottomSheet() }
            )
        }
        BottomSheet(
            isVisible = state.isLanguageBottomSheetEnabled,
            onDismiss = listener::onDismissLanguageBottomSheet,
        ) {
            LanguageBottomSheetContent(
                state = state,
                onLanguageSelected = { selectedLanguage -> listener.onChangeLanguage(selectedLanguage) },
                onConfirm = { listener.onDismissLanguageBottomSheet() }
            )
        }
        BottomSheet(
            isVisible = state.isLogoutBottomSheetEnabled,
            onDismiss = listener::onDismissLogoutBottomSheet,
        ) {
            LogoutBottomSheetContent(
                onLogout = listener::onLogout,
                )
        }
    }
}

@Composable
fun PersonInfo(
    weight: Float,
    height: Float,
    age: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(color = Theme.color.surfaces.surfaceContainer)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        MeasurementCard(
            modifier = Modifier.weight(1f),
            icon = Res.drawable.ic_ruler,
            name = "Height",
            value = height.toString()
        )
        Box(modifier = Modifier.border(width = 1.dp, color = Theme.color.surfaces.outlineVariant))
        MeasurementCard(
            modifier = Modifier.weight(1f),
            icon = Res.drawable.ic_workout,
            name = "Weight",
            value = weight.toString()
        )
        Box(modifier = Modifier.border(width = 1.dp, color = Theme.color.surfaces.outlineVariant))
        MeasurementCard(
            modifier = Modifier.padding(bottom = 12.dp).weight(1f),
            icon = Res.drawable.ic_count,
            name = "Age",
            value = age.toString()
        )
    }
}

@Composable
fun MeasurementCard(
    icon: DrawableResource,
    name: String,
    value: String,
    modifier: Modifier = Modifier,
    measurementUnit: String = ""
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.Start) {
        Row(
            modifier = Modifier.padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "$name icon",

                )
            Text(
                text = name,
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
        Text(
            text = "$value $measurementUnit",
            style = Theme.textStyle.body.mediumMedium14,
            color = Theme.color.surfaces.onSurfaceContainer
        )
    }
}

@Composable
fun ProfileInfo(
    userImage: String,
    userName: String,
    userEmail: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        NetworkImage(
            model = userImage,
            contentDescription = "Profile Image",
            modifier = Modifier.size(80.dp).clip(shape = CircleShape)
        )
        Text(
            text = userName,
            color = Theme.color.surfaces.onSurfaceContainer,
            style = Theme.textStyle.label.mediumMedium14
        )
        Text(
            text = userEmail,
            color = Theme.color.surfaces.onSurfaceVariant,
            style = Theme.textStyle.label.smallRegular12
        )
    }
}

@Composable
fun AccountRow(
    icon: DrawableResource,
    title: String,
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Row(
        modifier = modifier.background(color = Theme.color.surfaces.surfaceContainer)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "",
            tint = Theme.color.surfaces.onSurfaceContainer,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = title,
            style = Theme.textStyle.label.smallRegular14,
            color = Theme.color.surfaces.onSurfaceContainer,
            modifier = Modifier.weight(1f)
        )
        if (text != "") {
            Text(
                text = text,
                style = Theme.textStyle.label.smallRegular12,
                color = Theme.color.surfaces.onSurfaceVariant
            )
        }
        Icon(
            painter = painterResource(Res.drawable.ic_arrow_right),
            contentDescription = "icon",
            tint = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.size(16.dp)
        )
    }

}
@Composable
fun ThemeBottomSheetContent(
    state: MoreScreenState,
    onThemeSelected: (MoreScreenState.Theme) -> Unit,
    onConfirm: () -> Unit
) {
    val selectedTheme = state.currentTheme

    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Choose Theme",
            style = Theme.textStyle.label.mediumMedium16,
            color = Theme.color.surfaces.onSurface
        )
        Text(
            text = "Select your preferred look and feel for the app.",
            style = Theme.textStyle.label.mediumMedium12,
            color = Theme.color.surfaces.onSurfaceVariant
        )

        CheckboxItem(
            text = "Dark Mode",
            isChecked = selectedTheme == MoreScreenState.Theme.DARK,
            icon = Res.drawable.dashboard,
            onCheckedChange = { onThemeSelected(MoreScreenState.Theme.DARK) }
        )
        CheckboxItem(
            text = "Light Mode",
            isChecked = selectedTheme == MoreScreenState.Theme.LIGHT,
            icon = Res.drawable.dashboard,
            onCheckedChange = { onThemeSelected(MoreScreenState.Theme.LIGHT) }
        )

        PrimaryButton(
            text = "Confirm",
            onClick = onConfirm,
            modifier = Modifier.padding(bottom = 16.dp),
            isEnabled = true,
            enabledTextColor = Theme.color.brand.onPrimary,
            textStyle = Theme.textStyle.body.mediumMedium14,
        )
    }
}


@Composable
fun LanguageBottomSheetContent(
    state: MoreScreenState,
    onLanguageSelected: (Language) -> Unit,
    onConfirm: () -> Unit
) {
    val selectedLanguage = state.currentLanguage

Column {
        Text(
            text = "Choose Language",
            style = Theme.textStyle.label.mediumMedium16,
            color = Theme.color.surfaces.onSurface,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "This language will be used throughout the app.",
            style = Theme.textStyle.label.mediumMedium12,
            color = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        CheckboxItem(
            text = "English",
            isChecked = selectedLanguage == Language.ENGLISH,
            icon = Res.drawable.dashboard,
            onCheckedChange = { }
        )
        CheckboxItem(
            text = "Arabic",
            isChecked = selectedLanguage == Language.ARABIC,
            icon = Res.drawable.dashboard,
            onCheckedChange = { onLanguageSelected(Language.ARABIC)}
        )
        PrimaryButton(
            text = "Confirm",
            onClick = onConfirm,
            modifier = Modifier.padding(bottom = 16.dp),
            isEnabled = true,
            enabledTextColor = Theme.color.brand.onPrimary,
            textStyle = Theme.textStyle.body.mediumMedium14,
        )
    }
}

@Composable
fun LogoutBottomSheetContent(
    onLogout: () -> Unit
    ) {
    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Confirm Logout",
            style = Theme.textStyle.label.mediumMedium16,
            color = Theme.color.surfaces.onSurface,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = "Are you sure you want to log out?",
            style = Theme.textStyle.label.mediumMedium12,
            color = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        PrimaryButton(
            text = "Logout",
            onClick = onLogout,
            modifier = Modifier.padding(bottom = 16.dp),
            isEnabled = true,
            enabledTextColor = Theme.color.brand.onPrimary,
            textStyle = Theme.textStyle.body.mediumMedium14,
        )
    }
}