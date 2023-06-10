package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.chatapp.doctor.newchat.admin.util.Constants.Companion.BASE_URL
import com.example.chatapp.doctor.newchat.network.ApiService
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

                apiService = retrofit.create(ApiService::class.java)
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
                }


                else if (KindUser.equals("student")) {
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
                                    var Name=response.body()?.data?.user?.name.toString()
                                    var YearLevel=response.body()?.data?.user?.section?.year_level?.name.toString()
                                    val Depatment=response.body()?.data?.user?.section?.year_level?.department?.name.toString()


                                    myshared = getSharedPreferences("myshared", 0)
                                    var editor: SharedPreferences.Editor = myshared!!.edit()
                                    editor.putString("studenttoken", studenttoken)
                                    myshared=getSharedPreferences("myshared",0)
                                    editor.putString("name",Name )
                                    editor.putString("yearlevel",YearLevel )
                                    editor.putString("department",Depatment)
                                    editor.commit()
                                    var intent1 = Intent(this@Login_Screen, HomeChatScreen::class.java)
                                    startActivity(intent1)
                                    finish()



                                }else{
                                    Toast.makeText(this@Login_Screen,
                                        "wrong id or password",
                                        Toast.LENGTH_SHORT)
                                        .show()
                                }
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
    }

//    override fun startActivity(intent: Intent?) {
//        super.startActivity(intent)
//    }

//    private fun saveTokenToSharedPreferences(token: String?) {
//        val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putString("token", token)
//        editor.apply()
//    }

//    private fun startHomeChatScreen() {
//        val intent = Intent(this, HomeChatScreen::class.java)
//        startActivity(intent)
//        finish()
//    }
}