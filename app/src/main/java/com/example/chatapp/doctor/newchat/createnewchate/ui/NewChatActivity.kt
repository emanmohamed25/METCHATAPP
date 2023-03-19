package com.example.chatapp.doctor.newchat.createnewchate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.chatapp.R
import androidx.fragment.app.Fragment

import com.example.chatapp.databinding.ActivityNewChatBinding

class NewChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewChatBinding.inflate(layoutInflater)
       // binding= ActivityNewChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController=Navigation.findNavController(this,R.id.fragmentContainerView)

        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController)
    }
}