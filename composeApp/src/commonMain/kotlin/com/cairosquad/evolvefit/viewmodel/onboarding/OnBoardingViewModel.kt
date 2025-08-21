package com.cairosquad.evolvefit.viewmodel.onboarding

import com.cairosquad.evolvefit.domain.model.Language
import com.cairosquad.evolvefit.domain.usecase.profile.ManageLanguageUseCase
import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.more.MoreEffect

class OnBoardingViewModel(
    private val manageLanguageUseCase: ManageLanguageUseCase
) :
    BaseViewModel<OnboardingScreenState, OnboardingScreenEffect>(OnboardingScreenState()),
    OnboardingScreenListener {

    override fun onChangeLanguage(language: OnboardingScreenState.Language) {
        updateState {
            it.copy(bottomSheetSelectedLanguage = language)
        }
        updateState {
            it.copy(selectedLanguage = language)
        }
    }

    override fun onConfirmClicked(language: Language) {
        tryToCall(
            block = { manageLanguageUseCase.saveLanguage(language) },
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
        updateState {
            it.copy(isBottomSheetOpen = isOpen)
        }
    }

    override fun onSignUpClicked() {
        sendEffect(OnboardingScreenEffect.NavigateToRegister)
    }

    override fun onLoginClicked() {
        sendEffect(OnboardingScreenEffect.NavigateToLogin)
    }
}