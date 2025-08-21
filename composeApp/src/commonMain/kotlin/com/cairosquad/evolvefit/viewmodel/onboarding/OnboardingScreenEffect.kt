package com.cairosquad.evolvefit.viewmodel.onboarding

import com.cairosquad.evolvefit.domain.model.Language

sealed class OnboardingScreenEffect {
    object NavigateToLogin : OnboardingScreenEffect()
    object NavigateToRegister : OnboardingScreenEffect()
    data class ChangeLanguage(val language: Language) :OnboardingScreenEffect()
}