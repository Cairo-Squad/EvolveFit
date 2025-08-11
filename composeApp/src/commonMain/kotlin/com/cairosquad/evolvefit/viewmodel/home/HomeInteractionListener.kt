package com.cairosquad.evolvefit.viewmodel.home

interface HomeInteractionListener {
    fun onWorkoutClick(id: Long)
    fun onSavedWorkoutClick(id: Long)
    fun onRetryClicked()
}