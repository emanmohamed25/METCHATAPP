package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.section


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("year_level")
    val yearLevel: YearLevel
)