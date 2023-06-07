package com.example.chatapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.ActivityHomeChatScreenBinding
import com.example.chatapp.databinding.ActivityStarredMessageBinding
import com.example.chatapp.doctor.newchat.admin.util.Constants
import com.example.chatapp.student.student_chat_receive.BubbleAdapter
import com.example.chatapp.student.student_chat_receive.PrivateChatApi
import com.example.chatapp.student.student_chat_receive.PrivateStudentChatRes
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Starred_message_activity : AppCompatActivity() {
    lateinit var list :MutableList<DataXX>
    lateinit var starchatsrecyclerview: RecyclerView
    lateinit var starmessadapter:Starred_Message_Adapter

    private lateinit var privatestarapi: GetStarmessApi
    var myshared: SharedPreferences? = null
    lateinit var binding: ActivityStarredMessageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarredMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backitem.setOnClickListener(){finish()}
        list = ArrayList()
        starmessadapter = Starred_Message_Adapter(applicationContext, mutableListOf())
        starchatsrecyclerview = findViewById(R.id.receyclerstarredmessage)
        starchatsrecyclerview.layoutManager = LinearLayoutManager(this)
        starchatsrecyclerview.adapter = starmessadapter

        myshared = getSharedPreferences("myshared", 0)
        var mytoken = myshared?.getString("studenttoken", "").toString()
        Log.d("iiiiiiiiiiiiiiiiiiiiii", mytoken.toString())
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(Interceptor { chain ->
            val request: Request =
                chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer $mytoken")
                    .build()
            chain.proceed(request)
        })
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        privatestarapi= retrofit.create(GetStarmessApi::class.java)
        val call = privatestarapi.getstarmessage(mytoken.toString())
        call.enqueue(
            object : Callback<ResponseGetStarredMess> {
                override fun onResponse(
                    call: Call<ResponseGetStarredMess>,
                    response: Response<ResponseGetStarredMess>
                ) {
                    val x = response.code()
                    Log.d("cccccccccccccc", x.toString())

                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "catchstareedmess", Toast.LENGTH_SHORT)
                            .show()
                        val x =response.body()
                        starmessadapter=Starred_Message_Adapter(baseContext, x!!.data)
                        starchatsrecyclerview.adapter=starmessadapter
                        val f = response.code().toString()
                        Log.d("nnnnnnnnnnnnnnn", f.toString())

                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error fetching starred message",
                            Toast.LENGTH_SHORT,
                        )
                            .show()
                    }

                }

                override fun onFailure(call: Call<ResponseGetStarredMess>, t: Throwable) {
                    Toast.makeText(applicationContext,
                        "Error cause failur is:$t",
                        Toast.LENGTH_SHORT)
                        .show()

                }


            },
        )

    }
}