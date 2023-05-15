package com.example.chatapp.student.student_chat_receive

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.*
import com.example.chatapp.databinding.ActivityStudentChatBinding
import com.example.chatapp.doctor.newchat.admin.util.Constants.Companion.BASE_URL
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.ChannelEventListener
import com.pusher.client.channel.PusherEvent
import com.pusher.client.connection.ConnectionEventListener
import com.pusher.client.connection.ConnectionStateChange
import kotlinx.android.synthetic.main.activity_student_chat.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

//class StudentChatActivity : AppCompatActivity() {
////    lateinit var list: MutableList<MessageX>
//    lateinit var chatsrecyclerview: RecyclerView
//    lateinit var messadapter: BubbleAdapter
//    private lateinit var private_chat_api: PrivateChatApi
//    lateinit var binding: ActivityStudentChatBinding
//
//    //private var BubbleContentarray: MutableList<bubble_chat_data> = mutableListOf()
//    var myshared: SharedPreferences? = null


//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        binding = ActivityStudentChatBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        list = ArrayList()
//        messadapter = BubbleAdapter(applicationContext, mutableListOf())
//        chatsrecyclerview = findViewById(R.id.recyclerviewbubble)
//        chatsrecyclerview.layoutManager = LinearLayoutManager(this)
//        chatsrecyclerview.adapter = messadapter
//        searchicon.setOnClickListener() {
//            re1.setVisibility(View.VISIBLE)
//            re2.setVisibility(View.GONE)
//            close.setOnClickListener() {
//                re1.setVisibility(View.GONE)
//                re2.setVisibility(View.VISIBLE)
//
//
//            }
//        }
//
//        var chat_id = intent.getIntExtra("chatid", 0)
//
//
//        myshared = getSharedPreferences("myshared", 0)
//        var mytoken = myshared?.getString("studenttoken", "").toString()
//        Log.d("jjjjjjjjjjj", mytoken)
//        Log.d("zzzzzzzz", chat_id.toString())
//        val httpClient = OkHttpClient.Builder()
//
//        httpClient.addInterceptor(Interceptor { chain ->
//            val request: Request =
//                chain.request().newBuilder()
//                    .addHeader("Accept", "application/json")
//                    .addHeader("Authorization", "Bearer $mytoken")
//                    .build()
//            chain.proceed(request)
//        })
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(httpClient.build())
//            .build()
//        private_chat_api = retrofit.create(PrivateChatApi::class.java)
//        val call = private_chat_api.getmessages(mytoken, chat_id)
//        call.enqueue(
//            object : Callback<PrivateStudentChatRes> {
//                override fun onResponse(
//                    call: Call<PrivateStudentChatRes>,
//                    response: Response<PrivateStudentChatRes>,
//                ) {
//                    val x = response.code()
//                    Log.d("cccccccccccccc", x.toString())
//
//                    if (response.isSuccessful) {
//                        Toast.makeText(applicationContext, "connected", Toast.LENGTH_SHORT)
//                            .show()
//                        val f = response.code().toString()
//                        Log.d("wwwwwwwwwwww", f)
//                        val x = response.body()!!
//
//                        messadapter = BubbleAdapter(baseContext, x.messages)
//                        chatsrecyclerview.adapter = messadapter
//
//                        /* var messdata=response.body()!!
//                         messadapter= BubbleAdapter(baseContext,messdata)
//                         chatsrecyclerview.adapter=messadapter*/
//
//
//                    } else {
//                        Toast.makeText(
//                            applicationContext,
//                            "Error fetching private chats",
//                            Toast.LENGTH_SHORT,
//                        )
//                            .show()
//                    }
//
//                }
//
//                override fun onFailure(call: Call<PrivateStudentChatRes>, t: Throwable) {
//                    Toast.makeText(applicationContext,
//                        "Error cause failur is:$t",
//                        Toast.LENGTH_SHORT)
//                        .show()
//
//                }
//
//
//            },
//        )
//        doc.setOnClickListener() {
//            val intent = Intent(this, AdminProfile::class.java)
//            startActivity(intent)
//
//        }
//
//        val backbtn = findViewById<ImageView>(R.id.back_button)
//        backbtn.setOnClickListener {
//        }
//        setPusher()
//
//
//    }

    private fun setPusher() {
        val channelName = "new-message"
        val eventName = "server.created"

        val options = PusherOptions().apply {
            isUseTLS = true
            setCluster("eu")
        }

        val pusher = Pusher("ccdf3591e58ad169e24d", options)

        // connect to Pusher
        pusher.connect(object : ConnectionEventListener {
            override fun onConnectionStateChange(change: ConnectionStateChange?) {}

            override fun onError(message: String?, code: String?, e: java.lang.Exception?) {}

        })

        val channel = pusher.subscribe(channelName, object : ChannelEventListener {
            override fun onEvent(event: PusherEvent?) {}
            override fun onSubscriptionSucceeded(channelName: String?) {}
        }, eventName)

        channel.bind(eventName) { event ->
            val jsonObject = JSONObject(event.data)
            //kuInfoLog("Pusher", "event == : $jsonObject")
            val message = jsonObject.getString("message")
            /*
            val other = jsonObject.getString("other")
            val canDeclineOrder = jsonObject.getBoolean("can_decline_order")
            val messageId = jsonObject.getInt("message_id")
            val userId = jsonObject.getInt("user_id")
            val messageType = jsonObject.getInt("type")
            val createdAt = jsonObject.getString("created_at")
            val orderProgress = jsonObject.getString("order_progress")
            val closeChat = jsonObject.getBoolean("close_chat")
            val disableSendCost = jsonObject.getBoolean("disable_send_cost")
            val numberImage = jsonObject.getInt("number_image")
            var costModel : ResponseCost?= null*/


//            insertLocalMessage(MessageX(
//                content = message
//               ))

        }
    }

//    private fun insertLocalMessage(modelLocal: MessageX) {
//        messadapter.addDataItem(modelLocal)
//        // scroll the RecyclerView to the last added element
//
//
//
//    }


//}


