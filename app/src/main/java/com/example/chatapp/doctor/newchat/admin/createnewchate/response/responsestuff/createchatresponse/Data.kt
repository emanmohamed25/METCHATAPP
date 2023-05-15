package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsestuff.createchatresponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("group")
    val group: Group,
    @SerializedName("message")
    val message: Message
)