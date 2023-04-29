package com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.sendwithsection


import com.google.gson.annotations.SerializedName

data class SendMsgWithSection(

    @SerializedName("message")
    val message: String,
    @SerializedName("sections")
    val sections: List<Section>,
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
val `data`: List<Data>
)