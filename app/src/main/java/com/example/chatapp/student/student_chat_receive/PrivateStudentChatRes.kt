package com.example.chatapp.student.student_chat_receive

data class PrivateStudentChatRes(
    val messages: MutableList<MessageX>,
    val status: String
)