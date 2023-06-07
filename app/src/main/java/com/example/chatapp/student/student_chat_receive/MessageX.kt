package com.example.chatapp.student.student_chat_receive

data class MessageX(
    var content: String?=null,
    var created_at: String?=null,
    var file: String?=null,
    var id: Int,
    var user_id: Int ?=null,
    var user_name: String?=null
)