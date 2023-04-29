package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsegroup.list.student


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("label")
    val label: String,
    @SerializedName("url")
    val url: String
)