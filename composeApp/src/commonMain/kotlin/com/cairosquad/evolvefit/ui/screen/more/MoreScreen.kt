package com.cairosquad.evolvefit.ui.screen.more

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.ui.navigation.NavBarRoute
import com.cairosquad.evolvefit.ui.navigation.navBar.Scaffold
import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.ui.screen.more.content.MoreScreenContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.more.MoreEffect
import com.cairosquad.evolvefit.viewmodel.more.MoreScreenState
import com.cairosquad.evolvefit.viewmodel.more.MoreViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MoreScreen(
    navigateToFavorites: () -> Unit,
    navigateToNotificationSettings: () -> Unit,
    onThemeChanged: (MoreScreenState.Theme) -> Unit,
    currentTheme: MoreScreenState.Theme,
    currentLanguage: Language,
    onLanguageChange: (String) -> Unit,
    onLogout: () -> Unit,
    navigateToEditProfile: () -> Unit,
    onSelectNavBarRoute: (navBarRoute: NavBarRoute) -> Unit,
    viewModel: MoreViewModel = koinViewModel(parameters = { parametersOf(currentTheme,currentLanguage) }),
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
                onLanguageChange(effect.languageCode)
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

    Scaffold(
        currentRoute = NavBarRoute.More,
        onSelectNavBarRoute = onSelectNavBarRoute
    ) {
        MoreScreenContent(
            state = state,
            listener = viewModel
        )
    }
}