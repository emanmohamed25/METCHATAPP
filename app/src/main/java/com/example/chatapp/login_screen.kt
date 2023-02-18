package com.example.chatapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class login_screen : AppCompatActivity() {
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user_id = findViewById<EditText>(R.id.ed_id)
        val user_password = findViewById<EditText>(R.id.ed_password)
        setContentView(R.layout.activity_login_screen)
        setContentView(R.layout.activity_login_screen)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://your-backend-url.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        loginbtn.setOnClickListener {
            val id = user_id.toString()
            val password = user_password.toString()
            val loginRequest = LoginRequest(id, password)



            apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>,
                ) {
                    if (response.isSuccessful) {
                        val token = response.body()?.token
                        saveTokenToSharedPreferences(token)
                        startChatActivity()
                    } else {
                        Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun saveTokenToSharedPreferences(token: String?) {
        val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    private fun startChatActivity() {
        val intent = Intent(this, home_chat_screen::class.java)
        startActivity(intent)
        finish()
    }
}