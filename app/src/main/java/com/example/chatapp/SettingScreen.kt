package com.example.chatapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp.databinding.ActivityProfileBinding
import com.example.chatapp.databinding.ActivitySettingScreenBinding
import kotlinx.android.synthetic.main.activity_setting_screen.*

class SettingScreen : AppCompatActivity() {
    var myshared: SharedPreferences?=null
    lateinit var binding: ActivitySettingScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)
        bar3.setOnClickListener(){
            val intent = Intent(this, ChatPermision::class.java)
            startActivity(intent)


        }
        bar4.setOnClickListener(){
            val intent = Intent(this, NotificationPermision::class.java)
            startActivity(intent)



        }
        backbtnarrow.setOnClickListener() {
            val intent = Intent(this, HomeChatScreen::class.java)
            startActivity(intent)


        }
        myshared=getSharedPreferences("myshared",0)
        var name=myshared?.getString("name","")
        binding.namestudent.text=name
        binding.logoutbutton.setOnClickListener(){
            myshared = getSharedPreferences("myshared", 0)
            var editor: SharedPreferences.Editor = myshared!!.edit()
            editor.remove("studenttoken")
            editor.apply();
            val intent = Intent(this@SettingScreen, splash::class.java)
            startActivity(intent)
            finish()

        }
    }
}