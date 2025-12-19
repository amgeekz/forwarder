package com.geekz.forwarder

import android.content.Context
import android.content.SharedPreferences

object Prefs {
    private lateinit var sp: SharedPreferences

    fun init(ctx: Context) {
        sp = ctx.getSharedPreferences("geekz", Context.MODE_PRIVATE)
    }

    var enabled: Boolean
        get() = sp.getBoolean("enabled", true)
        set(v) = sp.edit().putBoolean("enabled", v).apply()

    var endpoint: String
        get() = sp.getString("endpoint", "") ?: ""
        set(v) = sp.edit().putString("endpoint", v).apply()

    var allowedApps: Set<String>
        get() = sp.getStringSet("apps", emptySet()) ?: emptySet()
        set(v) = sp.edit().putStringSet("apps", v).apply()
}