package com.example.chatapp.doctor.newchat.admin.createnewchate.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentAll2Binding
import com.example.chatapp.doctor.newchat.admin.createnewchate.adapter.AllStudentsAdapter
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.DataGroupRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Students
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responseallstudent.SingleStudentResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsegroup.DataGroupResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsegroup.list.student.ListStudentResponse
import com.example.chatapp.doctor.newchat.admin.util.Constants
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin
import kotlinx.android.synthetic.main.fragment_department2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllFragment : Fragment(), AllStudentsAdapter.OnItemClickListener {

    lateinit var binding: FragmentAll2Binding
    var studentListNames: MutableList<Students> = mutableListOf()
    var studentListIDs: MutableList<Int> = mutableListOf()
    var listSelectedStudentIDs: MutableList<String> = mutableListOf()
    lateinit var adapter: AllStudentsAdapter
    var allSectionIsCheck: Boolean = false
    var myshared: SharedPreferences? = null
    lateinit var dataGroupRequest: DataGroupRequest

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


        // Inflate the layout for this fragment
        binding = FragmentAll2Binding.inflate(layoutInflater, container, false)
        myshared = requireActivity().getSharedPreferences(Constants.MY_SHARED, 0)
        var adtoken = myshared?.getString("admintoken", "")
        getStudent()

        binding.btnSend.setOnClickListener {
            if (binding.etGroupName.text.toString().isNullOrEmpty()
                || binding.etEnterMessage.text.toString().isNullOrEmpty()
            ) {
                Toast.makeText(
                    context,
                    "please enter the group name and your message!",
                    Toast.LENGTH_LONG
                ).show()
            } else if (listSelectedStudentIDs.size == 1) {

                var studentId = listSelectedStudentIDs[0]
                RetrofitClientAdmin.api.sendMessageSingelStudent(
                    "Bearer $adtoken",
                    studentId,
                    binding.etEnterMessage.text.toString()
                )
                    .enqueue(object : Callback<SingleStudentResponse> {
                        override fun onResponse(
                            call: Call<SingleStudentResponse>,
                            response: Response<SingleStudentResponse>
                        ) {
                            Toast.makeText(
                                context,
                                "${response.body()?.message}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            Log.e("department response", "onResponse : ${response.body()?.message}")

                        }

                        override fun onFailure(call: Call<SingleStudentResponse>, t: Throwable) {
                            Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                                .show()
                            Log.e("department response", "Error : $t")

                        }

                    })
            } else {
                dataGroupRequest = DataGroupRequest(
                    binding.etGroupName.text.toString(),
                    binding.etEnterMessage.text.toString(),
                    listSelectedStudentIDs
                )
                RetrofitClientAdmin.api.sendDataGroupMessage("Bearer $adtoken", dataGroupRequest)
                    .enqueue(
                        object : Callback<DataGroupResponse> {
                            override fun onResponse(
                                call: Call<DataGroupResponse>,
                                response: Response<DataGroupResponse>
                            ) {
                                if (response.isSuccessful) {
                                    val data = response.body()
                                    Toast.makeText(
                                        context,
                                        "${data?.status}\n${data?.data?.group?.name}",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                    Log.e(
                                        "department response",
                                        "onResponse : ${data?.data?.group}\n${data?.data?.message}"
                                    )

                                }
                            }

                            override fun onFailure(call: Call<DataGroupResponse>, t: Throwable) {
                                Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                                    .show()
                                Log.e("department response", "Error : $t")
                            }
                        }
                    )

            }
        }
        binding.btnAllCheck.setOnClickListener {
            if (allSectionIsCheck) {
                allSectionIsCheck = false
                btnAllCheck.setBackgroundResource(R.drawable.uncheck_wite_checkbox)
                for (i in 0..studentListNames.size - 1) {
                    studentListNames[i].imgIsChecked = R.drawable.uncheck_black_checkbox
                    adapter.notifyItemChanged(i)
                }
                listSelectedStudentIDs.clear()

            } else {
                btnAllCheck.setBackgroundResource(R.drawable.check_wite_checkbox)

                allSectionIsCheck = true
                for (i in 0..studentListNames.size - 1) {
                    studentListNames[i].imgIsChecked = R.drawable.check_black_checkbox
                    adapter.notifyItemChanged(i)
                    listSelectedStudentIDs.add(studentListIDs[i].toString())
                }

            }
        }

        return binding.root
    }

    fun getStudent() {
        RetrofitClientAdmin.api.getAllStudents().enqueue(object :
            Callback<ListStudentResponse> {
            override fun onResponse(
                call: Call<ListStudentResponse>,
                response: Response<ListStudentResponse>
            ) {
                if (response.isSuccessful) {
                    studentListIDs.clear()

                    studentListNames.clear()

                    val data = response.body()
                    val listNames = data?.data?.map {
                        it.name
                    } ?: emptyList()
                    val listIDs = data?.data?.map {
                        it.id
                    } ?: emptyList()
                    Log.e("department response", "onResponse : $listNames\n $listIDs")

                    studentListIDs.addAll(listIDs)
                    Toast.makeText(
                        context,
                        "onResponse : $listNames\n $listIDs",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    Log.e("department response", "onResponse : $listNames\n $listIDs")
                    fillRecyclerView(listNames)
                }

            }

            override fun onFailure(call: Call<ListStudentResponse>, t: Throwable) {
                Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                    .show()
                Log.e("department response", "Error : $t")
            }

        })

    }

    fun fillRecyclerView(listName: List<String>) {
        studentListNames.clear()
        for (i in 0..listName.size - 1) {
            studentListNames.add(Students(listName[i], R.drawable.uncheck_black_checkbox))
        }
        if (studentListNames.isNullOrEmpty()) {
            Toast.makeText(context, "no Student added !", Toast.LENGTH_SHORT)
                .show()
            Log.e("department response", "Error : no Student added !")

        } else {
            adapter = AllStudentsAdapter(studentListNames, this)
            binding.rvListOfStudents.adapter = adapter
            binding.rvListOfStudents.layoutManager = LinearLayoutManager(requireActivity())
            Toast.makeText(context, "list Student added !", Toast.LENGTH_SHORT)
                .show()
        }
    }


    override fun onItemClick(position: Int) {
        Toast.makeText(requireActivity(), "$position clicked", Toast.LENGTH_LONG).show()
        val clickedItem = studentListNames[position]
        if (clickedItem.imgIsChecked == R.drawable.uncheck_black_checkbox) {
            Toast.makeText(requireActivity(), "$position check clicked", Toast.LENGTH_LONG).show()
            listSelectedStudentIDs.add(studentListIDs[position].toString())
            Log.e("response", "listSelectedStudentIDs : $listSelectedStudentIDs")
            clickedItem.imgIsChecked = R.drawable.check_black_checkbox
            adapter.notifyItemChanged(position)
        } else {

            Toast.makeText(requireActivity(), "$position uncheck clicked", Toast.LENGTH_LONG).show()
            var id = studentListIDs[position]
            listSelectedStudentIDs.remove("$id")
            Log.e("response", "listSelectedStudentIDs : $listSelectedStudentIDs")

            clickedItem.imgIsChecked = R.drawable.uncheck_black_checkbox
            adapter.notifyItemChanged(position)

        }
    }

}