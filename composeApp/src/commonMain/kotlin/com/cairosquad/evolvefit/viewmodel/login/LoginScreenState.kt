package com.cairosquad.evolvefit.viewmodel.login

data class LoginScreenState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null
)