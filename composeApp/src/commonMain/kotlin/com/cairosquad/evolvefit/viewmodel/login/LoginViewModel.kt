package com.cairosquad.evolvefit.viewmodel.login

import com.cairosquad.evolvefit.domain.exception.InternetConnectionException
import com.cairosquad.evolvefit.domain.exception.InvalidEmailFormatException
import com.cairosquad.evolvefit.domain.exception.InvalidPasswordException
import com.cairosquad.evolvefit.domain.exception.NetworkException
import com.cairosquad.evolvefit.domain.exception.UnknownException
import com.cairosquad.evolvefit.domain.usecase.authentication.AuthenticationUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import evolvefit.composeapp.generated.resources.Res
import evolvefit.composeapp.generated.resources.error_invalid_email_format
import evolvefit.composeapp.generated.resources.error_invalid_password
import evolvefit.composeapp.generated.resources.error_no_internet
import evolvefit.composeapp.generated.resources.error_unexpected
import evolvefit.composeapp.generated.resources.error_unknown_credentials
import org.jetbrains.compose.resources.StringResource

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
            onSuccess = { handleLoginSuccess() },
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
        return (uiState.email.isNotBlank() || uiState.password.isNotBlank()) &&
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
            is NetworkException -> {
                setErrorState(
                    passwordError = Res.string.error_unknown_credentials,
                    isFormError = true                )
            }

            is InternetConnectionException -> {
                setErrorState(
                    passwordError = Res.string.error_no_internet,
                    isFormError = true                )
            }

            is UnknownException -> setErrorState(
                isFormError = true,
                passwordError = Res.string.error_unknown_credentials
            )

            is InvalidEmailFormatException -> setErrorState(
                emailError = Res.string.error_invalid_email_format
            )

            is InvalidPasswordException -> setErrorState(
                passwordError = Res.string.error_invalid_password
            )

            else -> {
                val unexpectedError = Res.string.error_unexpected
                showError(unexpectedError)
                setErrorState(
                    isFormError = true,
                    passwordError = unexpectedError
                )
            }
        }
    }


    private fun showError(message: StringResource) {
        sendEffect(LoginScreenEffect.ShowError(message))
    }

    private fun setErrorState(
        emailError: StringResource? = null,
        passwordError: StringResource? = null,
        isFormError: Boolean = false
    ) {
        updateState {
            val updated = it.copy(
                emailError = emailError,
                passwordError = passwordError,
                isFormError = isFormError
            )
            updated.copy(canSubmit = isSubmitAllowed(updated))
        }
    }

}