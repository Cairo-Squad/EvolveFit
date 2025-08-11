package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.WorkoutRepo
import com.cairosquad.evolvefit.entity.BodyPart
import com.cairosquad.evolvefit.entity.Workout

class FakeWorkoutRepository : WorkoutRepo {
    override suspend fun getAllWorkouts(): List<Workout> = items

    override suspend fun getWorkoutsByBodyPart(bodyPartName: String): List<Workout> {
        val target = bodyPartName.normalize()
        if (target == "all") return items
        return items.filter { it.bodyPart.name.normalize() == target.normalize() }
    }

    private fun String.normalize(): String = trim().lowercase()

    private companion object {
        val items: List<Workout> = listOf(
            Workout(
                id = 1,
                title = "Workout 1",
                duration = "25 min",
                bodyPart = BodyPart(1, "Chest"),
                imageUrl = "https://images.unsplash.com/photo-1558611848-73f7eb4001a1?w=1200"
            ),
            Workout(
                id = 2,
                title = "Workout 2",
                duration = "30 min",
                bodyPart = BodyPart(2, "Arm"),
                imageUrl = "https://images.unsplash.com/photo-1599058917212-d750089bc07e?w=1200"
            ),
            Workout(
                id = 3,
                title = "Workout 3",
                duration = "20 min",
                bodyPart = BodyPart(3, "Back"),
                imageUrl = "https://images.unsplash.com/photo-1517963879433-6ad2b056d712?w=1200"
            ),
            Workout(
                id = 4,
                title = "Workout 4",
                duration = "35 min",
                bodyPart = BodyPart(4, "Shoulder"),
                imageUrl = "https://images.unsplash.com/photo-1534367610401-9f51e1384e96?w=1200"
            ),
            Workout(
                id = 5,
                title = "Workout 5",
                duration = "40 min",
                bodyPart = BodyPart(2, "Chest"),
                imageUrl = ""
            ),
            Workout(
                id = 6,
                title = "Workout 6",
                duration = "30 min",
                bodyPart = BodyPart(2, "Chest"),
                imageUrl = "https://images.unsplash.com/photo-1596357395100-25d7d9e20fc6?w=1200"
            ),
        )
    }
}