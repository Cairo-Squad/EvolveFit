package com.cairosquad.evolvefit.ui.screen.onBoarding

interface OnboardingScreenListener {
    fun onChangeLanguage(language: OnboardingScreenState.Language)
    fun toggleBottomSheet(isOpen: Boolean)
    fun onSignUpClicked()
    fun onLoginClicked()
    fun onConfirmClicked()
}