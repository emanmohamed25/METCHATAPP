package com.example.chatapp.doctor.newchat.network

import com.example.chatapp.LoginRequestStudent
import com.example.chatapp.ResponseAdmin
import com.example.chatapp.ResponseStudent
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.DataGroupRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.DepartmentRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.DepartmentResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.spinner.DepartmentSpinnerResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsegroup.DataGroupResponse
import com.example.chatapp.doctor.newchat.sendmessage.DataClass
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("admin-login")
    fun loginAdmin(
        @Query("email") email: String, @Query("password") password: String,
    ): Call<ResponseAdmin>

    @POST("student-login")
    fun loginstudent(@Body request: LoginRequestStudent): Call<ResponseStudent>

    //function for send message test
    @POST("send-message")
    fun storePost(@Body post: DataClass): Call<DataClass>

    //end point for create new chat As Department
    @POST("create-chat")
    fun sendDepartmentMessage(
        @Header("Authorization") authHeader: String, @Body request: DepartmentRequest
    ): Call<DepartmentResponse>

    @POST("create-group")
    fun sendDataGroupMessage(
        @Header("Authorization") authHeader: String, @Body request: DataGroupRequest
    ):Call<DataGroupResponse>

//department kinds
    @GET("departments")
    fun getDepartment():Call<DepartmentSpinnerResponse>
}
