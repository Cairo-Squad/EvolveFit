package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.WorkoutRepository
import com.cairosquad.evolvefit.entity.BodyPart
import com.cairosquad.evolvefit.entity.Workout

class FakeWorkoutRepository : WorkoutRepository {
    override suspend fun getAllWorkouts(): List<Workout> = items

    override suspend fun getWorkoutsByBodyPart(bodyPart: BodyPart): List<Workout> =
        items.filter { it.bodyPart == bodyPart }

    private companion object {
        val items: List<Workout> = listOf(
            Workout(
                id = 1,
                title = "Workout 1",
                duration = "25 min",
                bodyPart = BodyPart.Chest,
                imageUrl = ""
            ),
            Workout(
                id = 2,
                title = "Workout 2",
                duration = "30 min",
                bodyPart = BodyPart.Arm,
                imageUrl = "https://picsum.photos/seed/workout2/800/600"
            ),
            Workout(
                id = 3,
                title = "Workout 3",
                duration = "20 min",
                bodyPart = BodyPart.Back,
                imageUrl = "https://picsum.photos/seed/workout3/800/600"
            ),
            Workout(
                id = 4,
                title = "Workout 4",
                duration = "35 min",
                bodyPart = BodyPart.Shoulder,
                imageUrl = "https://picsum.photos/seed/workout4/800/600"
            ),
            Workout(
                id = 5,
                title = "Workout 5",
                duration = "40 min",
                bodyPart = BodyPart.Chest,
                imageUrl = ""
            ),
            Workout(
                id = 6,
                title = "Workout 6",
                duration = "30 min",
                bodyPart = BodyPart.Chest,
                imageUrl = "https://picsum.photos/seed/workout6/800/600"
            ),
        )
    }
}
