package com.cairosquad.evolvefit.repository.workout

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.model.FocusArea
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository
import com.cairosquad.evolvefit.repository.workout.remote.WorkoutRemoteDataSource
import kotlinx.coroutines.delay

class WorkoutRepositoryImpl(
    private val workoutRemoteDataSource: WorkoutRemoteDataSource,
) : WorkoutRepository {

    override suspend fun getWorkoutById(id: String): Workout {
        delay(500L)
        return fakeWorkout
    }

    override suspend fun getSuggestedWorkouts(): List<Workout> {
        TODO("Not yet implemented")
    }

    override suspend fun getCommunityWorkouts(): List<Workout> {
        TODO("Not yet implemented")
    }

    override suspend fun getCommunityWorkoutsByFocusArea(focusArea: FocusArea): List<Workout> {
        TODO("Not yet implemented")
    }

    override suspend fun getFavoriteWorkouts(): List<Workout> {
        TODO("Not yet implemented")
    }

    override suspend fun createWorkout(workout: Workout) {
        TODO("Not yet implemented")
    }

    override suspend fun addWorkoutToFavorites(workoutId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getWorkoutsByFocusArea(focusArea: FocusArea): List<Workout> {
        TODO("Not yet implemented")
    }

    private companion object {
        val fakeWorkout = Workout(
            id = "1",
            name = "Upper Body",
            imageUrl = "https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg",
            level = Workout.WorkoutLevel.BEGINNER,
            exercises = listOf(
                Exercise(
                    id = "0",
                    name = "Push-up",
                    specification = Exercise.Specification.Reps(10),
                    imageUrls = listOf("https://images.ctfassets.net/6ilvqec50fal/JdeBsAsNI2XepyM4IDL1U/ef2c96e26f7c3af5bce6db428cd1237f/Screenshot_2024-03-21_at_12.36.05_PM.png"),
                    equipment = Equipment(0, "Body Weight"),
                    focusAreas = setOf(),
                    instructions = listOf("this exercise is for for your health"),
                    estimatedTimeInSeconds = 60,
                ),
                Exercise(
                    id = "1",
                    name = "Running - Treadmill",
                    specification = Exercise.Specification.Time(30),
                    imageUrls = listOf("https://mrtreadmill.com.au/wp-content/uploads/shutterstock_1495412588-1.jpg"),
                    equipment = Equipment(0, "Body Weight"),
                    focusAreas = setOf(),
                    instructions = listOf("this exercise is for for your health"),
                    estimatedTimeInSeconds = 60,
                ),
                Exercise(
                    id = "0",
                    name = "Push-up",
                    specification = Exercise.Specification.Reps(10),
                    imageUrls = listOf("https://images.ctfassets.net/6ilvqec50fal/JdeBsAsNI2XepyM4IDL1U/ef2c96e26f7c3af5bce6db428cd1237f/Screenshot_2024-03-21_at_12.36.05_PM.png"),
                    equipment = Equipment(0, "Body Weight"),
                    focusAreas = setOf(),
                    instructions = listOf("this exercise is for for your health"),
                    estimatedTimeInSeconds = 60,
                ),
                Exercise(
                    id = "1",
                    name = "Running - Treadmill",
                    specification = Exercise.Specification.Time(30),
                    imageUrls = listOf("https://mrtreadmill.com.au/wp-content/uploads/shutterstock_1495412588-1.jpg"),
                    equipment = Equipment(0, "Body Weight"),
                    focusAreas = setOf(),
                    instructions = listOf("this exercise is for for your health"),
                    estimatedTimeInSeconds = 60,
                ),
                Exercise(
                    id = "0",
                    name = "Push-up",
                    specification = Exercise.Specification.Reps(10),
                    imageUrls = listOf("https://images.ctfassets.net/6ilvqec50fal/JdeBsAsNI2XepyM4IDL1U/ef2c96e26f7c3af5bce6db428cd1237f/Screenshot_2024-03-21_at_12.36.05_PM.png"),
                    equipment = Equipment(0, "Body Weight"),
                    focusAreas = setOf(),
                    instructions = listOf("this exercise is for for your health"),
                    estimatedTimeInSeconds = 60,
                ),
                Exercise(
                    id = "1",
                    name = "Running - Treadmill",
                    specification = Exercise.Specification.Time(30),
                    imageUrls = listOf("https://mrtreadmill.com.au/wp-content/uploads/shutterstock_1495412588-1.jpg"),
                    equipment = Equipment(0, "Body Weight"),
                    focusAreas = setOf(),
                    instructions = listOf("this exercise is for for your health"),
                    estimatedTimeInSeconds = 60,
                ),
            ),
            description = "this workout is good for your health",
            estimatedTimeInSeconds = 60 * 10
        )
    }
}