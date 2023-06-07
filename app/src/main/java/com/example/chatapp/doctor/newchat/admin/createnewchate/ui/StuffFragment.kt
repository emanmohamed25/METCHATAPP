package com.example.chatapp.doctor.newchat.admin.createnewchate.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentStuffBinding
import com.example.chatapp.doctor.newchat.admin.NewChatActivity
import com.example.chatapp.doctor.newchat.admin.createnewchate.adapter.StuffAdapter
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Stuff
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.StuffRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsestuff.GetStuffResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsestuff.createchatresponse.CreateGroupStuffResponse
import com.example.chatapp.doctor.newchat.admin.util.Constants
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin
import kotlinx.android.synthetic.main.fragment_department2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StuffFragment : Fragment(), StuffAdapter.OnItemClickListener {

    lateinit var binding: FragmentStuffBinding
    var stuffListNames: MutableList<Stuff> = mutableListOf()
    var stuffListIDs: MutableList<Int> = mutableListOf()
    var listSelectedStuffIDs: MutableList<String> = mutableListOf()
    var myshared: SharedPreferences? = null
    var allStuffIsCheck: Boolean = false
    lateinit var stuffRequest: StuffRequest

    lateinit var adapter: StuffAdapter
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val loading = LoadingDialog(requireActivity())
        loading.startLoading()
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                loading.isDismiss()
            }

        }, 2000)
        binding = FragmentStuffBinding.inflate(inflater, container, false)
        myshared = requireActivity().getSharedPreferences(Constants.MY_SHARED, 0)
        var adtoken = myshared?.getString("admintoken", "")
        getStuff()


        binding.btnSend.setOnClickListener {
            if (binding.etGroupName.text.toString().isNullOrEmpty()
                || binding.etEnterMessage.text.toString().isNullOrEmpty()
            ) {
                Toast.makeText(
                    context,
                    "please enter the group name and your message!",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                stuffRequest =
                    StuffRequest(
                        binding.etGroupName.text.toString(),
                        listSelectedStuffIDs,
                        binding.etEnterMessage.text.toString()
                    )
                RetrofitClientAdmin.api.createStuffGroup("Bearer $adtoken", stuffRequest)
                    .enqueue(object : Callback<CreateGroupStuffResponse> {
                        override fun onResponse(
                            call: Call<CreateGroupStuffResponse>,
                            response: Response<CreateGroupStuffResponse>
                        ) {
                            if (response.isSuccessful) {
                                val data = response.body()
                                Toast.makeText(
                                    context,
                                    ":onResponse :${data?.status}",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                Log.e("department response", "onResponse : ${data?.status}")

                                }
                        }

                        override fun onFailure(call: Call<CreateGroupStuffResponse>, t: Throwable) {
                            Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                                .show()
                            Log.e("department response", "Error : $t")
                        }

                    })
                startActivity(Intent(context,NewChatActivity::class.java))
                requireActivity().finish()
            }
        }
        binding.btnAllCheck.setOnClickListener {
            if (allStuffIsCheck) {
                allStuffIsCheck = false
                btnAllCheck.setBackgroundResource(R.drawable.uncheck_wite_checkbox)
                for (i in 0..stuffListNames.size - 1) {
                    stuffListNames[i].imgIsChecked = R.drawable.uncheck_black_checkbox
                    adapter.notifyItemChanged(i)
                }
                listSelectedStuffIDs.clear()
            } else {
                btnAllCheck.setBackgroundResource(R.drawable.check_wite_checkbox)
                allStuffIsCheck = true
                for (i in 0..stuffListNames.size - 1) {
                    stuffListNames[i].imgIsChecked = R.drawable.check_black_checkbox
                    adapter.notifyItemChanged(i)
                    listSelectedStuffIDs.add(stuffListIDs[i].toString())
                }

            }

        }
        return binding.root
    }

    fun getStuff() {
        RetrofitClientAdmin.api.getAllStuff().enqueue(object : Callback<GetStuffResponse> {
            override fun onResponse(
                call: Call<GetStuffResponse>,
                response: Response<GetStuffResponse>
            ) {
                if (response.isSuccessful) {
                    stuffListIDs.clear()
                    var response = response.body()
                    var listName = response?.data?.map {
                        it.name
                    } ?: emptyList()
                    var listId = response?.data?.map {
                        it.id
                    } ?: emptyList()
                    Log.e("response", response?.status.toString())
                    stuffListIDs.addAll(listId)
                    stuffListNames.clear()


                    fillRecyclerView(listName)

                } else {
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("department response", "Error!")

                }
            }

            override fun onFailure(call: Call<GetStuffResponse>, t: Throwable) {
                Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                    .show()
                Log.e("department response", "Error : $t")

            }

        })
    }

    fun fillRecyclerView(listName: List<String>) {
        stuffListNames.clear()
        for (i in 0..listName.size - 1) {
            stuffListNames.add(Stuff(listName[i], R.drawable.uncheck_black_checkbox))
        }
        if (stuffListNames.isNotEmpty()) {
            adapter = StuffAdapter(stuffListNames, this)
            binding.rvListOfStudents.adapter = adapter
            binding.rvListOfStudents.layoutManager = LinearLayoutManager(requireActivity())
        }


    }

    override fun onItemClick(position: Int) {
        var itemClicked = stuffListNames[position]
        if (itemClicked.imgIsChecked == R.drawable.uncheck_black_checkbox) {
            listSelectedStuffIDs.add(stuffListIDs[position].toString())
            itemClicked.imgIsChecked = R.drawable.check_black_checkbox
            adapter.notifyItemChanged(position)
        } else {
            var id = stuffListIDs[position]
            listSelectedStuffIDs.remove("$id")
            itemClicked.imgIsChecked = R.drawable.uncheck_black_checkbox
            adapter.notifyItemChanged(position)
        }

    }

}