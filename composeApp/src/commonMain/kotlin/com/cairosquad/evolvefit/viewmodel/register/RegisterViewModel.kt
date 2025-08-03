package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel :
    BaseViewModel<RegisterScreenState, RegisterEffect>(RegisterScreenState()),
    RegisterInteractionListener {

    override fun onClickNext() {
        updateState { it.copy(currentStep = it.currentStep + 1) }
    }

    override fun onClickBack() {
        if (screenState.value.currentStep == 1) {
            sendEffect(RegisterEffect.NavigateBack)
        } else {
            updateState { it.copy(currentStep = it.currentStep - 1) }
        }
    }

    override fun onSelectStep(step: Int) {
        updateState { it.copy(currentStep = step) }
    }

    override fun onClickStartNow() {
        // TODO: call the register use case and Navigate to home screen if register is successful
        sendEffect(RegisterEffect.NavigateToHome)
    }

    override fun onFemaleCheckedChange(checked: Boolean) {
        updateState {
            val newState = it.copy(
                isFemaleChecked = checked,
                isMaleChecked = if (checked) false else it.isMaleChecked
            )
            newState.copy(
                nextButtonEnabled = newState.isFemaleChecked || newState.isMaleChecked
            )
        }
    }

    override fun onMaleCheckedChange(checked: Boolean) {
        updateState {
            val newState = it.copy(
                isMaleChecked = checked,
                isFemaleChecked = if (checked) false else it.isFemaleChecked
            )
            newState.copy(
                nextButtonEnabled = newState.isFemaleChecked || newState.isMaleChecked
            )
        }
    }

    companion object {
        const val MAX_STEPS = 8
    }
}