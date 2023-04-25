package com.example.chatapp

data class Chat(
    val id: Int,
    val last_message: LastMessage,
    val name: String,
    val unseen: Int
)