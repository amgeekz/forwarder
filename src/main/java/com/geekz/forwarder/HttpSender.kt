package com.geekz.forwarder

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

object HttpSender {
    private val client = OkHttpClient()

    fun send(json: JSONObject) {
        if (Prefs.endpoint.isEmpty()) return

        val body = json.toString()
            .toRequestBody("application/json".toMediaType())

        val req = Request.Builder()
            .url(Prefs.endpoint)
            .post(body)
            .build()

        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: java.io.IOException) {}
            override fun onResponse(call: Call, response: Response) {}
        })
    }
}