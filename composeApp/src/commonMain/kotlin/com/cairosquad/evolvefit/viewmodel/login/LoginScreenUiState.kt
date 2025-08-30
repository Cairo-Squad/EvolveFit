package com.cairosquad.evolvefit.viewmodel.login

import org.jetbrains.compose.resources.StringResource

data class LoginScreenUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val emailError: StringResource? = null,
    val passwordError: StringResource? = null,
    val generalError: StringResource? = null,
    val isPasswordVisible: Boolean = false,
    val isFormError: Boolean? = null,
    val canSubmit: Boolean = false
)