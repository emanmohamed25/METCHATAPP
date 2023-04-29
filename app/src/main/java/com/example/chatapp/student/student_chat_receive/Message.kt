package com.example.chatapp.student.student_chat_receive

data class Message(
    val content: String,
    val created_at: String,
    val id: Int,
    val user_id: Int,
    val user_name: String
)