package com.cairosquad.evolvefit.ui.screen.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnBoardingViewModel: ViewModel() , OnboardingScreenListener {
    private val _state = MutableStateFlow(OnboardingScreenState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<OnboardingScreenEffect>()
    val effect = _effect.asSharedFlow()

    override fun onChangeLanguage(language: OnboardingScreenState.Language) {
        _state.update {
            it.copy(
                bottomSheetSelectedLanguage = language
            )
        }
    }
    override fun onConfirmClicked() {
        _state.update {
            it.copy(
                selectedLanguage = it.bottomSheetSelectedLanguage,
                isBottomSheetOpen = false
            )
        }
    }

    override fun toggleBottomSheet(isOpen: Boolean) {
        _state.update {
            it.copy(
                isBottomSheetOpen = isOpen
            )
        }
    }

    override fun onSignUpClicked() {
        viewModelScope.launch {
            _effect.emit(OnboardingScreenEffect.NavigateToRegister)
        }
    }

    override fun onLoginClicked() {
        viewModelScope.launch {
            _effect.emit(OnboardingScreenEffect.NavigateToLogin)
        }
    }

}