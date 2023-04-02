package com.example.chatapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.chatapp.databinding.ActivitySplashBinding


class  splash : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding

    private val SPLASH_TIME_OUT: Long = 2500 // 3 sec
   // var mysharedstudent : SharedPreferences?=null
    //var mysharedadmin:SharedPreferences?=null
    var myshared:SharedPreferences?=null

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
            myshared=getSharedPreferences("myshared",0)
            var stdtoken = myshared?.getString("studenttoken","")
           // myshared=getSharedPreferences("myshared",0)
            var adtoken=myshared?.getString("admintoken","")

            if (stdtoken!=""){
                Toast.makeText(this@splash,
                    stdtoken.toString(),
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

            }
            else if(adtoken!=""){

                Toast.makeText(this@splash,
                    adtoken.toString(),
                    Toast.LENGTH_SHORT)
                    .show()
                supportActionBar?.hide()
                Handler().postDelayed({
                    // This method will be executed once the timer is over
                    // Start your main activity
                    startActivity(Intent(this, doctortest::class.java))

                    // close this activity
                    finish()

                },SPLASH_TIME_OUT)
            }
            else{

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
