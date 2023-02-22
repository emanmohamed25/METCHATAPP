package com.example.chatapp

data class LoginRequestStudent(
    val username: String,
    val password: String
)

/*data class LoginResponseStudent(
    val token: String
)*/
data class LoginRequestAdmin(
    val email: String,
    val password: String
)

/*data class LoginResponseAdmin(
    val token: String
)*/