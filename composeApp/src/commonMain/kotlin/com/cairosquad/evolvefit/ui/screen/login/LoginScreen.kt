package com.cairosquad.evolvefit.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.composables.InputField
import com.cairosquad.evolvefit.design_system.theme.AppTheme
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.viewmodel.login.LoginInteractionListener
import com.cairosquad.evolvefit.viewmodel.login.LoginScreenEffect
import com.cairosquad.evolvefit.viewmodel.login.LoginScreenState
import com.cairosquad.evolvefit.viewmodel.login.LoginViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.ic_lock
import evolvefit.composeapp.generated.resources.ic_profile
import evolvefit.composeapp.generated.resources.ic_visibility_off
import evolvefit.composeapp.generated.resources.ic_visibility_on
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    navigateToApp: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is LoginScreenEffect.NavigateToApp -> navigateToApp()
                is LoginScreenEffect.ShowError -> {}
            }
        }
    }

    LoginScreenContent(
        state = state,
        listener = viewModel
    )
}


@Composable
fun LoginScreenContent(
    state: LoginScreenState,
    listener: LoginInteractionListener
) {
    var passwordVisible by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.color.surfaces.surface)
            .statusBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            InputField(
                value = state.email,
                onValueChange = listener::onEmailChanged,
                placeholder = "Enter your email",
                error = state.emailError ?: "",
                leadingIcon = Res.drawable.ic_profile,
                keyboardType = KeyboardType.Email
            )
            InputField(
                value = state.password,
                onValueChange = listener::onPasswordChanged,
                placeholder = "Enter your password",
                error = state.passwordError ?: "",
                leadingIcon = Res.drawable.ic_lock,
                trailingIcon = if (passwordVisible) Res.drawable.ic_visibility_on else Res.drawable.ic_visibility_off,
                onTrailingIconClick = { passwordVisible = !passwordVisible },
                isPasswordField = !passwordVisible
            )
        }
        PrimaryButton(
            text = if (state.isLoading) "Loading..." else "Login",
            onClick = listener::onLoginClicked,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(48.dp)
        )
    }
}

@Preview()
@Composable
fun LoginScreenContentPreview() {
    AppTheme {
        LoginScreen(
            navigateToApp = TODO(),
        )
    }
}