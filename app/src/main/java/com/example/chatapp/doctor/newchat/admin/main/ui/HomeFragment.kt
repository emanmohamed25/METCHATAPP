package com.example.chatapp.doctor.newchat.admin.main.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentHomeBinding
import com.example.chatapp.doctor.newchat.admin.CreateNewChatActivity
import com.example.chatapp.doctor.newchat.admin.createnewchate.adapter.HomeAdapter
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Chat
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsehomechats.ListChatsResponse
import com.example.chatapp.doctor.newchat.admin.util.Constants
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), HomeAdapter.OnItemClickListener {


    lateinit var binding: FragmentHomeBinding
    var myshared: SharedPreferences? = null
    lateinit var adapter: HomeAdapter
    var listChats: MutableList<Chat> = mutableListOf()
    var listChannelIDs: MutableList<Int> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        myshared = requireActivity().getSharedPreferences(Constants.MY_SHARED, 0)
        var admintoken = myshared?.getString("admintoken", "")
        getChats(admintoken.toString())

        //button for create new chat
        binding.btnCreateNewChat.setOnClickListener { view: View ->

            startActivity(Intent(context, CreateNewChatActivity::class.java))
        }

        binding.profilephoto.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToAdminPrifileFragment())

        }

        return binding.root
    }

    fun getChats(admintoken: String) {
        RetrofitClientAdmin.api.getAdminChats("Bearer $admintoken")
            .enqueue(object : Callback<ListChatsResponse> {
                override fun onResponse(
                    call: Call<ListChatsResponse>,
                    response: Response<ListChatsResponse>
                ) {
                    if (response.isSuccessful) {
                        listChats.clear()
                        Log.e("response", response.body()!!.status)
                        val data = response.body()
                        val listNames = data?.chats?.map {
                            it.name
                        } ?: emptyList()
                        val listMessages = data?.chats?.map {
                            it.lastMessage.content
                        } ?: emptyList()
                        val listTime = data?.chats?.map {
                            it.lastMessage.createdAt
                        } ?: emptyList()
                        val listChannelId = data?.chats?.map {
                            it.id
                        } ?: emptyList()
                        listChannelIDs.addAll(listChannelId)
                        fillRecyclerView(listNames, listMessages, listTime)
                    }
                }

                override fun onFailure(call: Call<ListChatsResponse>, t: Throwable) {
                    Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("department response", "Error : $t")
                }
            })
    }

    fun fillRecyclerView(
        listName: List<String>, listMessage: List<String>, listTime: List<String>
    ) {
        listChats.clear()

        for (i in 0..listMessage.size - 1) {
            listChats.add(
                Chat(
                    listName[i], listMessage[i],
                    listTime[i].toString(), R.drawable.profile
                )
            )
        }
        if (listChats.isNotEmpty()) {
            adapter = HomeAdapter(listChats, this)
            binding.rvListOfChats.adapter = adapter
            binding.rvListOfChats.layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    override fun onItemClick(position: Int) {
        view?.findNavController()
            ?.navigate(
                HomeFragmentDirections.actionHomeFragmentToViewChatFragment(
                    listChannelIDs[position],
                    listChats[position].Name
                )
            )
    }

    fun search() {



      //  binding.searchvieww.clearFocus()


//        binding.searchvieww.addTextChangedListener(object :
//            TextWatcher {
//
//            override fun afterTextChanged(s: Editable) {
//            }
//
//            override fun beforeTextChanged(
//                s: CharSequence, start: Int,
//                count: Int, after: Int
//            ) {
//            }
//
//            override fun onTextChanged(
//                s: CharSequence, start: Int,
//                before: Int, count: Int
//            ) {
//                // filter on customer list
//                Log.d("TAG5", "My letter is ${s}")
//                Log.d("SEARCH", "Length is ${s.length}")
//
//                var array = ArrayList<com.example.chatapp.Chat>()
//                if (datalist.isNotEmpty()) {
//                    for (a in datalist) {
//                        if (a.last_message.user_name.lowercase().contains(s.toString().lowercase()))
//
//                        {
//                            array.add(a)
//                        }
//                    }
//                    myadapter.differ.submitList(array)
//                    myadapter.notifyDataSetChanged()
//                    if (s.isEmpty() || s.isBlank()) {
//                        myadapter.differ.submitList(datalist)
//                        myadapter.notifyDataSetChanged()
//                    }
//                }
//
//            }
//        })
    }

}