package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsegroup


import com.google.gson.annotations.SerializedName

data class DataGroupResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)