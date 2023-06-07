package com.example.chatapp.student.pusher


import com.google.gson.annotations.SerializedName

data class Participant(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("pivot")
    val pivot: Pivot,
    @SerializedName("role")
    val role: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)