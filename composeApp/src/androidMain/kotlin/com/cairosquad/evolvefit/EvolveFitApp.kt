package com.cairosquad.evolvefit

import android.app.Application
import com.cairosquad.evolvefit.di.initKoin
import org.koin.android.ext.koin.androidContext

class EvolveFitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@EvolveFitApp)
        }
    }
}