package com.geekz.forwarder

import android.content.Context
import java.security.MessageDigest

object DedupStore {

    private const val MAX = 3000

    fun fingerprint(text: String): String {
        val md = MessageDigest.getInstance("MD5")
        return md.digest(text.toByteArray())
            .joinToString("") { "%02x".format(it) }
    }

    fun isDuplicate(ctx: Context, fp: String): Boolean {
        val sp = ctx.getSharedPreferences("dedup", Context.MODE_PRIVATE)
        val set = sp.getStringSet("keys", LinkedHashSet())!!.toMutableSet()

        if (set.contains(fp)) return true

        set.add(fp)
        if (set.size > MAX) {
            while (set.size > MAX) set.remove(set.first())
        }

        sp.edit().putStringSet("keys", set).apply()
        return false
    }
}