package com.cairosquad.evolvefit.viewmodel.onboarding

import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.usecase.profile.ManagePreferencesUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
class OnBoardingViewModel(
    private val managePreferencesUseCase: ManagePreferencesUseCase
) :
    BaseViewModel<OnboardingScreenState, OnboardingScreenEffect>(OnboardingScreenState()),
    OnboardingScreenListener {

    override fun onChangeLanguage(language: OnboardingScreenState.Language) {
        updateState { it.copy(bottomSheetSelectedLanguage = language) }
        updateState {
            it.copy(selectedLanguage = language)
        }
    }

    override fun onConfirmClicked(language: Language) {
        tryToCall(
            block = { managePreferencesUseCase.saveLanguage(languageToLanguageCode(language)) },
            onSuccess = { onSuccessChangeLanguage(language) },
            onError = { },
        )
    }
    private fun onSuccessChangeLanguage(updatedLanguage: Language) {
        updateState {
            it.copy(
                selectedLanguage = it.bottomSheetSelectedLanguage,
                isBottomSheetOpen = false
            )
        }
        sendEffect(OnboardingScreenEffect.ChangeLanguage(updatedLanguage))
    }

    override fun toggleBottomSheet(isOpen: Boolean) {
        updateState { it.copy(isBottomSheetOpen = isOpen) }
    }

    override fun onSignUpClicked() { sendEffect(OnboardingScreenEffect.NavigateToRegister) }

    override fun onLoginClicked() { sendEffect(OnboardingScreenEffect.NavigateToLogin) }
}