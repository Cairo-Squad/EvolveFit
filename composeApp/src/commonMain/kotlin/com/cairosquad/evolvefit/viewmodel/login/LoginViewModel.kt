package com.cairosquad.evolvefit.viewmodel.login

import com.cairosquad.evolvefit.domain.usecase.authentication.AuthUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class LoginViewModel(
    private val loginUseCase: AuthUseCase
) : BaseViewModel<LoginScreenState, LoginScreenEffect>(LoginScreenState()),
    LoginInteractionListener {

    override fun onEmailChanged(newEmail: String) {
        updateState { it.copy(email = newEmail, emailError = null) }
    }

    override fun onPasswordChanged(newPassword: String) {
        updateState { it.copy(password = newPassword, passwordError = null) }
    }

    override fun onLoginClicked() {
        login()
    }

    fun login() {
        val email = screenState.value.email.trim()
        val password = screenState.value.password.trim()

        println("Trying to login with email: $email") // عشان تشوفي القيم اللي داخلة

        val emailError = if (email.isEmpty()) "Email is required" else null
        val passwordError = if (password.isEmpty()) "Password is required" else null

        if (emailError != null || passwordError != null) {
            updateState { it.copy(emailError = emailError, passwordError = passwordError) }
            println("Validation failed: $emailError $passwordError")
            return
        }

        tryToCall(
            block = {
                println("Calling loginUseCase...")
                loginUseCase.login(email, password)
            },
            onSuccess = {
                println("Login success!")
                sendEffect(LoginScreenEffect.NavigateToApp)
            },
            onError = { throwable ->
                println("Login failed: ${throwable.message}")
                sendEffect(LoginScreenEffect.ShowError(throwable.message ?: "Unexpected error"))
            },
            onStart = {
                updateState { it.copy(isLoading = true) }
            },
            onEnd = {
                updateState { it.copy(isLoading = false) }
            }
        )
    }
}