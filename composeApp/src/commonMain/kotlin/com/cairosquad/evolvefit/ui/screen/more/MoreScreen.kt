package com.cairosquad.evolvefit.ui.screen.more

import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.screen.more.content.MoreScreenContent
import com.cairosquad.evolvefit.viewmodel.more.MoreEffect
import com.cairosquad.evolvefit.viewmodel.more.MoreViewModel
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState
import com.cairosquad.evolvefit.domain.model.Language

@Composable
fun MoreScreen(
    navigateToFavorites: () -> Unit,
    navigateToNotificationSettings: () -> Unit,
    onLanguageChanged: (Language) -> Unit,
    onThemeChanged: (MoreScreenState.Theme) -> Unit,
    onLogout: () -> Unit,
    navigateToEditProfile: () -> Unit,
    viewModel: MoreViewModel = koinViewModel(),

    ) {
    val state by viewModel.screenState.collectAsState()

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
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

            MoreEffect.NavigateToEditProfile -> {
                navigateToEditProfile()
            }
        }
    }

    MoreScreenContent(
        state = state,
        listener = viewModel
    )
}