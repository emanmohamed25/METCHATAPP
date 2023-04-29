package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.spinner


import com.google.gson.annotations.SerializedName

data class DepartmentSpinnerResponse(
    @SerializedName("data")
    val data: List<Data>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)