package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.levelspinner


import com.google.gson.annotations.SerializedName

data class LevelsResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)