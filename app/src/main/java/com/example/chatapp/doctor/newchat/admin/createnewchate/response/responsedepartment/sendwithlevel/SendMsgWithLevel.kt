package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.sendwithlevel


import com.google.gson.annotations.SerializedName

data class SendMsgWithLevel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("year_level")
    val yearLevel: YearLevel
)