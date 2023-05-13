package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsestuff


import com.google.gson.annotations.SerializedName

data class GetStuffResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)