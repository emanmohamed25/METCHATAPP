package com.example.chatapp.doctor.newchat.sendmessage

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityChatStudentBinding
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import org.json.JSONObject

class ChatStudentActivity : AppCompatActivity() {
    lateinit var binding: ActivityChatStudentBinding
    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityChatStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val  email= intent.getStringExtra("email")
        binding.tvStudentID.text= "Student Name : $email"

        try {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            val options = PusherOptions()
            options.setCluster("eu")

            val pusher = Pusher("ccdf3591e58ad169e24d", options)
            pusher.connect()
            Log.e("s", "success")
            val channel = pusher.subscribe("new-message")
            Log.e("ggggggggggggggggggggg","hhhhhhhhhh")
            channel.bind("server.created") { event ->
                Log.e("eeee","hhhhhhhhhh")
                val jsonObject = JSONObject(event.data)
                val message = jsonObject.getJSONObject("message")
                Log.e("eeee","{$message}")


                runOnUiThread {
                   // val id ="std"+message.getString("channel_id")
                   // if (message.getString("channel_id") == email)
                    binding.tvViewMessage.text = message.getString("content")
//                    +"\n"+
//                    message.getString("channel").get(6).toString()
//                    + message.getString("channel.id")
                }
            }
        } catch (e: Exception) {
            Log.e("e", e.toString())
        }

    }
}