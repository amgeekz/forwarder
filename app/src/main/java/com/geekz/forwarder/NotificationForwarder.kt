package com.geekz.forwarder

import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import org.json.JSONObject

class NotificationForwarder : NotificationListenerService() {

    private val MIN_AMOUNT = 1000
    private val MAX_AMOUNT = 100_000_000

    override fun onCreate() {
        super.onCreate()
        startForegroundService(
            Intent(this, ForegroundService::class.java)
        )
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        if (!Prefs.enabled) return
        if (!Prefs.allowedApps.contains(sbn.packageName)) return

        val e = sbn.notification.extras
        val title = e.getString("android.title") ?: ""
        val text = e.getCharSequence("android.text")?.toString() ?: ""
        val joined = "$title $text"

        val amount = extractAmount(joined) ?: return
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) return

        val fp = DedupStore.fingerprint(
            sbn.packageName + joined + sbn.postTime
        )
        if (DedupStore.isDuplicate(this, fp)) return

        HttpSender.send(JSONObject().apply {
            put("package", sbn.packageName)
            put("title", title)
            put("content", text)
            put("amount", amount)
            put("fingerprint", fp)
            put("time", System.currentTimeMillis())
        })
    }

    private fun extractAmount(s: String): Int? {
        val r = Regex("Rp\\s?([0-9.,]+)", RegexOption.IGNORE_CASE)
        val m = r.find(s) ?: return null
        return m.groupValues[1].replace("[^0-9]".toRegex(), "").toIntOrNull()
    }
}