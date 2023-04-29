package com.example.chatapp.student.student_chat_receive

import com.example.chatapp.student.student_chat_receive.Message

data class PrivateStudentChatResponse(
    val messages: List<Message>,
    val status: String
)