package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsesendmessage


import com.google.gson.annotations.SerializedName

data class SendMessageResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)