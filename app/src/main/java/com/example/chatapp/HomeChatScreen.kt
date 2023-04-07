package com.example.chatapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.student.ChatAdapter
import kotlinx.android.synthetic.main.activity_home_chat_screen.*
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
    lateinit var adapter: ChatAdapter
    private lateinit var chatsapi: ChatsAPI
    private lateinit var arrayusers: List<TestClass>
    var myshared: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_chat_screen)
        //logout part
        settings_text.setOnClickListener() {
            myshared = getSharedPreferences("myshared", 0)
            var editor: SharedPreferences.Editor = myshared!!.edit()
            editor.remove("studenttoken")
            editor.apply();
            val intent = Intent(this@HomeChatScreen, splash::class.java)
            startActivity(intent)
            finish()

        }

        adapter = ChatAdapter(applicationContext, mutableListOf());
        chatsrecyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        chatsrecyclerview.layoutManager = LinearLayoutManager(this)
        chatsrecyclerview.adapter = adapter
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
            .baseUrl("http://10.0.2.2:8000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        chatsapi = retrofit.create(ChatsAPI::class.java)
        var call = chatsapi.getChats(mytoken)
        call.enqueue(object : Callback<List<TestClass>> {
            override fun onResponse(
                call: Call<List<TestClass>>,
                response: Response<List<TestClass>>
            ) {
                val x = response.code()
                Log.d("cccccccccccccc",x.toString())

                if (response.isSuccessful) {
                    Toast.makeText(this@HomeChatScreen, "sucessfetch", Toast.LENGTH_LONG)
                        .show()
                    var data = response.body()!!
                   // Log.d("xxxxxxxxxxxxxxxx",data.toString())
                    adapter = ChatAdapter(baseContext, data)
                    chatsrecyclerview.adapter = adapter


                    //var data = response.body()!!
                    // chatsrecyclerview.adapter = ChatAdapter(
                    //    baseContext,
                    //   data as ArrayList<StudentChatsRseponse>
                    // )
                } else {
                    Toast.makeText(applicationContext, "Error fetching chats", Toast.LENGTH_SHORT)
                        .show()
                }

            }

            override fun onFailure(call: Call<List<TestClass>>, t: Throwable) {
                Toast.makeText(applicationContext, "Errorقققق is:$t", Toast.LENGTH_SHORT)
                    .show()

            }


        })
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


