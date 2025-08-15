package com.cairosquad.evolvefit.ui.screen.profile

import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.screen.profile.content.MoreScreenContent
import com.cairosquad.evolvefit.viewmodel.profile.MoreEffect
import com.cairosquad.evolvefit.viewmodel.profile.MoreViewModel
import com.cairosquad.evolvefit.viewmodel.profile.MoreScreenState
import com.cairosquad.evolvefit.domain.model.Language

@Composable
fun MoreScreen(
    navigateToPersonInformation: () -> Unit,
    navigateToFavorites: () -> Unit,
    navigateToNotificationSettings: () -> Unit,
    onLanguageChanged: (Language) -> Unit,
    onThemeChanged: (MoreScreenState.Theme) -> Unit,
    onLogout: () -> Unit,
    viewModel: MoreViewModel = koinViewModel(),

    ) {
    val state by viewModel.screenState.collectAsState()

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is MoreEffect.NavigateToPersonInformation -> {
                navigateToPersonInformation()
            }
            is MoreEffect.NavigateToFavorites -> {
                navigateToFavorites()
            }
            is MoreEffect.NavigateToNotificationSettings -> {
                navigateToNotificationSettings()
            }
            is MoreEffect.ChangeLanguage -> {
                onLanguageChanged(effect.language)
            }
            is MoreEffect.ChangeTheme -> {
                onThemeChanged(effect.theme)
            }
            is MoreEffect.Logout -> {
                onLogout()
            }
        }
    }

    MoreScreenContent(
        state = state,
        listener = viewModel
    )
}