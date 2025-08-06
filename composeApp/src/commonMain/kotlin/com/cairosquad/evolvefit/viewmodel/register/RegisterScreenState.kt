package com.cairosquad.evolvefit.viewmodel.register

data class RegisterScreenState(
    val currentStep: Int = 1,
    val selectedHeight : Float =0F,
    val selectedWeight :Float =0F,
    val nextButtonEnabled: Boolean = true,

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
