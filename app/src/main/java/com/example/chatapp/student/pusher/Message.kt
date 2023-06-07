package com.example.chatapp.student.pusher


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("channel")
    val channel: Channel,
    @SerializedName("channel_id")
    val channelId: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("file")
    val `file`: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: Int
)