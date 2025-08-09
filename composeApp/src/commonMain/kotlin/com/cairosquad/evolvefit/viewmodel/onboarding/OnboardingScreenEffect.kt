package com.cairosquad.evolvefit.viewmodel.onboarding

sealed class OnboardingScreenEffect {
    object NavigateToLogin : OnboardingScreenEffect()
    object NavigateToRegister : OnboardingScreenEffect()
}