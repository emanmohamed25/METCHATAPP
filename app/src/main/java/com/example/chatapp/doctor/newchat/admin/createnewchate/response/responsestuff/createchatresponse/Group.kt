package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsestuff.createchatresponse


import com.google.gson.annotations.SerializedName

data class Group(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)