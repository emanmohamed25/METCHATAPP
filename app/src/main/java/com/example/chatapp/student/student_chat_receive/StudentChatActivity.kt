package com.example.chatapp.student.student_chat_receive

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.*
import com.example.chatapp.databinding.ActivityStudentChatBinding
import com.example.chatapp.doctor.newchat.admin.util.Constants.Companion.BASE_URL
import com.example.chatapp.student.pusher.PusherEventBus
import com.example.chatapp.student.pusher.PusherEventListener
import com.example.chatapp.student.pusher.ResponsePusher
import com.google.gson.Gson
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import com.pusher.client.channel.SubscriptionEventListener
import kotlinx.android.synthetic.main.activity_home_chat_screen.*
import kotlinx.android.synthetic.main.activity_student_chat.*
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class StudentChatActivity : AppCompatActivity(), PusherEventListener {
    lateinit var sendmessstar: poststarmessageapi
    lateinit var pusher: Pusher
    lateinit var list: MutableList<MessageX>
    lateinit var chatsrecyclerview: RecyclerView
    lateinit var messadapter: BubbleAdapter
    private lateinit var private_chat_api: PrivateChatApi
    lateinit var binding: ActivityStudentChatBinding
    lateinit var messageArrarRespons: ArrayList<MessageX>

    //private var BubbleContentarray: MutableList<bubble_chat_data> = mutableListOf()
    var myshared: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityStudentChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        messageArrarRespons = ArrayList()
        list = ArrayList()
        messadapter = BubbleAdapter(applicationContext, mutableListOf())
        chatsrecyclerview = findViewById(R.id.recyclerviewbubble)
        chatsrecyclerview.layoutManager = LinearLayoutManager(this)
        chatsrecyclerview.adapter = messadapter
        searchicon.setOnClickListener() {
            resv.setVisibility(View.VISIBLE)
            redefault.setVisibility(View.GONE)
            close.setOnClickListener() {
                resv.setVisibility(View.GONE)
                redefault.setVisibility(View.VISIBLE)
                messadapter.differ.submitList(messageArrarRespons)
                messadapter.notifyDataSetChanged()

            }
        }

        var chat_id = intent.getIntExtra("chatid", 0)


        myshared = getSharedPreferences("myshared", 0)
        var mytoken = myshared?.getString("studenttoken", "").toString()
        Log.d("jjjjjjjjjjj", mytoken)
        Log.d("zzzzzzzz", chat_id.toString())
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
        private_chat_api = retrofit.create(PrivateChatApi::class.java)
        val call = private_chat_api.getmessages(mytoken, chat_id)
        call.enqueue(
            object : Callback<PrivateStudentChatRes> {
                override fun onResponse(
                    call: Call<PrivateStudentChatRes>,
                    response: Response<PrivateStudentChatRes>,
                ) {
                    val x = response.code()
                    Log.d("cccccccccccccc", x.toString())

                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "connected", Toast.LENGTH_SHORT)
                            .show()
                        val f = response.code().toString()
                        Log.d("wwwwwwwwwwww", f)
                        val x = response.body()!!
                        messageArrarRespons.addAll(x.messages)
                        messadapter.differ.submitList(messageArrarRespons)
                        messadapter.notifyDataSetChanged()
                        // chatsrecyclerview.adapter = messadapter

                        /* var messdata=response.body()!!
                         messadapter= BubbleAdapter(baseContext,messdata)
                         chatsrecyclerview.adapter=messadapter*/


                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error fetching private chats",
                            Toast.LENGTH_SHORT,
                        )
                            .show()
                    }

                }

                override fun onFailure(call: Call<PrivateStudentChatRes>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Error cause failur is:$t",
                        Toast.LENGTH_SHORT
                    )
                        .show()

                }


            },
        )
        doc.setOnClickListener() {
            val intent = Intent(this, AdminProfile::class.java)
            startActivity(intent)

        }

        val backbtn = findViewById<ImageView>(R.id.back_button)
        backbtn.setOnClickListener {
            finish()
        }
        val options = PusherOptions().apply {
            isUseTLS = true
            setCluster("eu")
        }

        pusher = Pusher("ccdf3591e58ad169e24d", options)

        // Subscribe to the desired channel
        val channel = pusher.subscribe("new-message")

        // Set up a listener for events on the channel
        val eventListener = SubscriptionEventListener { event ->

            PusherEventBus.postPusherEvent(event.eventName, event.data)
        }

        // Bind the listener to the channel's events
        channel.bind("server.created", eventListener)

        // Register the listener with the Pusher event bus
        PusherEventBus.registerListener(this)

        // Connect to Pusher
        pusher.connect()
