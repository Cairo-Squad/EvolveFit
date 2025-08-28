@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.cairosquad.evolvefit.repository.workout.local


import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.cairosquad.evolvefit.repository.workout.local.entity.PlayedWorkoutEntity

@Database(
    entities = [PlayedWorkoutEntity::class],
    version = 1,
    exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playedWorkoutDao(): PlayedWorkoutDao
}
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}