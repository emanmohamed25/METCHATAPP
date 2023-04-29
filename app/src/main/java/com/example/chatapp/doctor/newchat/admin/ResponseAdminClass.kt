package com.example.chatapp.doctor.newchat.admin


import com.google.gson.annotations.SerializedName

data class ResponseAdminClass(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)