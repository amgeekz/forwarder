package com.geekz.forwarder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        Prefs.init(this)
        setContentView(R.layout.activity_main)

        open(AppsFragment())

        findViewById<BottomNavigationView>(R.id.bottomNav)
            .setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.nav_apps -> open(AppsFragment())
                    R.id.nav_monitor -> open(MonitorFragment())
                    R.id.nav_settings -> open(SettingsFragment())
                }
                true
            }
    }

    private fun open(f: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, f)
            .commit()
    }
}