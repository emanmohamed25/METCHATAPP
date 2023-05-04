package com.example.chatapp.doctor.newchat.admin.createnewchate.ui

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentDataGroupBinding
import com.example.chatapp.doctor.newchat.admin.createnewchate.adapter.GroupStudentAdapter
import com.example.chatapp.doctor.newchat.admin.createnewchate.adapter.SectionsAdapter
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.DataGroupRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.ListStudentRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Sections
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Students
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsegroup.DataGroupResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsegroup.list.student.ListStudentResponse
import com.example.chatapp.doctor.newchat.admin.util.Constants
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataGroupFragment : Fragment(), GroupStudentAdapter.OnItemClickListener {

    lateinit var binding: FragmentDataGroupBinding
    var myshared: SharedPreferences? = null

    val args :DataGroupFragmentArgs by navArgs()

    lateinit var dataGroupRequest: DataGroupRequest
    lateinit var adapter: GroupStudentAdapter
    var studentListNames: MutableList<Students> = mutableListOf()
    var studentListIDs: MutableList<Int> = mutableListOf()
    var listSelectedStudentIDs: MutableList<String> = mutableListOf()
   //
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val loading =LoadingDialog(requireActivity())
       loading.startLoading()
       val handler= Handler()
       handler.postDelayed(object :Runnable{
           override fun run() {
               loading.isDismiss()
           }

       },2500 )
        binding = FragmentDataGroupBinding.inflate(inflater, container, false)
       myshared = requireActivity().getSharedPreferences(Constants.MY_SHARED, 0)
       var adminToken = myshared?.getString("admintoken", "")
//
        var departmentId =args.departmentId
        var levelId = args.levelId
       Toast.makeText(context,"departmentId : $departmentId\n levelId : $levelId",Toast.LENGTH_LONG).show()

//        var departmentId =2
//        var levelId =2
//
        val listStudentRequest=ListStudentRequest(departmentId.toString(),levelId.toString())
        getStudent(listStudentRequest)

       binding.btnSend.setOnClickListener{
           if (binding.etGroupName.text.toString().isNullOrEmpty()
               ||binding.etMessage.text.toString().isNullOrEmpty()) {
               Toast.makeText(context,"please enter the group name and your message!",Toast.LENGTH_LONG).show()
           }
           else{
               dataGroupRequest= DataGroupRequest(binding.etGroupName.text.toString()
                   ,binding.etMessage.text.toString(),listSelectedStudentIDs)

               RetrofitClientAdmin.api.sendDataGroupMessage("Bearer $adminToken",dataGroupRequest)
                   .enqueue(object :Callback<DataGroupResponse>{
                       override fun onResponse(
                           call: Call<DataGroupResponse>,
                           response: Response<DataGroupResponse>
                       ) {
                           if(response.isSuccessful){
                               val data =response.body()
                               Toast.makeText(context, "${data?.status}\n${data?.data?.group?.name}", Toast.LENGTH_SHORT)
                                   .show()
                               Log.e("department response", "onResponse : ${data?.data?.group}\n${data?.data?.message}")

                           }

                       }
                       override fun onFailure(call: Call<DataGroupResponse>, t: Throwable) {
                           Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                               .show()
                           Log.e("department response", "Error : $t")
                       }

                   })
           }
           Log.e("response","dataGroupRequest : $dataGroupRequest")
       }

        return binding.root
    }
    fun getStudent(request: ListStudentRequest) {
        RetrofitClientAdmin.api.getListStudents(request).enqueue(object :Callback<ListStudentResponse>{
            override fun onResponse(
                call: Call<ListStudentResponse>,
                response: Response<ListStudentResponse>
            ) {
                if(response.isSuccessful){
                    studentListIDs.clear()

                    studentListNames.clear()

                    val data =response.body()
                    val listNames = data?.data?.map {
                        it.name
                    }?: emptyList()
                    val listIDs = data?.data?.map {
                        it.id
                    }?: emptyList()
                    Log.e("department response", "onResponse : $listNames\n $listIDs")

                    studentListIDs.addAll(listIDs)
                    Toast.makeText(context, "onResponse : $listNames\n $listIDs", Toast.LENGTH_SHORT)
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

        }
        else{
            adapter = GroupStudentAdapter(studentListNames, this)
            binding.rvListOfStudents.adapter = adapter
            binding.rvListOfStudents.layoutManager = LinearLayoutManager(requireActivity())
            Toast.makeText(context, "list Student added !", Toast.LENGTH_SHORT)
                .show()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onItemClick(position: Int) {
        Toast.makeText(requireActivity(), "$position clicked", Toast.LENGTH_LONG).show()
        val clickedItem = studentListNames[position]
        if (clickedItem.imgIsChecked == R.drawable.uncheck_black_checkbox) {
            Toast.makeText(requireActivity(), "$position check clicked", Toast.LENGTH_LONG).show()
            listSelectedStudentIDs.add(studentListIDs[position].toString())
            Log.e("response","listSelectedStudentIDs : $listSelectedStudentIDs")
            clickedItem.imgIsChecked = R.drawable.check_black_checkbox
            adapter.notifyItemChanged(position)
        } else {

            Toast.makeText(requireActivity(), "$position uncheck clicked", Toast.LENGTH_LONG).show()
            var id = studentListIDs[position]
            listSelectedStudentIDs.remove("$id")
            Log.e("response","listSelectedStudentIDs : $listSelectedStudentIDs")

            clickedItem.imgIsChecked = R.drawable.uncheck_black_checkbox
            adapter.notifyItemChanged(position)

        }
    }


}