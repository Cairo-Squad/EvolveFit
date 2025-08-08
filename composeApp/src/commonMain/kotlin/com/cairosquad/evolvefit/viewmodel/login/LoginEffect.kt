package com.cairosquad.evolvefit.viewmodel.login

sealed class LoginEffect {
    object NavigateToHome: LoginEffect()
}