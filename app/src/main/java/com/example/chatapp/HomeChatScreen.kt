package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeChatScreen : AppCompatActivity() {
    private lateinit var chatsapi: ChatsAPI
    private var arrayusers: MutableList<ChatData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_chat_screen)
        /*
        val mytoken: String = intent.extras?.getString("tok").toString()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        chatsapi = retrofit.create(ChatsAPI::class.java)

        var call = chatsapi.getChats(mytoken)
        call.enqueue(object : Callback<List<ChatsComponent>> {
            override fun onResponse(
                call: Call<List<ChatsComponent>>,
                response: Response<List<ChatsComponent>>,
            ) {
                if (response.isSuccessful) {


                } else {

                    Toast.makeText(applicationContext, "Error fetching chats", Toast.LENGTH_SHORT)
                        .show()
                }
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<List<ChatsComponent>>, t: Throwable) {
                Toast.makeText(applicationContext, "Errorقققق is:$t", Toast.LENGTH_SHORT)
                    .show()
                TODO("Not yet implemented")
            }


        })*/
/////////////////////////////////////////////////////////////////////////////


        //  chatItems.add(ChatData("","", R.drawable.home_page_log,"21""33"))
        arrayusers.add(ChatData("Ahmed","how are you",R.drawable.userphoto2,"12:am","22"))
        arrayusers.add(ChatData("ali","how are you",R.drawable.userprofilephoto,"12:am","22"))
        arrayusers.add(ChatData("ss","how are you",R.drawable.userprofilephoto,"14:am","22"))
        arrayusers.add(ChatData("abdelrahamn","how are you",R.drawable.userprofilephoto,"12:am","22"))
        arrayusers.add(ChatData("sayed","how are you",R.drawable.userprofilephoto,"14:am","22"))
        arrayusers.add(ChatData("leen","how are you",R.drawable.userprofilephoto,"15am","22"))
        arrayusers.add(ChatData("mona","how are you",R.drawable.userprofilephoto,"4:am","22"))
        arrayusers.add(ChatData("ebrahim","how are you",R.drawable.userprofilephoto,"16:am","22"))
        arrayusers.add(ChatData("Ahmed","how are you",R.drawable.userprofilephoto,"12:am","22"))
        arrayusers.add(ChatData("mona","how are you",R.drawable.adminhomepagephoto,"4:am","22"))
        arrayusers.add(ChatData("ebrahim","how are you",R.drawable.userprofilephoto,"16:am","22"))
        arrayusers.add(ChatData("Ahmed","how are you",R.drawable.userprofilephoto,"12:am","22"))





        var chatsrecyclerview = findViewById<RecyclerView>(R.id.recyclerview)
       // inidata()
        chatsrecyclerview.layoutManager = LinearLayoutManager(this)

        chatsrecyclerview.adapter = ChatAdapter(this, arrayusers) {
            val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    /*private fun inidata() {
        val image = resources.obtainTypedArray(R.array.image)
        val name = resources.getStringArray(R.array.name)
        val status = resources.getStringArray(R.array.status)
        val time = resources.getStringArray(R.array.time)
        val unread = resources.getStringArray(R.array.unreadmess)
        arrayusers.clear()
        for (i in name.indices) {
            arrayusers.add(ChatData(name[i],
                status[i],
                image.getResourceId(i, 0),
                time[i],
                unread[i]))

        }
    }*/


}