package com.example.chatapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.TextKeyListener.clear
import android.widget.Toast
import com.example.chatapp.databinding.ActivitySplashBinding


class  splash : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding

    private val SPLASH_TIME_OUT: Long = 2500 // 3 sec
    var myshared : SharedPreferences?=null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
            myshared=getSharedPreferences("myshared",0)
            var token = myshared?.getString("token","")
            if (token!=""){
                Toast.makeText(this@splash,
                    token.toString(),
                    Toast.LENGTH_SHORT)
                    .show()
                supportActionBar?.hide()
                Handler().postDelayed({
                    // This method will be executed once the timer is over
                    // Start your main activity
                    startActivity(Intent(this,HomeChatScreen::class.java))

                    // close this activity
                    finish()
                }, SPLASH_TIME_OUT)

            }else{

                supportActionBar?.hide()
                Handler().postDelayed({
                    // This method will be executed once the timer is over
                    // Start your main activity
                    startActivity(Intent(this,HomepageActivity::class.java))

                    // close this activity
                    finish()
                }, SPLASH_TIME_OUT)

            }
    }
}
