package com.geekz.forwarder

import android.app.Application

class GeekzApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Prefs.init(this)
    }
}