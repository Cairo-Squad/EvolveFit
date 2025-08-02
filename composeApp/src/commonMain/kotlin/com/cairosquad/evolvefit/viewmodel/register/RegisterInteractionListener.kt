package com.cairosquad.evolvefit.viewmodel.register

interface RegisterInteractionListener {
    fun onClickNext()
    fun onClickBack()
    fun onSelectStep(step: Int)
    fun onClickStartNow()
    fun onHeightChanged(height : Float )
    fun onWeightChanged(weight : Float)
}