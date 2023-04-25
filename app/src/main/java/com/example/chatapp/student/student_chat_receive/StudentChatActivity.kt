package com.example.chatapp.student.student_chat_receive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.student.bubble_chat_data

class StudentChatActivity : AppCompatActivity() {
    private var BubbleContentarray: MutableList<bubble_chat_data> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_chat)
        //val senderphoto = findViewById<ImageView>(R.id.profilepic)
        val backbtn = findViewById<ImageView>(R.id.back_button)
        backbtn.setOnClickListener {
            finish()
        }
       // var pic = intent.extras?.get("senderphoto")
        //senderphoto.setImageResource(pic as Int)
        BubbleContentarray.add(bubble_chat_data("hi,how are you","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        BubbleContentarray.add(bubble_chat_data("aaaaaaaaaaaaaaaaa","10:am"))
        var bubblesrecyclerview = findViewById<RecyclerView>(R.id.recyclerviewbubble)
        bubblesrecyclerview.layoutManager = LinearLayoutManager(this)
        bubblesrecyclerview.adapter= BubbleAdapter(this,BubbleContentarray){


        }



    }
}