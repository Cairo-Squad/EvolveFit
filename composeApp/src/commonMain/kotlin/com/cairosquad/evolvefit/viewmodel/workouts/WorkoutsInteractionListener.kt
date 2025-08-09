package com.cairosquad.evolvefit.viewmodel.workouts

import com.cairosquad.evolvefit.domain.model.BodyPart

interface WorkoutsInteractionListener {
    fun onSelectBodyPart(bodyPart: BodyPart)
    fun onClickWorkout(id: Long)
    fun onClickAddWorkout()
    fun onClickCommunity()
}