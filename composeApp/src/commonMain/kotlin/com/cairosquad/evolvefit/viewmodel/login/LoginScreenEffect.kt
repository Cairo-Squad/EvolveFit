package com.cairosquad.evolvefit.viewmodel.login

sealed interface LoginScreenEffect {
    data object NavigateToApp : LoginScreenEffect
    data class ShowError(val message: String) : LoginScreenEffect
    object NavigateToRegister: LoginScreenEffect
    object NavigateBack: LoginScreenEffect
}