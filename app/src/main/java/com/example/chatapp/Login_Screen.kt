package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login_screen.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login_Screen : AppCompatActivity() {
    private lateinit var apiService: ApiService
    var admintoken: String = ""
    var BASE_URL = "http://10.0.2.2:8000/api/"
    var myshared: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login_screen)
        val user_email = findViewById<EditText>(R.id.ed_id)
        val user_password = findViewById<EditText>(R.id.ed_password)
        val KindUser = intent.getStringExtra("user")
        Toast.makeText(this@Login_Screen, KindUser.toString(), Toast.LENGTH_LONG).show()
        try {
            loginbtn.setOnClickListener {
                val okhttp = OkHttpClient.Builder()
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                okhttp.addInterceptor(httpLoggingInterceptor)
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(okhttp.build())
                    .build()

                val email = user_email.text.toString()
                val password = user_password.text.toString()
                /*var email = "test@example.com"
                var password = "123123"*/
                apiService = retrofit.create(ApiService::class.java)
                // var loginRequestAdmin = LoginRequestAdmin(email, password)


                if (KindUser.equals("admin")) {
                    Toast.makeText(this@Login_Screen, KindUser.toString(), Toast.LENGTH_LONG).show()
                    val call = apiService.loginAdmin(email, password)
                    Log.e("e", "bbbbbbbbbbbbbbbbbbbbb")
                    call.enqueue(object : Callback<ResponseAdmin> {
                        override fun onResponse(
                            call: Call<ResponseAdmin>,
                            response: Response<ResponseAdmin>,
                        ) {
                            print(response.body());
                            if (response.isSuccessful) {
                                Log.e("login*", "cccccc")
                                admintoken = response.body()?.data?.access_token.toString()
                                myshared = getSharedPreferences("myshared", 0)
                                var editor: SharedPreferences.Editor = myshared!!.edit()
                                editor.putString("admintoken",admintoken)
                                editor.commit()
                                val intent =Intent (this@Login_Screen, doctortest::class.java)
                                startActivity(intent)
                                finish()
                                Toast.makeText(this@Login_Screen,
                                    response.body()?.message.toString(),
                                    Toast.LENGTH_SHORT)
                                    .show()

                                // saveTokenToSharedPreferences(token)
                                //startHomeChatScreen()
                            } else {
                                Toast.makeText(this@Login_Screen,
                                    response.body()?.message.toString(),
                                    Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }

                        override fun onFailure(call: Call<ResponseAdmin>, t: Throwable) {
                            Toast.makeText(applicationContext, "Error: $t", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                } else if (KindUser.equals("student")) {
                    //  var callStudent = apiService.loginstudent(LoginRequestStudent(email, password))
                    var callStudent = apiService.loginstudent(LoginRequestStudent(email, password))
                    callStudent.enqueue(object : Callback<ResponseStudent> {
                        override fun onResponse(
                            call: Call<ResponseStudent>,
                            response: Response<ResponseStudent>,
                        ) {
                            if (response.isSuccessful) {

                                var authmess:String=response.body()?.message.toString()
                                if (authmess=="Login successfully"){
                                    Toast.makeText(this@Login_Screen,
                                        response.body()?.message.toString(),
                                        Toast.LENGTH_SHORT)
                                        .show()
                                    var studenttoken = response.body()?.data?.access_token.toString()
                                    myshared = getSharedPreferences("myshared", 0)
                                    var editor: SharedPreferences.Editor = myshared!!.edit()
                                    editor.putString("studenttoken", studenttoken)
                                    editor.commit()
                                    var intent1 = Intent(this@Login_Screen, HomeChatScreen::class.java)
                                    intent1.putExtra("tok", studenttoken)
                                    startActivity(intent1)
                                    finish()
                                }else{
                                    Toast.makeText(this@Login_Screen,
                                        "wrong id or password",
                                        Toast.LENGTH_SHORT)
                                        .show()



                                }
/*
                                val status = response.body()?.status.toString()
                                val messageE = response.body()?.message.toString()
                                val username = response.body()?.data?.user?.username.toString()
*/


                                // Toast.makeText(this@Login_Screen,
                                //   "status:$status\n" +
                                //         "message:$message\n" + "username:$username\n",
                                //Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(this@Login_Screen,
                                    response.body()?.message.toString(),
                                    Toast.LENGTH_SHORT)
                                    .show()
                            }

                        }

                        override fun onFailure(call: Call<ResponseStudent>, t: Throwable) {
                            Toast.makeText(applicationContext, "Error is: $t", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                }

            }
        } catch (e: Exception) {
            Log.e("e", e.toString())
        }

        /* if (text.equals("admin")) {
             val loginRequestad = LoginRequestAdmin(id, password)
             apiService.loginAdmin(loginRequestad)
                 .enqueue(object : Callback<LoginResponseAdmin> {
                     override fun onResponse(
                         call: Call<LoginResponseAdmin>,
                         response: Response<LoginResponseAdmin>,
                     ) {
                         if (response.isSuccessful) {
                             val token = response.body()?.token
                             saveTokenToSharedPreferences(token)
                             startHomeChatScreen()
                             finish()
                         } else {
                             Toast.makeText(applicationContext,
                                 "Login failed",
                                 Toast.LENGTH_SHORT)
                                 .show()
                         }
                     }

                     override fun onFailure(call: Call<LoginResponseAdmin>, t: Throwable) {
                         Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_SHORT)
                             .show()
                     }
                 })
         } *//*else if (text.equals("student")) {
                val loginRequestst = LoginRequestStudent(id, password)
                apiService.loginstudent(loginRequestst).enqueue(object :
                    Callback<LoginResponseStudent> {
                    override fun onResponse(
                        call: Call<LoginResponseStudent>,
                        response: Response<LoginResponseStudent>,
                    ) {
                        if (response.isSuccessful) {
                            val token = response.body()?.token
                            saveTokenToSharedPreferences(token)
                            startHomeChatScreen()
                        } else {
                            Toast.makeText(applicationContext,
                                "Login failed",
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(
                        call: Call<LoginResponseStudent>,
                        t: Throwable,
                    ) {
                        Toast.makeText(applicationContext,
                            "Login failed",
                            Toast.LENGTH_SHORT).show()
                    }
                })
            }*/

    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
    }

    private fun saveTokenToSharedPreferences(token: String?) {
        val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    private fun startHomeChatScreen() {
        val intent = Intent(this, HomeChatScreen::class.java)
        startActivity(intent)
        finish()
    }
}