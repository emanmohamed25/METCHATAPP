package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp.databinding.ActivityProfileBinding
import com.example.chatapp.databinding.ActivitySettingScreenBinding
import kotlinx.android.synthetic.main.activity_setting_screen.*

class SettingScreen : AppCompatActivity() {
    lateinit var binding: ActivitySettingScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)
        backbtnarrow.setOnClickListener() {
            val intent = Intent(this, HomeChatScreen::class.java)
            startActivity(intent)
            finish()


        }
    }
}