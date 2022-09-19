package com.hihasan.notes.utils

import com.loopj.android.http.SyncHttpClient

class APIClient {

    //API Response Will be here
    val BASE_URL = ""
    private val client: SyncHttpClient = SyncHttpClient()

    fun getInstance(): SyncHttpClient {
        return client
    }
}