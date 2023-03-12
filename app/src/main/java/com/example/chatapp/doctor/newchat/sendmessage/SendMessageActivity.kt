package com.example.chatapp.doctor.newchat.sendmessage

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.ApiService
import com.example.chatapp.databinding.ActivitySendMessageBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SendMessageActivity : AppCompatActivity() {
    lateinit var binding: ActivitySendMessageBinding
    lateinit var listStudent: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSend.setOnClickListener {
            listStudent = ArrayList()
            if (binding.etMessage.text.isNullOrEmpty()) {
                Toast.makeText(this, "please enter your message!", Toast.LENGTH_LONG).show()

            }
            if (binding.chkBName1.isChecked
                || binding.chkBName2.isChecked
                || binding.chkBName3.isChecked
                || binding.chkBName4.isChecked
                || binding.chkBName5.isChecked

            ) {
                if (binding.chkBName1.isChecked) {
                    listStudent.add(1)
                    if (binding.chkBName2.isChecked) {
                        listStudent.add(2)
                        if (binding.chkBName3.isChecked) {
                            listStudent.add(3)
                            if (binding.chkBName4.isChecked) {
                                listStudent.add(4)
                                if (binding.chkBName5.isChecked) {
                                    listStudent.add(5)
                                }
                            }
                        }
                    }
                } else if (binding.chkBName2.isChecked) {
                    listStudent.add(2)
                    if (binding.chkBName3.isChecked) {
                        listStudent.add(3)
                        if (binding.chkBName4.isChecked) {
                            listStudent.add(4)
                            if (binding.chkBName5.isChecked) {
                                listStudent.add(5)
                            }
                        }
                    }
                } else if (binding.chkBName3.isChecked) {
                    listStudent.add(3)
                    if (binding.chkBName4.isChecked) {
                        listStudent.add(4)
                        if (binding.chkBName5.isChecked) {
                            listStudent.add(5)
                        }
                    }
                }
            } else {
                Toast.makeText(this, "please select students!", Toast.LENGTH_LONG).show()
            }
            Toast.makeText(this, "" + listStudent, Toast.LENGTH_LONG).show()


            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.51:80/chatapp/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val api = retrofit.create(ApiService::class.java)
            val dataClass = DataClass(binding.etMessage.text.toString(), listStudent)
            val call = api.storePost(dataClass)
            call.enqueue(object : Callback<DataClass> {
                @SuppressLint("RestrictedApi")
                override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
                    // handle the response
                    val status = response.body()?.status.toString()

                    val message = response.body()?.message.toString()
                    Toast.makeText(
                        this@SendMessageActivity,
                        "statu : $status \n message : $message \n" + response.code(),
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onFailure(call: Call<DataClass>, t: Throwable) {
                    Toast.makeText(this@SendMessageActivity, "Error: $t", Toast.LENGTH_SHORT).show()


                }
            })
        }

    }
}