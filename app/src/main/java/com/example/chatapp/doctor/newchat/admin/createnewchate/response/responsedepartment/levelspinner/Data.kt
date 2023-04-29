package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.levelspinner


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("department")
    val department: Department,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)