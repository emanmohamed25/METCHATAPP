package com.example.chatapp.doctor.newchat.admin.createnewchate.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentStuffBinding
import com.example.chatapp.doctor.newchat.admin.createnewchate.adapter.StuffAdapter
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Stuff
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsestuff.GetStuffResponse
import com.example.chatapp.doctor.newchat.admin.util.Constants
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StuffFragment : Fragment(), StuffAdapter.OnItemClickListener {

    lateinit var binding: FragmentStuffBinding
    var stuffListNames: MutableList<Stuff> = mutableListOf()
    var stuffListIDs: MutableList<Int> = mutableListOf()
    var listSelectedStuffIDs: MutableList<String> = mutableListOf()
    var myshared: SharedPreferences? = null
    lateinit var adapter: StuffAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStuffBinding.inflate(inflater, container, false)
        myshared = requireActivity().getSharedPreferences(Constants.MY_SHARED, 0)
        var adtoken = myshared?.getString("admintoken", "")
        getStuff()

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

    //    var itemClicked = sectionListNames[position]
//    if (itemClicked.imgIsChecked == R.drawable.uncheck_black_checkbox) {
//        listSelectedSectionIDs.add(sectionListIDs[position].toString())
//        Log.e("department response", "listSelectedSectionIDs  : $listSelectedSectionIDs")
//        itemClicked.imgIsChecked = R.drawable.check_black_checkbox
//        adapter.notifyItemChanged(position)
//    } else {
//        var id = sectionListIDs[position]
//        listSelectedSectionIDs.remove("$id")
//        Log.e("department response", "listSelectedSectionIDs  : $listSelectedSectionIDs")
//
//        itemClicked.imgIsChecked = R.drawable.uncheck_black_checkbox
//        adapter.notifyItemChanged(position)
//    }
//}
    override fun onItemClick(position: Int) {
        var itemClicked = stuffListNames[position]
        if (itemClicked.imgIsChecked == R.drawable.uncheck_black_checkbox) {
            listSelectedStuffIDs.add(stuffListIDs[position].toString())
            itemClicked.imgIsChecked = R.drawable.check_black_checkbox
            adapter.notifyItemChanged(position)
        }else{
            var id = stuffListIDs[position]
            listSelectedStuffIDs.remove("$id")
            itemClicked.imgIsChecked= R.drawable.uncheck_black_checkbox
            adapter.notifyItemChanged(position)
        }

    }

}