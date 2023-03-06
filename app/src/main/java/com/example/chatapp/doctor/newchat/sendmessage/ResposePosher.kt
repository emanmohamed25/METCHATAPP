package com.example.chatapp.doctor.newchat.sendmessage


import com.google.gson.annotations.SerializedName

data class ResposePosher(
    @SerializedName("channel")
    val channel: Channel,
    @SerializedName("channel_id")
    val channelId: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("user_id")
    val userId: Int
)