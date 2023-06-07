package com.example.chatapp.doctor.newchat.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityCreateNewChatBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CreateNewChatActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateNewChatBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCreateNewChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener{
            startActivity(Intent(this,NewChatActivity::class.java))
        }
        val navView: BottomNavigationView = binding.bottomNavigationView


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerViewNewChat) as NavHostFragment
        navController = navHostFragment.findNavController()

        //view.findViewById(R.id.bottomNavigationView)
//        navController = findNavController(R.id.fragmentContainerViewNewChat)
        navView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {   menuItem ->
            when (menuItem.itemId) {
                R.id.departmentFragment -> {
                    Toast.makeText(this, "departmentFragment", Toast.LENGTH_LONG)
                        .show()
                    binding.fragmentContainerViewNewChat.findNavController().navigate(R.id.departmentFragment)
                    true
                }
                R.id.groupeFragment -> {
                    Toast.makeText(this, "groupeFragment", Toast.LENGTH_LONG)
                        .show()
                    binding.fragmentContainerViewNewChat.findNavController().navigate(R.id.groupeFragment)
                    true
                }R.id.allFragment -> {
                Toast.makeText(this, "allFragment", Toast.LENGTH_LONG)
                    .show()
                binding.fragmentContainerViewNewChat.findNavController().navigate(R.id.allFragment)
                true
            }R.id.stuffFragment -> {
                Toast.makeText(this, "stuffFragment", Toast.LENGTH_LONG)
                    .show()
                binding.fragmentContainerViewNewChat.findNavController().navigate(R.id.stuffFragment)
                true
            }
                else -> false
            }
        }
    }
}