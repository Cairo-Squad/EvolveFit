package com.cairosquad.evolvefit.ui.screen.more

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.screen.more.content.MoreScreenContent
import com.cairosquad.evolvefit.ui.util.LanguageManager
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.ui.util.recreateCurrentScreen
import com.cairosquad.evolvefit.viewmodel.more.MoreEffect
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState
import com.cairosquad.evolvefit.viewmodel.more.MoreViewModel
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MoreScreen(
    navigateToFavorites: () -> Unit,
    navigateToNotificationSettings: () -> Unit,
    onThemeChanged: (MoreScreenState.Theme) -> Unit,
    onLogout: () -> Unit,
    navigateToEditProfile: () -> Unit,
    viewModel: MoreViewModel = koinViewModel(),
    ) {
    val state by viewModel.screenState.collectAsState()
    val languageManager = koinInject<LanguageManager>()


    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is MoreEffect.NavigateToFavorites -> {
                navigateToFavorites()
            }
            is MoreEffect.NavigateToNotificationSettings -> {
                navigateToNotificationSettings()
            }
            is MoreEffect.ChangeLanguage -> {
                languageManager.applyLanguage(effect.language)
                recreateCurrentScreen()

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

