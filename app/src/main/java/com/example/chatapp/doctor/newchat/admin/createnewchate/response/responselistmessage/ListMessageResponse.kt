package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responselistmessage


import com.google.gson.annotations.SerializedName

data class ListMessageResponse(
    @SerializedName("messages")
    val messages: List<Message>,
    @SerializedName("status")
    val status: String
)