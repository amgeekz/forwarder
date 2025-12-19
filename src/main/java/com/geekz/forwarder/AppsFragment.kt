package com.geekz.forwarder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AppsFragment : Fragment(R.layout.fragment_apps) {

    override fun onViewCreated(v: View, b: Bundle?) {
        val rv = v.findViewById<RecyclerView>(R.id.recycler)
        rv.layoutManager = LinearLayoutManager(requireContext())

        val pm = requireContext().packageManager
        val selected = Prefs.allowedApps

        val apps = pm.getInstalledApplications(0)
            .filter { pm.getLaunchIntentForPackage(it.packageName) != null }
            .map {
                AppItem(
                    pm.getApplicationLabel(it).toString(),
                    it.packageName,
                    pm.getApplicationIcon(it),
                    selected.contains(it.packageName)
                )
            }
            .sortedBy { it.name.lowercase() }

        rv.adapter = AppAdapter(apps)
    }
}