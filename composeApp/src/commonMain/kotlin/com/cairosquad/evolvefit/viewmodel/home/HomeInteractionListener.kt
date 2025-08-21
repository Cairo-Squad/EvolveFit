package com.cairosquad.evolvefit.viewmodel.home

interface HomeInteractionListener {
    fun onWorkoutClicked(id: String)
    fun onSavedWorkoutClicked(id: String, isSaved: Boolean)
    fun onRetryClicked()
    fun onRefresh()
}