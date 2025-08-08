package com.cairosquad.evolvefit.viewmodel.login

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class LoginViewModel(
    private val authUseCase: AuthUseCase
) : BaseViewModel<LoginScreenUiState, LoginEffect>(
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
                sendEffect(LoginEffect.NavigateToHome)
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
        TODO("Not yet implemented")
    }

    private fun isSubmitAllowed(uiState: LoginScreenUiState): Boolean {
        return uiState.email.isNotBlank() &&
                uiState.password.isNotBlank() &&
                uiState.emailError == null &&
                uiState.passwordError == null &&
                !uiState.isLoading
    }

}