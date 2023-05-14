package com.example.chatapp.student.student_chat_receive

data class MessageX(
    val content: String,
    val created_at: String,
    val `file`: String,
    val id: Int,
    val user_id: Int,
    val user_name: String
) {
    constructor(content: String) : this(content,"","",0,0,"")
}