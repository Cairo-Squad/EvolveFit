package com.cairosquad.evolvefit.viewmodel.onboarding

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class OnBoardingViewModel :
    BaseViewModel<OnboardingScreenState, OnboardingScreenEffect>(OnboardingScreenState()),
    OnboardingScreenListener {

    override fun onChangeLanguage(language: OnboardingScreenState.Language) {
        updateState { it.copy(bottomSheetSelectedLanguage = language) }
    }

    override fun onConfirmClicked() {
        updateState {
            it.copy(
                selectedLanguage = it.bottomSheetSelectedLanguage,
                isBottomSheetOpen = false
            )
        }
    }

    override fun toggleBottomSheet(isOpen: Boolean) {
        updateState { it.copy(isBottomSheetOpen = isOpen) }
    }

    override fun onSignUpClicked() { sendEffect(OnboardingScreenEffect.NavigateToRegister) }

    override fun onLoginClicked() { sendEffect(OnboardingScreenEffect.NavigateToLogin) }
}