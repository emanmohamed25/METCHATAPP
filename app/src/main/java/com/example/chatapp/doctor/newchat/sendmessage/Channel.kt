package com.example.chatapp.doctor.newchat.sendmessage


import com.google.gson.annotations.SerializedName

data class Channel(
    @SerializedName("chattable_id")
    val chattableId: Int,
    @SerializedName("chattable_type")
    val chattableType: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("participants")
    val participants: List<Participant>,
    @SerializedName("updated_at")
    val updatedAt: String
)