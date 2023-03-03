package com.example.chatapp.doctor.newchat.admin


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("user")
    val user: User
)