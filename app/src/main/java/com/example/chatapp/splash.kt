package com.example.chatapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.chatapp.databinding.ActivitySplashBinding
import com.example.chatapp.doctor.newchat.sendmessage.ChatStudentActivity
import com.example.chatapp.doctor.newchat.sendmessage.SendMessageActivity

class splash : AppCompatActivity() {
    private lateinit var binding:ActivitySplashBinding

    private val SPLASH_TIME_OUT: Long = 2500 // 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
//        Handler().postDelayed({
//            val sharedprefernces: SharedPreferences = getSharedPreferences("myprefsfile", 0)
//            val studentHasLoggedIn: Boolean = sharedprefernces.getBoolean("studentHasLoggedIn", false)
//            val adminHasLoggedIn: Boolean = sharedprefernces.getBoolean("adminHasLoggedIn", false)
//            val studentToken : String? =sharedprefernces.getString("email","")
//            if (adminHasLoggedIn) {
//                val intent = Intent(this, SendMessageActivity::class.java)
//                   intent.putExtra("email",studentToken)
//                startActivity(intent)
//                finish()
//            } else if(studentHasLoggedIn) {
//                val intent = Intent(this, ChatStudentActivity::class.java)
//                startActivity(intent)
//                finish()
//            }else{
//                startActivity(Intent(this, HomepageActivity::class.java))
//                finish()
//            }
//                              }, SPLASH_TIME_OUT)
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your main activity
            startActivity(Intent(this,HomepageActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
        }
}
