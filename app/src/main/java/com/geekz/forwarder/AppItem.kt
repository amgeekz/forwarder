package com.geekz.forwarder

import android.graphics.drawable.Drawable

data class AppItem(
    val name: String,
    val packageName: String,
    val icon: Drawable,
    var enabled: Boolean
)