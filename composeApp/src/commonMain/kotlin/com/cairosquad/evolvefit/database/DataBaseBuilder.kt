package com.cairosquad.evolvefit.database


import androidx.room.RoomDatabase
import com.cairosquad.evolvefit.repository.workout.local.AppDatabase

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>