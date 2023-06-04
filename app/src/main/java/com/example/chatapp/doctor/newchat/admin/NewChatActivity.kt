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
//        val navController=Navigation.findNavController(this,R.id.fragmentContainerViewNewChat)
//
//        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController)
//binding.ivBack.setOnClickListener{
//   val myshared:SharedPreferences = getSharedPreferences(Constants.MY_SHARED, 0)
//    var editor: SharedPreferences.Editor = myshared!!.edit()
//    editor.putString("admintoken","")
//    editor.commit()
//startActivity(Intent(this@NewChatActivity,LoginScreenActivity::class.java))
//}
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.main_navigation) as NavHostFragment
//        val navController = navHostFragment.navController
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}