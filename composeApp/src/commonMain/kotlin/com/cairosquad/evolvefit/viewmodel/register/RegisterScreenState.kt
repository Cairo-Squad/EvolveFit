package com.cairosquad.evolvefit.viewmodel.register

data class RegisterScreenState(
    val currentStep: Int = 1,

    val nextButtonEnabled: Boolean = false,

    val selectedGender: Gender? = null,

    val selectedMeasurementUnit: MeasurementUnit? = null,

    val selectedGoal: Goal? = null
    ){
    enum class Gender {
        Female, Male
    }

    enum class MeasurementUnit {
        Metric, Imperial
    }

    enum class Goal {
        LoseWeight, GainWeight, StayInShape
    }
}
