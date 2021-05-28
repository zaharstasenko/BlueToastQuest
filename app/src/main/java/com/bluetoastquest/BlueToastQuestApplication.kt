package com.bluetoastquest

import android.app.Application
import com.bluetoastquest.utils.setupDependencyInjection
import timber.log.Timber

class BlueToastQuestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupDependencyInjection()
        Timber.plant(Timber.DebugTree())
    }
}