package com.example.chatapp.student.student_chat_receive

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.*
import com.example.chatapp.databinding.ActivityStudentChatBinding
import com.example.chatapp.doctor.newchat.admin.util.Constants.Companion.BASE_URL
import kotlinx.android.synthetic.main.activity_student_chat.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class StudentChatActivity : AppCompatActivity() {
    lateinit var  list: ArrayList<Message>
    lateinit var chatsrecyclerview: RecyclerView
    lateinit var messadapter: BubbleAdapter
    private lateinit var private_chat_api: PrivateChatApi
    lateinit var binding: ActivityStudentChatBinding
    //private var BubbleContentarray: MutableList<bubble_chat_data> = mutableListOf()
    var myshared: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityStudentChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list= ArrayList()
        messadapter= BubbleAdapter(applicationContext, mutableListOf())
        chatsrecyclerview=findViewById(R.id.recyclerviewbubble)
        chatsrecyclerview.layoutManager=LinearLayoutManager(this)
        chatsrecyclerview.adapter=messadapter


        var chat_id=intent.getIntExtra("chatid",0)
        myshared = getSharedPreferences("myshared", 0)
        var mytoken = myshared?.getString("studenttoken", "").toString()
        Log.d("jjjjjjjjjjj",mytoken)
        Log.d("zzzzzzzz",chat_id.toString())
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(Interceptor { chain ->
            val request: Request =
                chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer $mytoken")
                    .build()
            chain.proceed(request)
        })
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        private_chat_api=retrofit.create(PrivateChatApi::class.java)
        val call=private_chat_api.getmessages(mytoken,chat_id)
        call.enqueue(object : Callback<PrivateStudentChatResponse> {
            override fun onResponse(
                call: Call<PrivateStudentChatResponse>,
                response: Response<PrivateStudentChatResponse>
            ) {
                val x = response.code()
                Log.d("cccccccccccccc", x.toString())

                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "ناجح", Toast.LENGTH_SHORT)
                        .show()
                    var messdata=response.body()!!
                    messadapter= BubbleAdapter(baseContext,messdata.messages)
                    chatsrecyclerview.adapter=messadapter




                } else {
                    Toast.makeText(
                        applicationContext,
                        "Error fetching private chats",
                        Toast.LENGTH_SHORT,
                    )
                        .show()
                }

            }

            override fun onFailure(call: Call<PrivateStudentChatResponse>, t: Throwable) {
                Toast.makeText(applicationContext, "Error cause failur is:$t", Toast.LENGTH_SHORT)
                    .show()
                Log.e("onFailure","$t")

            }


        },
        )

        profilepic.setOnClickListener(){
        }
        val backbtn = findViewById<ImageView>(R.id.back_button)
        backbtn.setOnClickListener {
        }
       // var pic = intent.extras?.get("senderphoto")
        //senderphoto.setImageResource(pic as Int)
        /*
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
        }*/



    }
}