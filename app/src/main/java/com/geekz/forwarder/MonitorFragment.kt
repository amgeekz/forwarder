package com.geekz.forwarder

import android.view.View
import androidx.fragment.app.Fragment
import org.json.JSONObject

class MonitorFragment : Fragment(R.layout.fragment_monitor) {

    override fun onViewCreated(v: View, b: android.os.Bundle?) {
        v.findViewById<View>(R.id.btnTest).setOnClickListener {
            HttpSender.send(JSONObject().apply {
                put("package", "test")
                put("title", "SIMULATION")
                put("content", "DANA: Kamu terima transfer Rp 10.000")
                put("test", true)
            })
        }
    }
}