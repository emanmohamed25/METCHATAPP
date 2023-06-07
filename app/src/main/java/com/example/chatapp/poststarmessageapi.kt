package com.example.chatapp

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface poststarmessageapi {
    @POST("star-message")
    fun send(@Header("Authorization") accessToken: String, @Query("message_id") message_id: Int?): Call<poststarmessage>
}