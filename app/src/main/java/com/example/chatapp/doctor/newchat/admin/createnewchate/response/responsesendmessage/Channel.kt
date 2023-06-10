package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsesendmessage


import com.google.gson.annotations.SerializedName

data class Channel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_message")
    val lastMessage: LastMessage,
    @SerializedName("name")
    val name: String,
    @SerializedName("unseen")
    val unseen: Int
)