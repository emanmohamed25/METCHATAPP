package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeChatScreen : AppCompatActivity() {
    private  var chatItems :MutableList<ChatItem>  =mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_chat_screen)
        var list =findViewById<RecyclerView>(R.id.recyclerview)
        inidata()
        list.layoutManager=LinearLayoutManager(this)
        list.adapter=ChatAdapter(this,chatItems){
            val toast=Toast.makeText(applicationContext,it.name,Toast.LENGTH_SHORT)
            toast.show()
        }


    }
    private fun inidata(){
        val image=resources.obtainTypedArray(R.array.image)
        val name =resources.getStringArray(R.array.name)
        val status =resources.getStringArray(R.array.status)
        val time =resources.getStringArray(R.array.time)
        val unread=resources.getStringArray(R.array.unreadmess)
        chatItems.clear()
        for (i in name.indices ){
            chatItems.add(ChatItem(name[i],status[i],image.getResourceId(i,0),time[i], unread[i]))

        }

    }
}