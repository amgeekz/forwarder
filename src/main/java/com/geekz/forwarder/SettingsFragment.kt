package com.geekz.forwarder

import android.content.Intent
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    override fun onViewCreated(v: View, b: android.os.Bundle?) {
        v.findViewById<Switch>(R.id.swEnable).apply {
            isChecked = Prefs.enabled
            setOnCheckedChangeListener { _, b -> Prefs.enabled = b }
        }

        val edt = v.findViewById<EditText>(R.id.edtUrl)
        edt.setText(Prefs.endpoint)

        v.findViewById<Button>(R.id.btnSave).setOnClickListener {
            Prefs.endpoint = edt.text.toString()
        }

        v.findViewById<Button>(R.id.btnNotif).setOnClickListener {
            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
        }
    }
}