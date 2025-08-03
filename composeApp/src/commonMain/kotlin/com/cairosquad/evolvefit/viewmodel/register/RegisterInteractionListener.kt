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

}