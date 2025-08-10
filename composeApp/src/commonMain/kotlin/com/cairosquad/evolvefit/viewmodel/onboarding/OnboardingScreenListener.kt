package com.cairosquad.evolvefit.viewmodel.onboarding

interface OnboardingScreenListener {
    fun onChangeLanguage(language: OnboardingScreenState.Language)
    fun toggleBottomSheet(isOpen: Boolean)
    fun onSignUpClicked()
    fun onLoginClicked()
    fun onConfirmClicked()
}