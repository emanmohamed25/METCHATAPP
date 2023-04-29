package com.example.chatapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.databinding.ActivityHomeChatScreenBinding
import com.example.chatapp.databinding.ActivityHomepageBinding
import com.example.chatapp.doctor.newchat.admin.util.Constants.Companion.BASE_URL
import com.example.chatapp.student.ChatAdapter
import kotlinx.android.synthetic.main.activity_home_chat_screen.*
import kotlinx.android.synthetic.main.activity_profile.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeChatScreen : AppCompatActivity() {
    lateinit var chatsrecyclerview: RecyclerView
    lateinit var list: ArrayList<Chat>
    lateinit var myadapter: ChatAdapter
    private lateinit var chatsapi: ChatsAPI

    var myshared: SharedPreferences? = null
    lateinit var binding: ActivityHomeChatScreenBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeChatScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.profilephoto.setOnClickListener(){
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }


        settings_text.setOnClickListener() {
            val intent = Intent(this, SettingScreen::class.java)
            startActivity(intent)

            /*
            myshared = getSharedPreferences("myshared", 0)
            var editor: SharedPreferences.Editor = myshared!!.edit()
            editor.remove("studenttoken")
            editor.apply();
            val intent = Intent(this@HomeChatScreen, splash::class.java)
            startActivity(intent)
            finish()*/

        }
        list=ArrayList()

        myadapter = ChatAdapter(applicationContext, mutableListOf());
        chatsrecyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        chatsrecyclerview.layoutManager = LinearLayoutManager(this)
        chatsrecyclerview.adapter = myadapter
        ///////////////////////////////////////
        myshared = getSharedPreferences("myshared", 0)
        var mytoken = myshared?.getString("studenttoken", "").toString()

        Log.d("aaaaaaaaaaaaaaaaaaaaaa",mytoken)


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
        call.enqueue(object : Callback<StudentChatsResponse> {
                override fun onResponse(
                    call: Call<StudentChatsResponse>,
                    response: Response<StudentChatsResponse>
                ) {
                    val x = response.code()
                    Log.d("cccccccccccccc", x.toString())

                    if (response.isSuccessful) {
                        Toast.makeText(this@HomeChatScreen, "sucessfetch", Toast.LENGTH_LONG)
                            .show()
                        var data = response.body()!!
                       //yu9tg var x=response.body()!!.chats[0].id
                        // Log.d("xxxxxxxxxxxxxxxx",data.toString())
                        myadapter = ChatAdapter(baseContext, data.chats)
                        chatsrecyclerview.adapter = myadapter



                        //var data = response.body()!!
                        // chatsrecyclerview.adapter = ChatAdapter(
                        //    baseContext,
                        //   data as ArrayList<StudentChatsRseponse>
                        // )
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
/////////////////////////////////////////////////////////////////////////////


        // chatItems.add(ChatData("","", R.drawable.home_page_log,"21""33"))
        /*arrayusers.add(StudentChatsRseponse())
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
*/


        // var chatsrecyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        // inidata()
        //chatsrecyclerview.layoutManager = LinearLayoutManager(this)

        //chatsrecyclerview.adapter = ChatAdapter(this, arrayusers) {
        //val toast = Toast.makeText(applicationContext, it.name, Toast.LENGTH_SHORT)
        //toast.show()
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


