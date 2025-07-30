package com.cairosquad.evolvefit.viewmodel.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel: ViewModel(), RegisterInteractionListener {

    private val _state = MutableStateFlow(RegisterScreenState())
    val state = _state.asStateFlow()

    override fun onClickNext() {
        println("currentStep onClickNext before: ${state.value.currentStep}")
        _state.update { it.copy(currentStep = (it.currentStep + 1) % MAX_STEPS) }
        println("currentStep onClickNext after: ${state.value.currentStep}")
    }

    override fun onClickBack() {
        println("currentStep onClickBack: ${state.value.currentStep}")
        _state.update { it.copy(currentStep = (it.currentStep - 1) % MAX_STEPS) }
    }


    companion object {
        const val MAX_STEPS = 8
    }
}