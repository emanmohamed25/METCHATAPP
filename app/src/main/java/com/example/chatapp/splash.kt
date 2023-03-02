package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.chatapp.databinding.ActivitySplashBinding

class splash : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding

    private val SPLASH_TIME_OUT: Long = 2500 // 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your main activity
            startActivity(Intent(this,HomeChatScreen::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
