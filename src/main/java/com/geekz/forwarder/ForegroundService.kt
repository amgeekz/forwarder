package com.geekz.forwarder

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder

class ForegroundService : Service() {

    override fun onCreate() {
        super.onCreate()
        startForeground(1, notif())
    }

    private fun notif(): Notification {
        val chId = "geekz_fw"
        if (Build.VERSION.SDK_INT >= 26) {
            val ch = NotificationChannel(
                chId, "Geekz Forwarder",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(ch)
        }

        return Notification.Builder(this, chId)
            .setContentTitle("Geekz Forwarder")
            .setContentText("Running & listening notifications")
            .setSmallIcon(android.R.drawable.ic_menu_send)
            .build()
    }

    override fun onBind(i: Intent?): IBinder? = null
}