package com.cairosquad.evolvefit

import android.app.Application
import com.cairosquad.evolvefit.di.initKoin
import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.manualFileKitCoreInitialization
import org.koin.android.ext.koin.androidContext

class EvolveFitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        FileKit.manualFileKitCoreInitialization(this)
        initKoin {
            androidContext(this@EvolveFitApp)
        }
    }
}