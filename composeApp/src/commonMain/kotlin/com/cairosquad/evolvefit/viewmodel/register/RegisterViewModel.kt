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
        updateState { it.copy(selectedHeight = height) }
    }

    override fun onWeightChanged(weight: Float) {
        updateState { it.copy(selectedWeight = weight) }
    }

    override fun onWorkoutReminderToggled(enabled: Boolean) {
        updateState { it.copy(isWorkoutReminderEnabled = enabled) }
    }

    override fun onWaterReminderToggled(enabled: Boolean) {
        updateState { it.copy(isWaterReminderEnabled = enabled) }
    }

    override fun onBodyWeightReminderToggled(enabled: Boolean) {
        updateState { it.copy(isBodyWeightReminderEnabled = enabled) }
    }

    override fun onChallengesReminderToggled(enabled: Boolean) {
        updateState { it.copy(isChallengesReminderEnabled = enabled) }
    }

    override fun onWorkoutDaySelected(day: RegisterScreenState.WorkoutDay) {
        updateState {
            val currentDays = it.selectedWorkoutDays
            val updatedDays = if (day in currentDays) {
                currentDays - day
            } else {
                currentDays + day
            }
            val newState = it.copy(selectedWorkoutDays = updatedDays)
            newState.copy(nextButtonEnabled = updateNextButtonEnableState(newState))
        }
    }

    override fun onNoEquipmentSelected() {
        updateState {
            val isSelected = !it.isNoEquipmentSelected
            it.copy(
                isNoEquipmentSelected = isSelected,
                selectedEquipments = if (isSelected) emptyList() else it.selectedEquipments,
                nextButtonEnabled = updateNextButtonEnableState(
                    it.copy(
                        isNoEquipmentSelected = isSelected,
                        selectedEquipments = if (isSelected) emptyList() else it.selectedEquipments
                    )
                )
            )
        }
    }

    override fun onEquipmentToggled(equipmentId: String) {
        updateState {
            val updatedSelection = it.selectedEquipments.toMutableList()
            if (equipmentId in updatedSelection) {
                updatedSelection.remove(equipmentId)
            } else {
                updatedSelection.add(equipmentId)
            }

            val newState = it.copy(
                isNoEquipmentSelected = false,
                selectedEquipments = updatedSelection
            )
            newState.copy(nextButtonEnabled = updateNextButtonEnableState(newState))
        }
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
            5->state.isNoEquipmentSelected || state.selectedEquipments.isNotEmpty()
            6->true
            7 -> state.selectedWorkoutDays.isNotEmpty()
            else -> false
        }
    }

    companion object {
        const val MAX_STEPS = 8
    }
}