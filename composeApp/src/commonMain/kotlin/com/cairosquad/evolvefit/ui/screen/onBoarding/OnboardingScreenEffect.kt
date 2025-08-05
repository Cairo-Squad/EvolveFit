package com.cairosquad.evolvefit.ui.screen.onBoarding

sealed class OnboardingScreenEffect {
    object NavigateToLogin : OnboardingScreenEffect()
    object NavigateToRegister : OnboardingScreenEffect()
}