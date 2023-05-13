package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.method.TextKeyListener.clear
import android.widget.Toast
import com.example.chatapp.databinding.ActivitySplashBinding
import com.example.chatapp.doctor.newchat.admin.util.Constants.Companion.MY_SHARED
import com.example.chatapp.doctor.newchat.sendmessage.ChatStudentActivity
import com.example.chatapp.doctor.newchat.sendmessage.SendMessageActivity

class splash : AppCompatActivity() {
    var myshared: SharedPreferences? = null
//    override fun onStart() {
//        super.onStart()
//        super.onStart()
//        myshared=getSharedPreferences("myshared",0)
//        var token = myshared?.getString("token","")
//        if(token!=""){
//            val it =Intent(this@splash,SendMessageActivity::class.java)
//            startActivity(it)
//        }else if(token==""){
//            val it =Intent(this@splash,HomepageActivity::class.java)
//            startActivity(it)
//        }
//    }


    private lateinit var binding: ActivitySplashBinding

    private val SPLASH_TIME_OUT: Long = 3500// 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivitySplashBinding.inflate(layoutInflater)
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
                finish()
                // close this activity
            }, SPLASH_TIME_OUT)

        }
//        else if(!adtoken.isNullOrEmpty()){
//
//            Toast.makeText(this@splash,
//                adtoken.toString(),
//                Toast.LENGTH_SHORT)
//                .show()
//            supportActionBar?.hide()
//            Handler().postDelayed({
//                // This method will be executed once the timer is over
//                // Start your main activity
//                startActivity(Intent(this, NewChatActivity::class.java))
//
//                // close this activity
//                finish()
//
//            },SPLASH_TIME_OUT)
//        }
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

        ////////////////////////////////////////////////////////////////////////////////////////////
//        Handler().postDelayed({
//            val sharedprefernces: SharedPreferences = getSharedPreferences("myprefsfile", 0)
//            val studentHasLoggedIn: Boolean = sharedprefernces.getBoolean("studentHasLoggedIn", false)
//            val adminHasLoggedIn: Boolean = sharedprefernces.getBoolean("adminHasLoggedIn", false)
//            val studentToken : String? =sharedprefernces.getString("email","")
//            if (adminHasLoggedIn) {
//                val intent = Intent(this, NewChatActivity::class.java)
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
//        Handler().postDelayed({
//
///*
//            val sharedPreferences = getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
//            val accesstoken=sharedPreferences.getString("acesstoken",null)
//            if (accesstoken != null) {
//                // Access token is available, go to chat screen
//                val intent = Intent(this, ChatStudentActivity::class.java)
//                startActivity(intent)
//                finish()
//            } else {
//                // Access token is not available, go to login screen
//                val intent = Intent(this, HomepageActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//*/
//            // This method will be executed once the timer is over
//            // Start your main activity
//            startActivity(Intent(this,HomepageActivity::class.java))
////
//            // close this activity
//            finish()
//        }, SPLASH_TIME_OUT)

    }
}