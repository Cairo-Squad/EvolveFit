package com.cairosquad.evolvefit.viewmodel.register

data class RegisterScreenState(
    val currentStep: Int = 1,
    val nextButtonEnabled: Boolean = false,
    val isFemaleChecked: Boolean = false,
    val isMaleChecked: Boolean = false,

    )
