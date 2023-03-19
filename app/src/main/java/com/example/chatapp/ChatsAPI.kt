package com.example.chatapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ChatsAPI {
    @GET("/chats")
    fun getChats(@Header("Authorization") accessToken: String): Call<ChatsComponent>
}