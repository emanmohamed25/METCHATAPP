package com.example.chatapp

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class AdminProfile : AppCompatActivity() {
    var myshared: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_profile)
        val myTextView = findViewById<TextView>(R.id.namedoc)
        myshared=getSharedPreferences("MyPrefs",0)
        var drname = myshared?.getString("drname","")
        Log.d("yyyyyy",drname.toString())
        myTextView.text=drname.toString()

    }
}