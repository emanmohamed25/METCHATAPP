package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsehomechats


import com.google.gson.annotations.SerializedName

data class ListChatsResponse(
    @SerializedName("chats")
    val chats: List<Chat>,
    @SerializedName("status")
    val status: String
)