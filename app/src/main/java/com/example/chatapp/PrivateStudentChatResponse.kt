package com.example.chatapp

data class PrivateStudentChatResponse(
    val messages: List<Message>,
    val status: String
)