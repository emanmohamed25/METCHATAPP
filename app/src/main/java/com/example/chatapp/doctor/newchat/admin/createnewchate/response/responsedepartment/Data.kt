package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("channel_id")
    val channelId: Int,
    @SerializedName("channel_name")
    val channelName: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_name")
    val userName: String
)