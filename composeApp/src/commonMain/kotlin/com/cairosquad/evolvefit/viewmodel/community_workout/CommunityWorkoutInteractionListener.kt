package com.cairosquad.evolvefit.viewmodel.community_workout

import com.cairosquad.evolvefit.entity.BodyPart

interface CommunityWorkoutInteractionListener {
    fun onSelectBodyPart(bodyPart: BodyPart)
    fun onClickWorkout(id: Long)
}