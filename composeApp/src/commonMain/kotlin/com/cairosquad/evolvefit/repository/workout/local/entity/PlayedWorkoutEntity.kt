package com.cairosquad.evolvefit.repository.workout.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "played_workouts")
data class PlayedWorkoutEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val workoutId: String,
    val durationSeconds: Int,
    val isSynced: Boolean = false
)