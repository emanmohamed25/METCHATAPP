package com.example.chatapp

import com.example.chatapp.doctor.newchat.sendmessage.DataClass
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("admin-login")
    fun loginAdmin(@Query("email") email: String,@Query("password") password: String,
    ): Call<ResponseAdmin>
    @POST("student-login")
    fun loginstudent(@Body request: LoginRequestStudent):  Call<ResponseStudent>

    //function for send message test
    @POST("send-message")
    fun storePost(@Body post : DataClass): Call<DataClass>
}
