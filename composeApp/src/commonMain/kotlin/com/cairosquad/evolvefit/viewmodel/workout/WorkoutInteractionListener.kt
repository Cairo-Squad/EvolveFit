package com.cairosquad.evolvefit.viewmodel.workout

import com.cairosquad.evolvefit.entity.BodyPart

interface WorkoutInteractionListener {
    fun onSelectBodyPart(bodyPart: BodyPart)
    fun onClickWorkout(id: Long)
    fun onClickAddWorkout()
    fun onClickCommunity()
}