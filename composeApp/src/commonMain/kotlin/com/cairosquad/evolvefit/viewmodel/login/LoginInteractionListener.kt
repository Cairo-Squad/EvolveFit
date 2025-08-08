package com.cairosquad.evolvefit.viewmodel.login

interface LoginInteractionListener {
    fun onEmailChanged(email: String)
    fun onPasswordChanged(password: String)
    fun onLoginClicked()
}