package com.cairosquad.evolvefit.ui.screen.login.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.cairosquad.evolvefit.design_system.component.PrimaryButton
import com.cairosquad.evolvefit.design_system.component.appbar.ActionIconButton
import com.cairosquad.evolvefit.design_system.component.appbar.CustomAppBar
import com.cairosquad.evolvefit.design_system.component.InputField
import com.cairosquad.evolvefit.design_system.theme.Theme
import com.cairosquad.evolvefit.ui.screen.login.content.component.SignUpPromptRow
import com.cairosquad.evolvefit.ui.screen.register.content.RegisterHeader
import com.cairosquad.evolvefit.viewmodel.login.LoginInteractionListener
import com.cairosquad.evolvefit.viewmodel.login.LoginScreenUiState
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.arrow_back_description
import evolvefit.composeapp.generated.resources.enter_your_email
import evolvefit.composeapp.generated.resources.ic_app_logo
import evolvefit.composeapp.generated.resources.ic_back
import evolvefit.composeapp.generated.resources.ic_email
import evolvefit.composeapp.generated.resources.ic_error
import evolvefit.composeapp.generated.resources.ic_lock
import evolvefit.composeapp.generated.resources.ic_visibility_off
import evolvefit.composeapp.generated.resources.ic_visibility_on
import evolvefit.composeapp.generated.resources.login
import evolvefit.composeapp.generated.resources.logo_description
import evolvefit.composeapp.generated.resources.password_placeholder
import evolvefit.composeapp.generated.resources.welcome_back_description
import evolvefit.composeapp.generated.resources.welcome_back_title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun LoginScreenContent(
    state: LoginScreenUiState,
    listener: LoginInteractionListener,
    showBackButton: Boolean
) {
    val visibilityIcon = if (state.isPasswordVisible) {
        Res.drawable.ic_visibility_on
    } else {
        Res.drawable.ic_visibility_off
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.color.surfaces.surface)
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomAppBar(
            title = "",
            header = {
                if (showBackButton) {
                    ActionIconButton(
                        icon = painterResource(Res.drawable.ic_back),
                        contentDescription = stringResource(Res.string.arrow_back_description),
                        tint = Theme.color.surfaces.onSurface,
                        onClick = listener::onBackClicked
                    )
                }
            }
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                val gradientBrush = Brush.linearGradient(Theme.color.gradiant.iconGradiant)

                Icon(
                    painter = painterResource(Res.drawable.ic_app_logo),
                    contentDescription = stringResource(Res.string.logo_description),
                    modifier = Modifier
                        .padding(bottom = 24.dp, top = 20.dp)
                        .graphicsLayer(alpha = 0.99f)
                        .drawWithCache {
                            onDrawWithContent {
                                drawContent()
                                drawRect(
                                    brush = gradientBrush,
                                    blendMode = BlendMode.SrcAtop
                                )
                            }
                        }
                )
            }

            item {
                RegisterHeader(
                    modifier = Modifier
                        .padding(bottom = 24.dp),
                    title = stringResource(Res.string.welcome_back_title),
                    description = stringResource(Res.string.welcome_back_description),
                )
            }

            item {
                InputField(
                    modifier = Modifier
                        .padding(bottom = 12.dp),
                    value = state.email,
                    onValueChange = listener::onEmailChanged,
                    placeholder = stringResource(Res.string.enter_your_email),
                    leadingIcon = Res.drawable.ic_email,
                    isErrorMessageShown = state.emailError != null,
                    error = state.emailError?.let { stringResource(it) } ?: "",
                    isError = state.emailError != null || state.isFormError != null
                )
            }

            item {
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
                    isErrorMessageShown = state.passwordError != null,
                    error = state.passwordError?.let { stringResource(it) } ?: "",
                    isError = state.passwordError != null || state.isFormError != null
                )
            }
            item{
                state.generalError?.let { errorRes ->
                    Row(
                        modifier = Modifier.padding(top = 7.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp).padding(end=4.dp),
                            painter = painterResource(Res.drawable.ic_error),
                            contentDescription = "error",
                            tint = Theme.color.system.error
                        )
                        Text(
                            text = stringResource(errorRes),
                            color = Theme.color.system.error,
                            style = Theme.textStyle.label.smallRegular12,
                        )
                    }
                }
            }

            item {
                PrimaryButton(
                    modifier = Modifier
                        .padding(bottom = 16.dp),
                    text = stringResource(Res.string.login),
                    isEnabled = state.canSubmit,
                    isLoading = state.isLoading,
                    onClick = listener::onLoginClicked
                )
            }

        }
        SignUpPromptRow(onJoinNowClicked = listener::onJoinNowClicked)
    }
}
