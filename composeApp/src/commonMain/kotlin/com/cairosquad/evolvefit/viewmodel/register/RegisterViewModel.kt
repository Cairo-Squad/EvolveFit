package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

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

    override fun onMetricCheckedChange(checked: Boolean) {
        updateState {
            val newState = it.copy(
                isMetricChecked = checked,
                isImperialChecked = if (checked) false else it.isImperialChecked
            )
            newState.copy(
                nextButtonEnabled = newState.isMetricChecked || newState.isImperialChecked
            )
        }
    }

    override fun onImperialCheckedChange(checked: Boolean) {
        updateState {
            val newState = it.copy(
                isImperialChecked = checked,
                isMetricChecked = if (checked) false else it.isMetricChecked
            )
            newState.copy(
                nextButtonEnabled = newState.isMetricChecked || newState.isImperialChecked
            )
        }
    }

    override fun onLossWeightCheckedChange(checked: Boolean) {
        updateState {
            val newState = it.copy(
                isLoseWeightChecked = checked,
                isGainWeightChecked = if (checked) false else it.isGainWeightChecked,
                isStayInShapeChecked = if (checked) false else it.isStayInShapeChecked
            )
            newState.copy(
                nextButtonEnabled = newState.isLoseWeightChecked || newState.isGainWeightChecked || newState.isStayInShapeChecked
            )
        }
    }

    override fun onGainWeightCheckedChange(checked: Boolean) {
        updateState {
            val newState = it.copy(
                isGainWeightChecked = checked,
                isLoseWeightChecked = if (checked) false else it.isLoseWeightChecked,
                isStayInShapeChecked = if (checked) false else it.isStayInShapeChecked

            )
            newState.copy(
                nextButtonEnabled = newState.isLoseWeightChecked || newState.isGainWeightChecked || newState.isStayInShapeChecked
            )
        }
    }

    override fun onStayInShapeCheckedChange(checked: Boolean) {
        updateState {
            val newState = it.copy(
                isStayInShapeChecked = checked,
                isLoseWeightChecked = if (checked) false else it.isLoseWeightChecked,
                isGainWeightChecked = if (checked) false else it.isGainWeightChecked,
            )
            newState.copy(
                nextButtonEnabled = newState.isLoseWeightChecked || newState.isGainWeightChecked || newState.isStayInShapeChecked
            )
        }
    }

    companion object {
        const val MAX_STEPS = 8
    }
}