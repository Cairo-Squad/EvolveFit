package com.cairosquad.evolvefit.viewmodel.login

interface LoginInteractionListener {
    fun onLoginClicked()
    fun onEmailChanged(newEmail: String)
    fun onPasswordChanged(newPassword: String)
    fun onTogglePasswordVisibility()
    fun onBackClicked()

}