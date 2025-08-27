package com.cairosquad.evolvefit.repository.workout.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.cairosquad.evolvefit.repository.workout.local.entity.PlayedWorkoutEntity

@Database(
    entities = [PlayedWorkoutEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playedWorkoutDao(): PlayedWorkoutDao
}