//        setPusher()
        /////////////////////////////////////
        myshared = getSharedPreferences("idpref", 0)
        var messid = myshared?.getInt("messid", 0).toString()
        Log.d("44444444444", messid.toString())


        binding.staremp.setOnClickListener() {
            if (messid != "") {
                myshared = getSharedPreferences("idpref", 0)
                var messid = myshared?.getInt("messid", 0)
                Log.d("44444444444", messid.toString())
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

                sendmessstar = retrofit.create(poststarmessageapi::class.java)
                val call = sendmessstar.send(mytoken, messid)
                call.enqueue(
                    object : Callback<poststarmessage> {
                        override fun onResponse(
                            call: Call<poststarmessage>,
                            response: Response<poststarmessage>,
                        ) {


                            if (response.isSuccessful) {
                                var messs = response.body()?.message
                                Toast.makeText(
                                    applicationContext,
                                    "$messs$messid",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                val f = response.code().toString()
                                Log.d("wwwwwwwwwwww", f)
                                val x = response.body()!!

                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "Please select message",
                                    Toast.LENGTH_SHORT,
                                )
                                    .show()
                            }

                        }

                        override fun onFailure(call: Call<poststarmessage>, t: Throwable) {
                            Toast.makeText(
                                applicationContext,
                                "Error cause failur is:$t",
                                Toast.LENGTH_SHORT
                            )
                                .show()

                        }


                    },
                )
                val sharedPreferences = getSharedPreferences("idpref", 0)
                val editor = sharedPreferences.edit()
                editor.remove("messid")
                editor.apply()
                Log.d("55555555", mytoken.toString())
                //Log.d("vvvvvvvvvvv",messid.toString())
                starfil.visibility = View.VISIBLE
                starfil.postDelayed({
                    starfil.visibility = View.GONE
                }, 3000)


            } else {

                Toast.makeText(
                    applicationContext,
                    "selectmessage pleas",
                    Toast.LENGTH_SHORT
                )
                    .show()

            }


        }

        search()
    }


    fun search() {

        binding.searchviewst.clearFocus()


        binding.searchviewst.addTextChangedListener(object :
            TextWatcher {

            override fun afterTextChanged(s: Editable) {
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                // filter on customer list
                Log.d("TAG5", "My letter is ${s}")
                Log.d("SEARCH", "Length is ${s.length}")

                var array = ArrayList<MessageX>()
                if (messageArrarRespons.isNotEmpty()) {
                    for (a in messageArrarRespons) {
                        if ((a.content != null) && (a.content!!.lowercase()
                                .contains(s.toString().lowercase()))
                        ) {
                            array.add(a)
                        }
                    }
                    messadapter.differ.submitList(array)
                    messadapter.notifyDataSetChanged()
                    if (s.isEmpty() || s.isBlank()) {
                        messadapter.differ.submitList(messageArrarRespons)
                        messadapter.notifyDataSetChanged()
                    }
                }

            }
        })


    }


    override fun onPusherEventReceived(eventName: String, eventData: String) {
        Log.d("tag", "my event name is ${eventName}")
        Log.d("tag", "my event data is ${eventData}")
        lifecycleScope.launch {
            if (eventData != "" || eventData != null) {
                val gson = Gson()

                var messageresponse: ResponsePusher =
                    gson.fromJson(eventData, ResponsePusher::class.java)
                Log.d("tag", "my event messageresponse is ${messageresponse}")
                messageArrarRespons.add(
                    MessageX(
                        content = messageresponse.message.content,
                        created_at = messageresponse.message.createdAt,
                        id = messageresponse.message.id,
                        file = "",
                        user_id = messageresponse.message.userId,
                        user_name = ""
                    )
                )

                if (messageArrarRespons.size == 0) {
                    messadapter.notifyDataSetChanged()
                } else {
                    messadapter.notifyItemRangeInserted(
                        messageArrarRespons.size,
                        messageArrarRespons.size
                    )
                    chatsrecyclerview.smoothScrollToPosition(messageArrarRespons.size - 1)
                }
                messadapter.differ.submitList(messageArrarRespons)
                // messadapter = BubbleAdapter(baseContext, )

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        // Disconnect from Pusher and unregister the listener
        pusher.disconnect()
        PusherEventBus.unregisterListener(this)
    }


}





