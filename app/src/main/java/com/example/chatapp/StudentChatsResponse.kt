package com.example.chatapp

data class StudentChatsResponse(
    val chats: List<Chat>,
    val status: String
)