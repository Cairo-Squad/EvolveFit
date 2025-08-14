package com.cairosquad.evolvefit.viewmodel.login

import org.jetbrains.compose.resources.StringResource

sealed interface LoginScreenEffect {
    data object NavigateToApp : LoginScreenEffect
    data class ShowError(val message: StringResource) : LoginScreenEffect
    object NavigateToRegister: LoginScreenEffect
    object NavigateBack: LoginScreenEffect
}