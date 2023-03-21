package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment


import com.google.gson.annotations.SerializedName

data class DepartmentResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)