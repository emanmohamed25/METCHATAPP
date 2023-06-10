package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsesendmessage


import com.google.gson.annotations.SerializedName

data class LastMessage(
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_name")
    val userName: String
)