package com.cairosquad.evolvefit.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.login.content.SignUpPromptRow
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import com.cairosquad.evolvefit.ui.util.ObserveAsEffect
import com.cairosquad.evolvefit.viewmodel.login.LoginInteractionListener
import com.cairosquad.evolvefit.viewmodel.login.LoginScreenEffect
import com.cairosquad.evolvefit.viewmodel.login.LoginScreenUiState
import com.cairosquad.evolvefit.viewmodel.login.LoginViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arrow_back_description
import evolvefit.composeapp.generated.resources.email_placeholder
import evolvefit.composeapp.generated.resources.ic_app_logo
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_lock
import evolvefit.composeapp.generated.resources.ic_profile
import evolvefit.composeapp.generated.resources.ic_visibility_off
import evolvefit.composeapp.generated.resources.ic_visibility_on
import evolvefit.composeapp.generated.resources.login
import evolvefit.composeapp.generated.resources.logo_description
import evolvefit.composeapp.generated.resources.password_placeholder
import evolvefit.composeapp.generated.resources.welcome_back_description
import evolvefit.composeapp.generated.resources.welcome_back_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    navigateBack: () -> Unit,
    navigateToRegister: () -> Unit,
    navigateToApp: () -> Unit,
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
    LoginScreenContent(state = state, listener = loginViewModel)
}

@Composable
private fun LoginScreenContent(
    state: LoginScreenUiState,
    listener: LoginInteractionListener
) {
    val visibilityIcon = if (state.isPasswordVisible) {
        Res.drawable.ic_visibility_on
    } else {
        Res.drawable.ic_visibility_off
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp)
                .align(Alignment.Start)
                .clickable(onClick = listener::onBackClicked),
            painter = painterResource(Res.drawable.ic_back),
            contentDescription = stringResource(Res.string.arrow_back_description),
        )

        Icon(
            modifier = Modifier
                .padding(bottom = 24.dp, top = 20.dp),
            painter = painterResource(Res.drawable.ic_app_logo),
            contentDescription = stringResource(Res.string.logo_description),
            tint = Theme.color.brand.primary,
        )

        RegisterHeader(
            modifier = Modifier
                .padding(bottom = 24.dp),
            title = stringResource(Res.string.welcome_back_title),
            description = stringResource(Res.string.welcome_back_description),
        )

        InputField(
            modifier = Modifier
                .padding(bottom = 12.dp),
            value = state.email,
            onValueChange = listener::onEmailChanged,
            placeholder = stringResource(Res.string.email_placeholder),
            leadingIcon = Res.drawable.ic_profile,
        )

        InputField(
            modifier = Modifier
                .padding(bottom = 68.dp),
            value = state.password,
            onValueChange = listener::onPasswordChanged,
            placeholder = stringResource(Res.string.password_placeholder),
            isPasswordField = !state.isPasswordVisible,
            leadingIcon = Res.drawable.ic_lock,
            trailingIcon = visibilityIcon,
            onTrailingIconClick = listener::onTogglePasswordVisibility,
        )

        PrimaryButton(
            text = stringResource(Res.string.login),
            isEnabled = state.canSubmit,
            onClick = listener::onLoginClicked
        )

        Spacer(modifier = Modifier.weight(1f))

        SignUpPromptRow(onJoinNowClicked = listener::onJoinNowClicked)
    }
}