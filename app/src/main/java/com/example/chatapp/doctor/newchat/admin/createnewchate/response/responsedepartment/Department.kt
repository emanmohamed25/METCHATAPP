package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment


import com.google.gson.annotations.SerializedName

data class Department(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)