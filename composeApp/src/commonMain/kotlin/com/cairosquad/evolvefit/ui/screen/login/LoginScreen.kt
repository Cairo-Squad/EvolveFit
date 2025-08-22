package com.cairosquad.evolvefit.ui.screen.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.ui.screen.login.content.LoginScreenContent
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.login.LoginInteractionListener
import com.cairosquad.evolvefit.viewmodel.login.LoginScreenEffect
import com.cairosquad.evolvefit.viewmodel.login.LoginScreenUiState
import com.cairosquad.evolvefit.viewmodel.login.LoginViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    navigateBack: () -> Unit,
    navigateToRegister: () -> Unit,
    navigateToApp: () -> Unit,
    showBackButton: Boolean,
    loginViewModel: LoginViewModel = koinViewModel()
) {
    val state by loginViewModel.screenState.collectAsState()
    ObserveAsEffect(loginViewModel.effect) { effect ->
        when (effect) {
            LoginScreenEffect.NavigateToApp -> {
                navigateToApp()
            }

            LoginScreenEffect.NavigateToRegister -> {
                navigateToRegister()
            }

            LoginScreenEffect.NavigateBack -> {
                navigateBack()
            }

            is LoginScreenEffect.ShowError -> {
                // TODO()
            }
        }
    }
    LoginScreenContent(
        state = state, 
        listener = loginViewModel,
        showBackButton = showBackButton
    )
}


@Preview()
@Composable
fun LoginScreenPreview() {
    AppTheme(isDarkTheme = true) {
        LoginScreenContent(
            state = LoginScreenUiState(
                email = "test@example.com",
                password = "password123",
                isPasswordVisible = false,
                canSubmit = true
            ),
            listener = object : LoginInteractionListener {
                override fun onLoginClicked() {}
                override fun onEmailChanged(newEmail: String) {}
                override fun onPasswordChanged(newPassword: String) {}
                override fun onTogglePasswordVisibility() {}
                override fun onBackClicked() {}
                override fun onJoinNowClicked() {}
            },
            showBackButton = true
        )
    }
}