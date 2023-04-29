package com.example.chatapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp.databinding.ActivityChatPermisionBinding
import com.example.chatapp.databinding.ActivityNotificationPermisionBinding
import kotlinx.android.synthetic.main.activity_setting_screen.*

class NotificationPermision : AppCompatActivity() {
    var myshared: SharedPreferences?=null
    lateinit var binding: ActivityNotificationPermisionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationPermisionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        logoutbutton.setOnClickListener() {
            myshared = getSharedPreferences("myshared", 0)
            var editor: SharedPreferences.Editor = myshared!!.edit()
            editor.remove("studenttoken")
            editor.apply();
            val intent = Intent(this@NotificationPermision, splash::class.java)
            startActivity(intent)
            finish()


        }
        backbtnarrow.setOnClickListener() {
            val intent = Intent(this, SettingScreen::class.java)
            startActivity(intent)

        }
        myshared=getSharedPreferences("myshared",0)
        var name=myshared?.getString("name","")
        binding.namestudent.text=name
    }
    }
