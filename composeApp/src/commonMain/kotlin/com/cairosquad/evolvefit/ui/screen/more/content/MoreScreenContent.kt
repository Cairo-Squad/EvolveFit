package com.cairosquad.evolvefit.ui.screen.more.content

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.BottomSheet
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.design_system.util.NetworkImage
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.viewmodel.more.MoreInteractionListener
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.account
import evolvefit.composeapp.generated.resources.age
import evolvefit.composeapp.generated.resources.arabic
import evolvefit.composeapp.generated.resources.arrow_icon
import evolvefit.composeapp.generated.resources.art
import evolvefit.composeapp.generated.resources.calender
import evolvefit.composeapp.generated.resources.dark_mode
import evolvefit.composeapp.generated.resources.earth
import evolvefit.composeapp.generated.resources.english
import evolvefit.composeapp.generated.resources.favorites
import evolvefit.composeapp.generated.resources.height
import evolvefit.composeapp.generated.resources.ic_arrow_right
import evolvefit.composeapp.generated.resources.ic_bell
import evolvefit.composeapp.generated.resources.ic_bookmark_big
import evolvefit.composeapp.generated.resources.ic_profile
import evolvefit.composeapp.generated.resources.ic_ruler
import evolvefit.composeapp.generated.resources.icon_description
import evolvefit.composeapp.generated.resources.language
import evolvefit.composeapp.generated.resources.light_mode
import evolvefit.composeapp.generated.resources.logout
import evolvefit.composeapp.generated.resources.notification_settings
import evolvefit.composeapp.generated.resources.personal_information
import evolvefit.composeapp.generated.resources.profile_image
import evolvefit.composeapp.generated.resources.theme
import evolvefit.composeapp.generated.resources.weight
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
        Column(
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(Res.string.account),
                style = Theme.textStyle.label.smallRegular14,
                color = Theme.color.surfaces.onSurfaceVariant,
                modifier = Modifier.align(Alignment.Start).padding(top = 24.dp)
            )
            AccountRow(
                modifier = Modifier.clickable { listener.onClickPersonInformation() },
                icon = Res.drawable.ic_profile,
                title = stringResource(Res.string.personal_information)
            )
            AccountRow(
                modifier = Modifier.clickable { listener.onClickFavorites() },
                icon = Res.drawable.ic_bookmark_big,
                title = stringResource(Res.string.favorites)
            )
            AccountRow(
                modifier = Modifier.clickable { listener.onClickNotification() },
                icon = Res.drawable.ic_bell,
                title = stringResource(Res.string.notification_settings)
            )
            AccountRow(
                modifier = Modifier.clickable { listener.onClickTheme() },
                icon = Res.drawable.art,
                title = stringResource(Res.string.theme),
                text = if (state.currentTheme == MoreScreenState.Theme.DARK) stringResource(Res.string.dark_mode) else stringResource(Res.string.light_mode)
            )
            AccountRow(
                modifier = Modifier.clickable { listener.onClickLanguage() },
                icon = Res.drawable.earth,
                text = if (state.profile.preferredLanguage == Language.ENGLISH) stringResource(Res.string.english) else stringResource(
                    Res.string.arabic
                ),
                title = stringResource(Res.string.language)
            )
            AccountRow(
                modifier = Modifier.clickable { listener.onClickLogout() },
                icon = Res.drawable.logout,
                title = stringResource(Res.string.logout)
            )
        }
        BottomSheet(
            isVisible = state.isThemeBottomSheetEnabled,
            onDismiss = listener::onDismissThemeBottomSheet,
        ) {
            ThemeBottomSheetContent(
                state = state,
                onConfirm = { selectedTheme ->
                    listener.onConfirmChangeTheme(selectedTheme)
                }
            )
        }
        BottomSheet(
            isVisible = state.isLanguageBottomSheetEnabled,
            onDismiss = listener::onDismissLanguageBottomSheet,
        ) {
            LanguageBottomSheetContent(
                state = state,
                onConfirm = { selectedLanguage -> listener.onConfirmChangeLanguage(selectedLanguage) }
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
            .clip(RoundedCornerShape(8.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        MeasurementCard(
            modifier = Modifier.weight(1f),
            icon = Res.drawable.ic_ruler,
            name = stringResource(Res.string.height),
            value = height.toString()
        )
        Box(modifier = Modifier.border(width = 1.dp, color = Theme.color.surfaces.outlineVariant))
        MeasurementCard(
            modifier = Modifier.weight(1f),
            icon = Res.drawable.weight,
            name = stringResource(Res.string.weight),
            value = weight.toString()
        )
        Box(modifier = Modifier.border(width = 1.dp, color = Theme.color.surfaces.outlineVariant))
        MeasurementCard(
            modifier = Modifier.weight(1f),
            icon = Res.drawable.calender,
            name = stringResource(Res.string.age),
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
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier.padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(Res.string.icon_description, name),
                tint = Theme.color.brand.primary,
                modifier = Modifier.size(16.dp)
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
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NetworkImage(
            model = userImage,
            contentDescription = stringResource(Res.string.profile_image),
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
        modifier = modifier
            .background( color = Theme.color.surfaces.surfaceContainer , shape = RoundedCornerShape(8.dp) )
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
            contentDescription = stringResource(Res.string.arrow_icon),
            tint = Theme.color.surfaces.onSurfaceVariant,
            modifier = Modifier.size(16.dp)
        )
    }

}
