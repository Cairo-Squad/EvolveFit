package com.cairosquad.evolvefit.viewmodel.register

interface RegisterInteractionListener {
    fun onClickNext()
    fun onClickBack()
    fun onSelectStep(step: Int)
    fun onClickStartNow()
}