package com.cairosquad.evolvefit.viewmodel.login

import com.cairosquad.evolvefit.domain.exception.InvalidEmailFormatException
import com.cairosquad.evolvefit.domain.exception.InvalidPasswordException
import com.cairosquad.evolvefit.domain.exception.NetworkException
import com.cairosquad.evolvefit.domain.exception.UnknownException
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

        setLoadingState()

        tryToCall(
            block = {
                authenticationUseCase.login(current.email, current.password)
            },
            onSuccess = {
                handleLoginSuccess()
            },
            onError = { error ->
                handleLoginError(error)
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

    private fun setLoadingState() {
        updateState { it.copy(isLoading = true, canSubmit = false) }
    }

    private fun handleLoginSuccess() {
        updateState {
            val updated = it.copy(isLoading = false)
            updated.copy(canSubmit = isSubmitAllowed(updated))
        }
        sendEffect(LoginScreenEffect.NavigateToApp)
    }

    private fun handleLoginError(error: Throwable) {
        updateState { it.copy(isLoading = false) }

        when (error) {
            is NetworkException -> showError("There is no internet connection.")
            is UnknownException -> setErrorState(
                emailError = "",
                passwordError = "Email or password you entered are incorrect."
            )
            is InvalidEmailFormatException -> setErrorState(
                emailError = "Invalid email format"
            )
            is InvalidPasswordException -> setErrorState(
                passwordError = "Password must be at least 8 characters"
            )
            else -> {
                showError("An unexpected error occurred.")
                setErrorState(
                    emailError = "An unexpected error occurred.",
                    passwordError = "An unexpected error occurred."
                )
            }
        }
    }

    private fun showError(message: String) {
        sendEffect(LoginScreenEffect.ShowError(message))
    }

    private fun setErrorState(
        emailError: String = "",
        passwordError: String = ""
    ) {
        updateState {
            val updated = it.copy(
                emailError = emailError,
                passwordError = passwordError
            )
            updated.copy(canSubmit = isSubmitAllowed(updated))
        }
    }

}