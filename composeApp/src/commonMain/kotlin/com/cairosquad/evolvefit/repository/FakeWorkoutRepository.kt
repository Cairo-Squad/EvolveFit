package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.domain.model.BodyPart
import com.cairosquad.evolvefit.domain.model.WorkoutModel

class FakeWorkoutRepository : WorkoutRepository {
    private val items: List<WorkoutModel> = listOf(
        WorkoutModel(
            id = 1,
            title = "Workout 1",
            duration = "25 min",
            bodyPart = BodyPart.Chest,
            imageUrl = ""
        ),
        WorkoutModel(
            id = 2,
            title = "Workout 2",
            duration = "30 min",
            bodyPart = BodyPart.Arm,
            imageUrl = "https://picsum.photos/seed/workout2/800/600"
        ),
        WorkoutModel(
            id = 3,
            title = "Workout 3",
            duration = "20 min",
            bodyPart = BodyPart.Back,
            imageUrl = "https://picsum.photos/seed/workout3/800/600"
        ),
        WorkoutModel(
            id = 4,
            title = "Workout 4",
            duration = "35 min",
            bodyPart = BodyPart.Shoulder,
            imageUrl = "https://picsum.photos/seed/workout4/800/600"
        ),
        WorkoutModel(
            id = 5,
            title = "Workout 5",
            duration = "40 min",
            bodyPart = BodyPart.Chest,
            imageUrl = ""
        ),
        WorkoutModel(
            id = 6,
            title = "Workout 6",
            duration = "30 min",
            bodyPart = BodyPart.Chest,
            imageUrl = "https://picsum.photos/seed/workout6/800/600"
        ),
    )

    override suspend fun getAllWorkouts(): List<WorkoutModel> = items

    override suspend fun getWorkoutsByBodyPart(bodyPart: BodyPart): List<WorkoutModel> =
        items.filter { it.bodyPart == bodyPart }

}