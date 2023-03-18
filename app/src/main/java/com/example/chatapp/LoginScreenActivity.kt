package com.example.chatapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.databinding.ActivityLoginScreenBinding
import com.example.chatapp.doctor.newchat.sendmessage.ChatStudentActivity
import com.example.chatapp.doctor.newchat.sendmessage.SendMessageActivity
import com.example.chatapp.doctor.newchat.createnewchate.ui.NewChatActivity
import kotlinx.android.synthetic.main.activity_login_screen.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginScreenActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    var token: String = ""

    // var user:Int=0
    //var BASE_URL = "http://10.0.2.2:8000/api/"
    lateinit var binding: ActivityLoginScreenBinding
//    var BASE_URL = "http://192.168.1.53:80/chatapp/public/api/"
    var BASE_URL = "http://192.168.1.60:80/chatapp/public/api/"

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val KindUser = intent.getStringExtra("user")
        Toast.makeText(this@LoginScreenActivity, KindUser.toString(), Toast.LENGTH_LONG).show()
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

                val email = binding.edId.text.toString()
                val password = binding.edPassword.text.toString()
//                var email = "test@example.com"
//                var password = "123123"
                apiService = retrofit.create(ApiService::class.java)
//                var loginRequestAdmin = LoginRequestAdmin(email, password)


                if (KindUser.equals("admin")) {
//                    if ((email.equals("tester2@met.com")
//                        ||email.equals("tester@met.com"))
//                        && password.equals("123123")
//                    )
//                    {
                    if (binding.edId.text.isNullOrEmpty()
                        ||binding.edPassword.text.isNullOrEmpty())
                    {
                        Toast.makeText(
                            applicationContext,
                            "invalid email OR password!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    else{
                    Toast.makeText(
                            this@LoginScreenActivity,
                            KindUser.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        val call = apiService.loginAdmin(email, password)
                        Log.e("e", "bbbbbbbbbbbbbbbbbbbbb")
                        call.enqueue(object : Callback<ResponseAdmin> {
                            override fun onResponse(
                                call: Call<ResponseAdmin>,
                                response: Response<ResponseAdmin>,
                            ) {
                                print(response.body())
                                if (response.isSuccessful) {
                                    val sharedPreferences = getSharedPreferences("myprefsfile", Context.MODE_PRIVATE)
                                    val editor = sharedPreferences.edit()
                                    editor.putBoolean("adminHasLoggedIn",true)
                                    editor.apply()
                                   // token = response.body()?.data?.access_token.toString()
                                    Toast.makeText(
                                        this@LoginScreenActivity,
                                        response.body()?.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    if(response.body()?.message.toString().equals("Login successfully")){
                                    val intent = Intent(
                                        this@LoginScreenActivity,
                                        NewChatActivity::class.java
                                    )
                                    startActivity(intent)}
                                    // saveTokenToSharedPreferences(token)
//                                startSendMessageActivity()
                                } else {
                                    Toast.makeText(
                                        this@LoginScreenActivity,
                                        response.message(),
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }

                            override fun onFailure(call: Call<ResponseAdmin>, t: Throwable) {
                                Toast.makeText(applicationContext, "Error: $t", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        })
                    }
//                else


                } else if (KindUser.equals("student")) {
//                    if ((password.equals("eman") && (email.equals("eman")))
//                        || (password.equals("123123") && (email.equals("std1")
//                                || email.equals("std2") || email.equals("std3")))
//                    ) {
                    if (binding.edId.text.isNullOrEmpty()
                        ||binding.edPassword.text.isNullOrEmpty())
                    {
                        Toast.makeText(
                            applicationContext,
                            "invalid email OR password!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    else{
                        var callStudent =
                            apiService.loginstudent(LoginRequestStudent(email, password))
//                  val callStudent = apiService.loginstudent(LoginRequestStudent("std1", "123123"))
                        //Log.e("e", "bbbbbbbbbbbbbbbbbbbbb")
                        callStudent.enqueue(object : Callback<ResponseStudent> {
                            override fun onResponse(
                                call: Call<ResponseStudent>,
                                response: Response<ResponseStudent>,
                            ) {
                                if (response.isSuccessful) {
                                    val status = response.body()?.status.toString()
                                    val message = response.body()?.message.toString()
                                    val username = response.body()?.data?.user?.username.toString()
                                    val sharedPreferences = getSharedPreferences("myprefsfile", Context.MODE_PRIVATE)
                                    val editor = sharedPreferences.edit()
                                    editor.putBoolean("studentHasLoggedIn",true)
                                    editor.putString("email",
                                        response.body()?.data?.user?.username
                                    )
                                    editor.apply()
                                    Toast.makeText(
                                        this@LoginScreenActivity,
                                        "status:$status\n" +
                                                "message:$message\n" + "username:$username\n",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val intent = Intent(
                                        this@LoginScreenActivity,
                                        ChatStudentActivity::class.java
                                    )
                                    intent.putExtra("email", email)
                                    startActivity(intent)
                                } else {
                                    Toast.makeText(
                                        this@LoginScreenActivity,
                                        response.message(),
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                            }

                            override fun onFailure(call: Call<ResponseStudent>, t: Throwable) {
                                Toast.makeText(applicationContext, "Error: $t", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        })
                    }
//                    else
//                        Toast.makeText(
//                            applicationContext,
//                            "invalid email OR password!",
//                            Toast.LENGTH_SHORT
//                        )
//                            .show()

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


//    private fun saveTokenToSharedPreferences(token: String?) {
//        val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putString("token", token)
//        editor.apply()
//    }

    private fun startSendMessageActivity() {
        val intent = Intent(this, SendMessageActivity::class.java)
        startActivity(intent)
        finish()
    }
}