package com.example.chatapp.student.pusher


import com.google.gson.annotations.SerializedName

data class ResponsePusher(
    @SerializedName("message")
    val message: Message
)