package com.example.chatapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PrivateChatApi {
    @GET("channel-messages")
    fun getmessages(@Header("Authorization") accessToken: String,@Query("channel_id") id:Int):Call<PrivateStudentChatResponse>

}