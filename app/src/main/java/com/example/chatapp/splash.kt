package com.example.chatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splash : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 2500 // 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your main activity
            startActivity(Intent(this,homepage::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
