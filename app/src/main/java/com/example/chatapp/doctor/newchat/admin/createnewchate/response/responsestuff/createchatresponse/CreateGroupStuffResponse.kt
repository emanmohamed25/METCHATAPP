package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsestuff.createchatresponse


import com.google.gson.annotations.SerializedName

data class CreateGroupStuffResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)