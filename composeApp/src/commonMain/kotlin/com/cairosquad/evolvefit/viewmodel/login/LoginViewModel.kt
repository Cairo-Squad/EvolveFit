package com.cairosquad.evolvefit.viewmodel.login

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class LoginViewModel(
    private val authUseCase: AuthUseCase
) : BaseViewModel<LoginScreenUiState, LoginScreenEffect>(
    LoginScreenUiState()
), LoginInteractionListener {

    override fun onLoginClicked() {
        val current = screenState.value
        if (!current.canSubmit) return

        updateState { it.copy(isLoading = true, canSubmit = false) }
        tryToCall(
            block = {
                authUseCase.login(current.email, current.password)
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
            val updated = it.copy(
                email = newEmail,
                emailError = null,
                passwordError = it.passwordError
            )
            updated.copy(canSubmit = isSubmitAllowed(updated))
        }
    }

    override fun onPasswordChanged(newPassword: String) {
        updateState {
            val updated = it.copy(
                password = newPassword,
                passwordError = null,
                emailError = it.emailError
            )
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
        val hasEmail = uiState.email.isNotBlank()
        val hasPassword = uiState.password.isNotBlank()
        val isLoading = uiState.isLoading

        val noErrors = uiState.emailError == null && uiState.passwordError == null

        if (noErrors && hasEmail && hasPassword && !isLoading) {
            return true
        }
        return hasEmail && hasPassword && !isLoading
    }
}