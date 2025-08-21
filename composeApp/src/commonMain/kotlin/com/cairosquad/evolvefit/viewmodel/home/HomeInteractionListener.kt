package com.cairosquad.evolvefit.viewmodel.home

interface HomeInteractionListener {
    fun onWorkoutClicked(id: String)
    fun onSavedWorkoutClicked(id: String)
    fun onRetryClicked()
    fun onRefresh()
}