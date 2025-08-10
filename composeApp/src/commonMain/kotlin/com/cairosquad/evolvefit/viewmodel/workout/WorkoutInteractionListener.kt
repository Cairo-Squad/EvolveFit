package com.cairosquad.evolvefit.viewmodel.workout

interface WorkoutInteractionListener {
    fun onSelectBodyPart(bodyPart: String)
    fun onClickWorkout(id: Long)
    fun onClickAddWorkout()
    fun onClickCommunity()
}