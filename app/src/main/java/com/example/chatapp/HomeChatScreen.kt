package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeChatScreen : AppCompatActivity() {
    private lateinit var chatsapi: ChatsAPI
    private  var chatItems :MutableList<ChatData>  =mutableListOf()
    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_chat_screen)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://your-api-base-url.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
         chatsapi = retrofit.create(ChatsAPI::class.java)
        val mytoken:String= intent.extras?.getString("tok").toString()
//        chatsapi.getChats(mytoken).enqueue(object : Callback<ChatsComponent>)



      //  chatItems.add(ChatData("","", R.drawable.home_page_log,"21""33"))
        var list =findViewById<RecyclerView>(R.id.recyclerview)
        inidata()
        list.layoutManager=LinearLayoutManager(this , )
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
            chatItems.add(ChatData(name[i],status[i],image.getResourceId(i,0),time[i], unread[i]))

        }
    }


}