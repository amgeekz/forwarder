package com.geekz.forwarder

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class AppAdapter(private val list: List<AppItem>) :
    RecyclerView.Adapter<AppAdapter.VH>() {

    inner class VH(v: View) : RecyclerView.ViewHolder(v) {
        val icon: ImageView = v.findViewById(R.id.appIcon)
        val name: TextView = v.findViewById(R.id.appName)
        val pkg: TextView = v.findViewById(R.id.appPkg)
        val sw: Switch = v.findViewById(R.id.appSwitch)
    }

    override fun onCreateViewHolder(p: ViewGroup, t: Int): VH {
        val v = LayoutInflater.from(p.context)
            .inflate(R.layout.item_app, p, false)
        return VH(v)
    }

    override fun onBindViewHolder(h: VH, i: Int) {
        val app = list[i]
        h.icon.setImageDrawable(app.icon)
        h.name.text = app.name
        h.pkg.text = app.packageName
        h.sw.isChecked = app.enabled

        h.sw.setOnCheckedChangeListener { _, on ->
            val set = Prefs.allowedApps.toMutableSet()
            if (on) set.add(app.packageName) else set.remove(app.packageName)
            Prefs.allowedApps = set
        }
    }

    override fun getItemCount() = list.size
}