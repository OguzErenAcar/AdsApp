package com.example.objtradeapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TradeApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}