package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp.databinding.ActivityHomepageBinding

class HomepageActivity : AppCompatActivity() {
     lateinit var binding:ActivityHomepageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.linAdm.setOnClickListener(){
// FirstActivity.kt

            val intent = Intent(this, LoginScreenActivity::class.java)
            intent.putExtra("user", "admin")
            startActivity(intent)
            finish()
        }
        binding.linStd.setOnClickListener(){
            val intent = Intent(this, Login_Screen::class.java)
            intent.putExtra("user", "student")
            startActivity(intent)
            finish()
        }
}

}