package com.cairosquad.evolvefit.viewmodel.onboarding

import com.cairosquad.evolvefit.domain.model.Language
interface OnboardingScreenListener {
    fun onChangeLanguage(language: OnboardingScreenState.Language)
    fun toggleBottomSheet(isOpen: Boolean)
    fun onSignUpClicked()
    fun onLoginClicked()
    fun onConfirmClicked(language : Language)
}