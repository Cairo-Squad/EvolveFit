package com.cairosquad.evolvefit.viewmodel.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel: ViewModel(), RegisterInteractionListener {

    private val _state = MutableStateFlow(RegisterScreenState())
    val state = _state.asStateFlow()

    override fun onClickNext() {
        _state.update { it.copy(currentStep = (it.currentStep + 1) % MAX_STEPS) }
    }

    override fun onClickBack() {
        _state.update { it.copy(currentStep = (it.currentStep - 1) % MAX_STEPS) }
    }
    override fun onHeightChanged(height: Float) {
        _state.update { it.copy(selectedHeight = height) }
    }

    override fun onWeightChanged(weight: Float) {
        _state.update { it.copy(selectedWeight = weight) }
    }


    companion object {
        const val MAX_STEPS = 8
    }
}