package com.cairosquad.evolvefit.viewmodel.home

interface HomeInteractionListener {
    fun onWorkoutClick(id: String)
    fun onSavedWorkoutClick(id: String, isSaved: Boolean)
    fun onRetryClick()
    fun onRefresh()
}