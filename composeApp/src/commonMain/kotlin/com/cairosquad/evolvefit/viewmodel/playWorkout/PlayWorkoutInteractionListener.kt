package com.cairosquad.evolvefit.viewmodel.playWorkout

interface PlayWorkoutInteractionListener {
    fun onClickCancelWorkout()
    fun onGetReadyCounterFinish()
    fun onClickStart()
    fun onClickExerciseInfo(id: String)
    fun onClickRestFinish()
    fun onFinishExercise()
    fun onClickForward()
    fun onClickBack()
    fun onClickSkipRest()
    fun onClickNextToAnotherWorkout()
    fun onClickFinish()
    fun onClinkStayInWorkout()
    fun onClinkEnd()
    fun onDismissExerciseInfo()
}
