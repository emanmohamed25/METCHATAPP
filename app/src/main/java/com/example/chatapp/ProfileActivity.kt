package com.example.chatapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.chatapp.databinding.ActivityHomeChatScreenBinding
import com.example.chatapp.databinding.ActivityProfileBinding
import kotlinx.android.synthetic.main.activity_home_chat_screen.*
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    var myshared: SharedPreferences?=null
    /*
    val nametextView = findViewById<TextView>(R.id.name)
    val yeartextView = findViewById<TextView>(R.id.grade)
    val departmenttextView = findViewById<TextView>(R.id.department)
    */

    lateinit var binding: ActivityProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        backitem.setOnClickListener(){
            val intent = Intent(this, HomeChatScreen::class.java)
            startActivity(intent)

        }
        logoutbutton.setOnClickListener() {
            myshared = getSharedPreferences("myshared", 0)
            var editor: SharedPreferences.Editor = myshared!!.edit()
            editor.remove("studenttoken")
            editor.apply();
            val intent = Intent(this@ProfileActivity, splash::class.java)
            startActivity(intent)
            finish()

        }
        myshared=getSharedPreferences("myshared",0)
        var name=myshared?.getString("name","")
        var year=myshared?.getString("yearlevel","")
        var department=myshared?.getString("department","")
        binding.namestudent.text=name
        binding.grade.text=year
        binding.department.text=department



        /*
        nametextView.text=name.toString()
        yeartextView.text=year.toString()
        departmenttextView.text=department.toString()
*/
    }
}