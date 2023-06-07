package com.example.chatapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.ActivityHomeChatScreenBinding
import com.example.chatapp.databinding.ActivityHomepageBinding
import com.example.chatapp.doctor.newchat.admin.util.Constants.Companion.BASE_URL
import com.example.chatapp.student.ChatAdapter
import com.example.chatapp.student.student_chat_receive.MessageX
import com.google.gson.Gson
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import kotlinx.android.synthetic.main.activity_home_chat_screen.*
import kotlinx.android.synthetic.main.activity_profile.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log


class HomeChatScreen : AppCompatActivity() {
    private var datalist: MutableList<Chat> = mutableListOf()
    private lateinit var searchView: SearchView
    var searchlist: MutableList<Chat> = mutableListOf()
    private lateinit var x: RelativeLayout
    private val pusherAppKey = "PUSHER_APP_KEY"
    private val pusherAppCluster = "PUSHER_APP_CLUSTER"
    lateinit var list: ArrayList<Chat>
    lateinit var myadapter: ChatAdapter
    private lateinit var chatsapi: ChatsAPI

    var myshared: SharedPreferences? = null
    lateinit var binding: ActivityHomeChatScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeChatScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.staricon.setOnClickListener(){
            val intent = Intent(this, Starred_message_activity::class.java)
            startActivity(intent)

        }
        binding.profilephoto.setOnClickListener() {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }


        settings_text.setOnClickListener() {
            val intent = Intent(this, SettingScreen::class.java)
            startActivity(intent)


        }
        setupusher()
        list = ArrayList()

        myadapter = ChatAdapter(applicationContext, mutableListOf())
        initRecyclerView()

        ///////////////////////////////////////
        myshared = getSharedPreferences("myshared", 0)
        var mytoken = myshared?.getString("studenttoken", "").toString()

        Log.d("aaaaaaaaaaaaaaaaaaaaaa", mytoken)

        getChat(mytoken.toString())

        //      x=findViewById(R.id.actionbar1)
        //    this.setSupportActionBar(x)
//        Log.d("qqqqqqqqq", "out:"+datalist.toString())

        //searchView = findViewById(R.id.searchvieww)
        binding.imageClear.setOnClickListener {
            binding.searchvieww.text.clear()
            binding.searchvieww.clearFocus()
            myadapter.differ.submitList(searchlist)
            myadapter.notifyDataSetChanged()
            binding.imageClear.isVisible=false
        }

    }

    private fun initRecyclerView() {
        binding.recyclerview.apply {
            adapter=myadapter
           layoutManager = LinearLayoutManager(this@HomeChatScreen)
        }
    }

    fun getChat(mytoken: String) {
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

        chatsapi = retrofit.create(ChatsAPI::class.java)
        var call = chatsapi.getChats(mytoken)
        call.enqueue(
            object : Callback<StudentChatsResponse> {
                override fun onResponse(
                    call: Call<StudentChatsResponse>,
                    response: Response<StudentChatsResponse>,
                ) {
                    val x = response.code()
                    Log.d("cccccccccccccc", x.toString())

                    if (response.isSuccessful) {
                        Toast.makeText(this@HomeChatScreen, "sucessfetch", Toast.LENGTH_LONG)
                            .show()
                        var data = response.body()!!
                        datalist = data.chats as ArrayList<Chat>
                        searchlist.addAll(data.chats)
                        myadapter.differ.submitList(datalist)
                        myadapter.notifyDataSetChanged()
                        search()
                        Log.d("qqqqqqqqq", datalist.toString())


                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error fetching chats",
                            Toast.LENGTH_SHORT,
                        )
                            .show()
                    }

                }

                override fun onFailure(call: Call<StudentChatsResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, "Errorقققق is:$t", Toast.LENGTH_SHORT)
                        .show()

                }


            },
        )
    }

    fun fillList(list: List<Chat>) {

    }

    fun search() {



        binding.searchvieww.clearFocus()


        binding.searchvieww.addTextChangedListener(object :
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

                var array = ArrayList<Chat>()
                if (datalist.isNotEmpty()) {
                    for (a in datalist) {
                        if (a.last_message.user_name.lowercase().contains(s.toString().lowercase()))

                         {
                            array.add(a)
                        }
                    }
                    myadapter.differ.submitList(array)
                    myadapter.notifyDataSetChanged()
                    if (s.isEmpty() || s.isBlank()) {
                        myadapter.differ.submitList(datalist)
                        myadapter.notifyDataSetChanged()
                    }
                }

            }
        })


//        searchView.clearFocus()




//        (object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                searchView.clearFocus()
//                Log.d("qqqqqqqqq", "onQueryTextSubmit :" + datalist.toString())
//
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                searchlist.clear()
//                val searchtext = newText!!.toLowerCase(Locale.getDefault())
//                if (searchtext.isEmpty()) {
//                    datalist.forEach {
//                        if (it.name.lowercase(Locale.getDefault()).contains(searchtext)) {
//
//                            searchlist.add(it)
//                        }
//                    }
//                    recyclerview.adapter!!.notifyDataSetChanged()
//
//
//                } else {
//                    searchlist.clear()
//                    searchlist.addAll(datalist)
//                    recyclerview.adapter!!.notifyDataSetChanged()
//
//                }
//                return false
//                TODO("Not yet implemented")
//            }
//        })

    }

    private fun setupusher() {
        val options = PusherOptions()
        options.setCluster("eu")

        val pusher = Pusher("ccdf3591e58ad169e24d", options)
        Log.e("s", "success")
        val channel = pusher.subscribe("new-message")
        Log.e("ggggggggggggggggggggg", "hhhhhhhhhh")
        channel.bind("server.created") { event ->
            Log.e("eeee", "hhhhhhhhhh")
            // val jsonObject = JSONObject(event.data)
            // val message = jsonObject.getJSONObject("message")
            // Log.e("eeee","{$message}")


            runOnUiThread {
                // val id ="std"+message.getString("channel_id")
                // if (message.getString("channel_id") == email)
                //binding.tvViewMessage.text = message.getString("content")
//                    +"\n"+
//                    message.getString("channel").get(6).toString()
//                    + message.getString("channel.id")
            }
        }

        pusher.connect()


    }

}






