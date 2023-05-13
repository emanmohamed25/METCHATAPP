package com.example.chatapp.doctor.newchat.admin.main.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.chatapp.databinding.FragmentViewChatBinding
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responselistmessage.ListMessageResponse
import com.example.chatapp.doctor.newchat.admin.util.Constants
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewChatFragment : Fragment() {
    lateinit var binding: FragmentViewChatBinding
    private val arg: ViewChatFragmentArgs by navArgs()
    var myshared: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewChatBinding.inflate(layoutInflater, container, false)
        myshared = requireActivity().getSharedPreferences(Constants.MY_SHARED, 0)
        var adminToken = myshared?.getString("admintoken", "")
        var channel_id = arg.channelId
        var channelName = arg.channelName
        getMessage(channel_id, adminToken.toString())
        return binding.root
    }
    fun getMessage(id:Int,token:String){
        RetrofitClientAdmin.api.getMessages("Bearer $token",id).enqueue(
            object :Callback<ListMessageResponse> {
                override fun onResponse(
                    call: Call<ListMessageResponse>,
                    response: Response<ListMessageResponse>
                ) {
                    Log.e("respons","${response.body()}")
                }

                override fun onFailure(call: Call<ListMessageResponse>, t: Throwable) {
                }

            }
        )

    }

}