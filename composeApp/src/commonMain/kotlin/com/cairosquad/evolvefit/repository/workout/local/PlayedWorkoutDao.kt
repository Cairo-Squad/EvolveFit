package com.cairosquad.evolvefit.repository.workout.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cairosquad.evolvefit.repository.workout.local.entity.PlayedWorkoutEntity


@Dao
interface PlayedWorkoutDao {
    @Insert
    suspend fun insertPlayedWorkout(workout: PlayedWorkoutEntity): Long

    @Query("SELECT * FROM played_workouts WHERE isSynced = 0")
    suspend fun getPendingWorkouts(): List<PlayedWorkoutEntity>

    @Update
    suspend fun updatePlayedWorkouts(workouts: List<PlayedWorkoutEntity>)

    @Delete
    suspend fun deletePlayedWorkouts(workouts: List<PlayedWorkoutEntity>)
}