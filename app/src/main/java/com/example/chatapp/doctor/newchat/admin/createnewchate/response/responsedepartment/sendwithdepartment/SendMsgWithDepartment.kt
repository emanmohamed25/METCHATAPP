package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.sendwithdepartment


import com.google.gson.annotations.SerializedName

data class SendMsgWithDepartment(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("department")
    val department: Department,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)