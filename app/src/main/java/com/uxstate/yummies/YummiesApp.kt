package com.uxstate.yummies

import android.app.Application
import timber.log.Timber

class YummiesApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}