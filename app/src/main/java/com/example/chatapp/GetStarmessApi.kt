package com.example.chatapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Header

interface GetStarmessApi {
    @GET("starred-messages")
    fun getstarmessage(@Header("Authorization") accessToken: String): Call<ResponseGetStarredMess>
}