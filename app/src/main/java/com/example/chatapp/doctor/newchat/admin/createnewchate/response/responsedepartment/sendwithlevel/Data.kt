package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.sendwithlevel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("channel_id")
    val channelId: Int,
    @SerializedName("channel_name")
    val channelName: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("created_at")
    val createdAt: String,
//    @SerializedName("file")
//    val `file`: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val type: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_name")
    val userName: String
)