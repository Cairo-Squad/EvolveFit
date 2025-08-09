package com.cairosquad.evolvefit.viewmodel.login

data class LoginScreenUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isPasswordVisible: Boolean = false,
    val canSubmit: Boolean = false
)