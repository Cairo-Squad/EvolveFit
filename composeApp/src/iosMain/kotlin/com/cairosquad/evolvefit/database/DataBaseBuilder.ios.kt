package com.cairosquad.evolvefit.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.cairosquad.evolvefit.repository.workout.local.AppDatabase
import com.cairosquad.evolvefit.repository.workout.local.DatabaseConfig
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSUserDomainMask

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFilePath = documentDirectory() + DatabaseConfig.DATABASE_NAME
    return Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
    )
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}