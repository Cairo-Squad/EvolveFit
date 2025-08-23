package com.cairosquad.evolvefit

import android.app.Application
import android.content.Context
import com.cairosquad.evolvefit.di.initKoin
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.manualFileKitCoreInitialization
import org.koin.android.ext.koin.androidContext

class EvolveFitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        FileKit.manualFileKitCoreInitialization(this)
        initKoin {
            androidContext(this@EvolveFitApp)
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}