package com.example.chatapp.doctor.newchat.admin.createnewchate.data

data class StuffRequest(
    val group_name:String,
    val staff :MutableList<String>,
    val message:String
)
