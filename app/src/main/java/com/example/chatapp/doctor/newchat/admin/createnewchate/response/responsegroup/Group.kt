package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsegroup


import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)