package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel

class RegisterViewModel :
    BaseViewModel<RegisterScreenState, RegisterEffect>(RegisterScreenState()),
    RegisterInteractionListener {

    override fun onClickNext() {
        updateState { current ->
            val nextStep = current.currentStep + 1
            val newState = current.copy(currentStep = nextStep)
            newState.copy(nextButtonEnabled = isNextButtonEnabled(newState))
        }
    }

    override fun onClickBack() {
        if (screenState.value.currentStep == 1) {
            sendEffect(RegisterEffect.NavigateBack)
        } else {
            updateState { it.copy(currentStep = it.currentStep - 1) }
        }
    }

   override fun onClickStartNow() {
        // TODO: call the register use case and Navigate to home screen if register is successful
        sendEffect(RegisterEffect.NavigateToHome)
    }

    override fun onFemaleCheckedChange(checked: Boolean) {
        updateState {
            val gender = if (checked) RegisterScreenState.Gender.Female else null
            val newState = it.copy(selectedGender = gender)
            newState.copy(nextButtonEnabled = isNextButtonEnabled(newState))
        }
    }

    override fun onMaleCheckedChange(checked: Boolean) {
        updateState {
            val gender = if (checked) RegisterScreenState.Gender.Male else null
            val newState = it.copy(selectedGender = gender)
            newState.copy(nextButtonEnabled = isNextButtonEnabled(newState))
        }
    }

    override fun onMetricCheckedChange(checked: Boolean) {
        updateState {
            val unit = if (checked) RegisterScreenState.MeasurementUnit.Metric else null
            val newState = it.copy(selectedMeasurementUnit = unit)
            newState.copy(nextButtonEnabled = isNextButtonEnabled(newState))
        }
    }

    override fun onImperialCheckedChange(checked: Boolean) {
        updateState {
            val unit = if (checked) RegisterScreenState.MeasurementUnit.Imperial else null
            val newState = it.copy(selectedMeasurementUnit = unit)
            newState.copy(nextButtonEnabled = isNextButtonEnabled(newState))
        }
    }

    override fun onLossWeightCheckedChange(checked: Boolean) {
        updateState {
            val goal = if (checked) RegisterScreenState.Goal.LoseWeight else null
            val newState = it.copy(selectedGoal = goal)
            newState.copy(nextButtonEnabled = isNextButtonEnabled(newState))
        }
    }

    override fun onGainWeightCheckedChange(checked: Boolean) {
        updateState {
            val goal = if (checked) RegisterScreenState.Goal.GainWeight else null
            val newState = it.copy(selectedGoal = goal)
            newState.copy(nextButtonEnabled = isNextButtonEnabled(newState))
        }
    }

    override fun onStayInShapeCheckedChange(checked: Boolean) {
        updateState {
            val goal = if (checked) RegisterScreenState.Goal.StayInShape else null
            val newState = it.copy(selectedGoal = goal)
            newState.copy(nextButtonEnabled = isNextButtonEnabled(newState))
        }
    }

    private fun isNextButtonEnabled(state: RegisterScreenState): Boolean {
        return when (state.currentStep) {
            1 -> state.selectedGender != null
            2 -> state.selectedMeasurementUnit != null
            3 -> true
            4 -> state.selectedGoal != null
            5, 6, 7 -> true
            else -> false
        }
    }

    companion object {
        const val MAX_STEPS = 8
    }
}