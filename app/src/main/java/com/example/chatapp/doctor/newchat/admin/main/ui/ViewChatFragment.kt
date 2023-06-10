package com.example.chatapp.doctor.newchat.admin.main.ui

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.databinding.FragmentViewChatBinding
import com.example.chatapp.doctor.newchat.admin.createnewchate.adapter.ListMessagesAdapter
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Message
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responselistmessage.ListMessageResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsesendmessage.SendMessageResponse
import com.example.chatapp.doctor.newchat.admin.util.Constants
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewChatFragment : Fragment(), ListMessagesAdapter.OnItemClickListener {
    lateinit var binding: FragmentViewChatBinding
    var listContentMessage: MutableList<String> = mutableListOf()
    var listTimeMessage: MutableList<String> = mutableListOf()
    var listMessages: MutableList<Message> = mutableListOf()
    lateinit var adapter: ListMessagesAdapter
    private val arg: ViewChatFragmentArgs by navArgs()
    var myshared: SharedPreferences? = null

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewChatBinding.inflate(layoutInflater, container, false)
        myshared = requireActivity().getSharedPreferences(Constants.MY_SHARED, 0)
        var adminToken = myshared?.getString("admintoken", "")
        var channel_id = arg.channelId
        var channelName = arg.channelName
        getMessage(channel_id, adminToken.toString())
        binding.tvTitle.text = channelName.toString()

        binding.ivBack.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(ViewChatFragmentDirections.actionViewChatFragmentToHomeFragment())

        }

        binding.btnSend.setOnClickListener {
            var message = binding.etEnterMessage.text.toString()

            if (message.isNullOrEmpty()) {
                Toast.makeText(context, "please enter message !!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                RetrofitClientAdmin.api.sendMessageInChannel(
                    "Bearer $adminToken",
                    channel_id.toInt(),
                    message
                ).enqueue(object :Callback<SendMessageResponse>{
                    override fun onResponse(
                        call: Call<SendMessageResponse>,
                        response: Response<SendMessageResponse>
                    ) {
                        if (response.isSuccessful){

                            val _responseMessage=response.body()?.data?.message
                            val contentMessage=_responseMessage?.content.toString()
                            val timeMessaege=_responseMessage?.createdAt.toString()
                            addNewMessage(contentMessage,timeMessaege)
                            Toast.makeText(context, "${response.body()?.status}", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<SendMessageResponse>, t: Throwable) {
                        Toast.makeText(context, "onFailure: $t", Toast.LENGTH_SHORT)
                            .show()
                        Log.e("chat response", "onFailure : $t")
                    }

                })
            }
        }
        return binding.root
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addNewMessage(content:String, time:String){
        val message:Message=Message(content,time)
        listMessages.add(message)
        adapter.notifyItemInserted(listMessages.size - 1)

    }

    fun getMessage(id: Int, token: String) {
        RetrofitClientAdmin.api.getMessages("Bearer $token", id)
            .enqueue(object : Callback<ListMessageResponse> {
                override fun onResponse(
                    call: Call<ListMessageResponse>, response: Response<ListMessageResponse>
                ) {

                    if (response.isSuccessful) {
                        val listContent = response.body()?.messages?.map {
                            it.content
                        } ?: emptyList()

                        val listTime = response.body()?.messages?.map {
                            it.createdAt
                        } ?: emptyList()
                        fillRecyclerView(listContent, listTime)
                    }
                    Log.e("respons", "${response.body()}")
                }

                override fun onFailure(call: Call<ListMessageResponse>, t: Throwable) {
                    Toast.makeText(context, "onFailure: $t", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("chat response", "onFailure : $t")
                }

            })

    }


    fun fillRecyclerView(listContent: List<String>, listTime: List<String>) {
        listTimeMessage.clear()
        listContentMessage.clear()
        for (i in 0..listContent.size - 1) {
            listMessages.add(Message(listContent[i], listTime[i]))
        }

        if (listMessages.isNotEmpty()) {
            adapter = ListMessagesAdapter(listMessages, this)
            binding.rvLiatOfMessages.adapter = adapter
            binding.rvLiatOfMessages.layoutManager = LinearLayoutManager(requireActivity())
        }
    }

    override fun onItemClick(position: Int) {
    }

}