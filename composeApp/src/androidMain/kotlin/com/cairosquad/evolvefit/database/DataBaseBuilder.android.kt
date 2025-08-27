package com.cairosquad.evolvefit.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.cairosquad.evolvefit.EvolveFitApp
import com.cairosquad.evolvefit.repository.workout.local.AppDatabase
import com.cairosquad.evolvefit.repository.workout.local.DatabaseConfig

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    return Room.databaseBuilder(
        EvolveFitApp.appContext,
        AppDatabase::class.java,
        DatabaseConfig.DATABASE_NAME
    )
}