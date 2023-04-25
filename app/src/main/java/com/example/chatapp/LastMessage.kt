package com.example.chatapp

data class LastMessage(
    val content: String,
    val created_at: String,
    val id: Int,
    val user_id: Int,
    val user_name: String
)