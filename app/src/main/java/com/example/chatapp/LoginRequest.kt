package com.example.chatapp

data class LoginRequest(
    val id: String,
    val password: String
)

data class LoginResponse(
    val token: String
)