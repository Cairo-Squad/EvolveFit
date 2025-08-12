package com.cairosquad.evolvefit.viewmodel.login

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthenticationUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class LoginViewModel(
    private val authenticationUseCase: AuthenticationUseCase
) : BaseViewModel<LoginScreenUiState, LoginScreenEffect>(
    LoginScreenUiState()
), LoginInteractionListener {

    override fun onLoginClicked() {
        val current = screenState.value
        if (!current.canSubmit) return

        updateState { it.copy(isLoading = true, canSubmit = false) }
        tryToCall(
            block = {
                authenticationUseCase.login(current.email, current.password)
            },
            onSuccess = {
                updateState {
                    val updated = it.copy(isLoading = false)
                    updated.copy(canSubmit = isSubmitAllowed(updated))
                }
                sendEffect(LoginScreenEffect.NavigateToApp)
            },
            onError = { error ->
                updateState {
                    val updated = it.copy(
                        isLoading = false,
                        emailError = "Invalid credentials",
                        passwordError = "Invalid credentials"
                    )
                    updated.copy(canSubmit = isSubmitAllowed(updated))
                }
            }
        )
    }

    override fun onEmailChanged(newEmail: String) {
        updateState {
            val updated = it.copy(email = newEmail, emailError = null)
            updated.copy(canSubmit = isSubmitAllowed(updated))
        }
    }

    override fun onPasswordChanged(newPassword: String) {
        updateState {
            val updated = it.copy(password = newPassword, passwordError = null)
            updated.copy(canSubmit = isSubmitAllowed(updated))
        }
    }

    override fun onTogglePasswordVisibility() {
        updateState { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    override fun onBackClicked() {
        sendEffect(LoginScreenEffect.NavigateBack)
    }

    override fun onJoinNowClicked() {
        sendEffect(LoginScreenEffect.NavigateToRegister)
    }

    private fun isSubmitAllowed(uiState: LoginScreenUiState): Boolean {
        return uiState.email.isNotBlank() &&
                uiState.password.isNotBlank() &&
                uiState.emailError == null &&
                uiState.passwordError == null &&
                !uiState.isLoading
    }
}