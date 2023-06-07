package com.example.chatapp.student.pusher


import com.google.gson.annotations.SerializedName

data class Pivot(
    @SerializedName("channel_id")
    val channelId: Int,
    @SerializedName("user_id")
    val userId: Int
)