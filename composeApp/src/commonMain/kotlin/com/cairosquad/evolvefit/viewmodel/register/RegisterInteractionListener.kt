package com.cairosquad.evolvefit.viewmodel.register

import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState.Gender
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState.MeasurementUnit
import com.cairosquad.evolvefit.viewmodel.register.RegisterScreenState.Goal

interface RegisterInteractionListener {
    fun onClickNext()
    fun onClickBack()
    fun onClickStartNow()
    fun onGenderClicked(gender: Gender)
    fun onMeasurementUnitClicked(unit: MeasurementUnit)
    fun onGoalClicked(goal: Goal)
    fun onHeightChanged(height: Float)
    fun onWeightChanged(weight: Float)
    fun onNotificationToggled(type: NotificationType)
    fun onWorkoutDaySelected(day: RegisterScreenState.WorkoutDay)
    fun onNoEquipmentSelected()
    fun onEquipmentToggled(equipmentId: String)
}