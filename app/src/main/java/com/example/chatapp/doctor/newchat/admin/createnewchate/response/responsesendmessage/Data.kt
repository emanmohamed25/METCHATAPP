package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsesendmessage


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("channel")
    val channel: Channel,
    @SerializedName("message")
    val message: Message
)