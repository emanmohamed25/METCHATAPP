package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responseallstudent


import com.google.gson.annotations.SerializedName

data class SingleStudentResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)