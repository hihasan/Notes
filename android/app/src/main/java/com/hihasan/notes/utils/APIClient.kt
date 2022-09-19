package com.hihasan.notes.utils

import com.loopj.android.http.SyncHttpClient

class APIClient {

    //Base URL will be here. It will be changed based on server IP/Domain Name
    val BASE_URL = "127.0.0.1:8080/api/v1/"
    private val client: SyncHttpClient = SyncHttpClient()

    fun getInstance(): SyncHttpClient {
        return client
    }
}