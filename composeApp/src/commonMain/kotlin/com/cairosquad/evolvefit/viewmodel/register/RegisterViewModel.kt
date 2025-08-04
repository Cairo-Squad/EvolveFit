package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.viewmodel.base.BaseViewModel
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState.Goal

class RegisterViewModel :
    BaseViewModel<RegisterScreenState, RegisterEffect>(RegisterScreenState()),
    RegisterInteractionListener {

    override fun onClickNext() {
        updateState { current ->
            val nextStep = current.currentStep + 1
            val newState = current.copy(currentStep = nextStep)
            newState.copy(nextButtonEnabled = updateNextButtonEnableState(newState))
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
    override fun onHeightChanged(height: Float) {
        updateState{ it.copy(selectedHeight = height) }
    }

    override fun onWeightChanged(weight: Float) {
        updateState { it.copy(selectedWeight = weight) }
    }


    override fun onGenderClicked(gender: RegisterScreenState.Gender) {
        updateState {
            val newState =
                it.copy(selectedGender = if (it.selectedGender == gender) null else gender)
            newState.copy(nextButtonEnabled = updateNextButtonEnableState(newState))
        }
    }

    override fun onMeasurementUnitClicked(unit: RegisterScreenState.MeasurementUnit) {
        updateState {
            val newState =
                it.copy(selectedMeasurementUnit = if (it.selectedMeasurementUnit == unit) null else unit)
            newState.copy(nextButtonEnabled = updateNextButtonEnableState(newState))
        }
    }

    override fun onGoalClicked(goal: Goal) {
        updateState {
            val newState = it.copy(selectedGoal = if (it.selectedGoal == goal) null else goal)
            newState.copy(nextButtonEnabled = updateNextButtonEnableState(newState))
        }
    }

    private fun updateNextButtonEnableState(state: RegisterScreenState): Boolean {
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