package com.fab.phgpslocator

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class HiltApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

    }
}