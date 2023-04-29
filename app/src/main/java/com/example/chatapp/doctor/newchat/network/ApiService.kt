package com.example.chatapp.doctor.newchat.network

import android.media.tv.SectionResponse
import com.example.chatapp.LoginRequestStudent
import com.example.chatapp.ResponseAdmin
import com.example.chatapp.ResponseStudent
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.DataGroupRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.DepartmentRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.ListStudentRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.DepartmentResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.levelspinner.LevelsResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.section.SectionsResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.sendwithdepartment.SendMsgWithDepartment
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.sendwithlevel.SendMsgWithLevel
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.sendwithsection.SendMsgWithSection
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.spinner.DepartmentSpinnerResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsegroup.DataGroupResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsegroup.list.student.ListStudentResponse
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
    fun sendDepartmentMessageDepart(
        @Header("Authorization") authHeader: String, @Body request: DepartmentRequest
    ): Call<SendMsgWithDepartment>


    @POST("create-chat")
    fun sendDepartmentMessageLevel(
        @Header("Authorization") authHeader: String, @Body request: DepartmentRequest
    ): Call<SendMsgWithLevel>


    @POST("create-chat")
    fun sendDepartmentMessageSection(
        @Header("Authorization") authHeader: String, @Body request: DepartmentRequest
    ): Call<SendMsgWithSection>
    @POST("create-group")
    fun sendDataGroupMessage(
        @Header("Authorization") authHeader: String, @Body request: DataGroupRequest
    ):Call<DataGroupResponse>

//department kinds
    @GET("departments")
     fun getDepartment():Call<DepartmentSpinnerResponse>

     @GET("year-levels")
     fun getLevels(@Query("department_id") departmentId: Int):Call<LevelsResponse>

     @GET("sections")
     fun  getSections(@Query("year_level_id") yearLevelId: Int):Call<SectionsResponse>

     @POST("student-search")
     fun getListStudents(@Body response: ListStudentRequest):Call<ListStudentResponse>
}
