package com.example.chatapp.doctor.newchat.admin

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.chatapp.LoginScreenActivity
import com.example.chatapp.R

import com.example.chatapp.databinding.ActivityNewChatBinding
import com.example.chatapp.doctor.newchat.admin.util.Constants

class NewChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewChatBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}