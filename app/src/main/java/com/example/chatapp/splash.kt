package com.example.chatapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.databinding.ActivitySplashBinding
import com.example.chatapp.doctor.newchat.admin.NewChatActivity

class splash : AppCompatActivity() {
    var myshared: SharedPreferences? = null
    private lateinit var binding: ActivitySplashBinding
    private val SPLASH_TIME_OUT: Long = 3500// 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myshared = getSharedPreferences("myshared", 0)
        var stdtoken = myshared?.getString("studenttoken", "")
        var adtoken = myshared?.getString("admintoken", "")
        if (stdtoken != "") {
            Toast.makeText(
                this@splash,
                stdtoken.toString(),
                Toast.LENGTH_SHORT
            )
                .show()
            supportActionBar?.hide()
            Handler().postDelayed({
                startActivity(Intent(this, HomeChatScreen::class.java))
                finish()
            }, SPLASH_TIME_OUT)

        } else if (adtoken != "") {
            Toast.makeText(
                this@splash,
                adtoken.toString(),
                Toast.LENGTH_SHORT
            )
                .show()
            supportActionBar?.hide()
            Handler().postDelayed({
                val intent = Intent(
                    this@splash,
                    NewChatActivity::class.java
                )
                startActivity(intent)
                finish()
            }, SPLASH_TIME_OUT)

        } else {
            supportActionBar?.hide()
            Handler().postDelayed({
                startActivity(Intent(this, HomepageActivity::class.java))
                finish()
            }, SPLASH_TIME_OUT)

        }
    }
}