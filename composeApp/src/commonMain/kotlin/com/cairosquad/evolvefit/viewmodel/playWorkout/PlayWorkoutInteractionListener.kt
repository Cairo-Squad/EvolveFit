package com.cairosquad.evolvefit.viewmodel.playWorkout

interface PlayWorkoutInteractionListener {
    fun onCancelWorkoutClicked()
    fun onGetReadyCounterFinish()
    fun onStartClicked()
    fun onExerciseInfoClicked(id: String)
    fun onRestFinishClicked()
    fun onFinishExercise()
    fun onForwardClicked()
    fun onBackClicked()
    fun onSkipRestClicked()
    fun onNextToAnotherWorkoutClicked()
    fun onFinishClicked()
    fun onStayInWorkoutClinked()
    fun onEndClinked()
    fun onDismissExerciseInfo()

}
