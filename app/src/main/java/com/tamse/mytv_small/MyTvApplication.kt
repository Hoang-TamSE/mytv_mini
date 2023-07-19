package com.tamse.mytv_small

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyTvApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}