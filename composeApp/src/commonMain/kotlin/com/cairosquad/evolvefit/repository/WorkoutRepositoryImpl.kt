package com.cairosquad.evolvefit.repository

import com.cairosquad.evolvefit.domain.entity.Equipment
import com.cairosquad.evolvefit.domain.entity.Exercise
import com.cairosquad.evolvefit.domain.entity.MeasurementType
import com.cairosquad.evolvefit.domain.entity.Workout
import com.cairosquad.evolvefit.domain.repository.WorkoutRepository
import com.cairosquad.evolvefit.repository.remote.safeApiCall
import com.cairosquad.evolvefit.repository.remote.toDomain
import com.cairosquad.evolvefit.repository.remote.toDto
import com.cairosquad.evolvefit.repository.remote.workout.WorkoutRemoteDataSource
import kotlinx.coroutines.delay

class WorkoutRepositoryImpl(
    private val workoutRemoteDataSource: WorkoutRemoteDataSource,
) : WorkoutRepository {

    override suspend fun getEquipments(): List<Equipment>  {
        return safeApiCall {  workoutRemoteDataSource.getEquipments().map { it.toDomain() } }
    }

    override suspend fun createExercise(exercise: Exercise) = safeApiCall{
        workoutRemoteDataSource.createExercise(exercise.toDto())
    }

    override suspend fun getWorkoutById(id: String): Workout {
        delay(500L)
        return fakeWorkout
    }

    private companion object {
        val fakeWorkout = Workout(
            id = "1",
            name = "Upper Body",
            imageUrl = "https://phxgymwitham.co.uk/wp-content/uploads/2024/05/Upper-body-gym-workout-1024x681.jpg",
            level = Workout.WorkoutLevel.BEGINNER,
            exercises = listOf(
                Exercise(
                    name = "Push-up",
                    measurementType = MeasurementType.REPS,
                    imageUrl = "https://images.ctfassets.net/6ilvqec50fal/JdeBsAsNI2XepyM4IDL1U/ef2c96e26f7c3af5bce6db428cd1237f/Screenshot_2024-03-21_at_12.36.05_PM.png",
                    id = 0,
                    equipmentIds = emptyList(),
                    measurementValue = 10,
                    focusAreas = setOf(),
                    description = "this exercise is for for your health",
                ),
                Exercise(
                    name = "Running - Treadmill",
                    measurementType = MeasurementType.DURATION,
                    imageUrl = "https://mrtreadmill.com.au/wp-content/uploads/shutterstock_1495412588-1.jpg",
                    id = 1,
                    equipmentIds = emptyList(),
                    measurementValue = 30,
                    focusAreas = setOf(),
                    description = "this exercise is for for your health",
                ),
                Exercise(
                    name = "Push-up",
                    measurementType = MeasurementType.REPS,
                    imageUrl = "https://images.ctfassets.net/6ilvqec50fal/JdeBsAsNI2XepyM4IDL1U/ef2c96e26f7c3af5bce6db428cd1237f/Screenshot_2024-03-21_at_12.36.05_PM.png",
                    id = 0,
                    equipmentIds = emptyList(),
                    measurementValue = 10,
                    focusAreas = setOf(),
                    description = "this exercise is for for your health",
                ),
                Exercise(
                    name = "Running - Treadmill",
                    measurementType = MeasurementType.DURATION,
                    imageUrl = "https://mrtreadmill.com.au/wp-content/uploads/shutterstock_1495412588-1.jpg",
                    id = 1,
                    equipmentIds = emptyList(),
                    measurementValue = 30,
                    focusAreas = setOf(),
                    description = "this exercise is for for your health",
                ),
                Exercise(
                    name = "Push-up",
                    measurementType = MeasurementType.REPS,
                    imageUrl = "https://images.ctfassets.net/6ilvqec50fal/JdeBsAsNI2XepyM4IDL1U/ef2c96e26f7c3af5bce6db428cd1237f/Screenshot_2024-03-21_at_12.36.05_PM.png",
                    id = 0,
                    equipmentIds = emptyList(),
                    measurementValue = 10,
                    focusAreas = setOf(),
                    description = "this exercise is for for your health",
                ),
                Exercise(
                    name = "Running - Treadmill",
                    measurementType = MeasurementType.DURATION,
                    imageUrl = "https://mrtreadmill.com.au/wp-content/uploads/shutterstock_1495412588-1.jpg",
                    id = 1,
                    equipmentIds = emptyList(),
                    measurementValue = 30,
                    focusAreas = setOf(),
                    description = "this exercise is for for your health",
                )
            )
        )
    }
}