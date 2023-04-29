package com.example.chatapp.doctor.newchat.admin.createnewchate.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentDataGroupBinding
import com.example.chatapp.doctor.newchat.admin.createnewchate.adapter.GroupStudentAdapter
import com.example.chatapp.doctor.newchat.admin.createnewchate.adapter.SectionsAdapter
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.ListStudentRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Sections
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Students
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsegroup.list.student.ListStudentResponse
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataGroupFragment : Fragment(), GroupStudentAdapter.OnItemClickListener {

    lateinit var binding: FragmentDataGroupBinding

    //  lateinit var dataGroupViewModel: DataGroupViewModel
    //lateinit var studentList: MutableList<Students>
    lateinit var adapter: GroupStudentAdapter
    lateinit var _groupName: String
    lateinit var _message: String
    var studentListNames: MutableList<Students> = mutableListOf()
    var studentListIDs: MutableList<Int> = mutableListOf()
    var listSelectedStudentIDs: MutableList<String> = mutableListOf()
   // var studentList: MutableList<Students> = mutableListOf()

    //  lateinit var dataRequest :DataGroupRequest
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataGroupBinding.inflate(inflater, container, false)
//        dataGroupViewModel=ViewModelProviders.of(requireActivity()).get(DataGroupViewModel::class.java)
//        val idS = DataGroupFragmentArgs.fromBundle(arguments)
//        var departmentId = idS.departmentId
//        var levelId = idS.levelId
        var departmentId =1
        var levelId =10
        val listStudentRequest=ListStudentRequest(departmentId.toString(),levelId.toString())
        getStudent(listStudentRequest)
        _groupName = binding.etGroupName.text.toString()
        _message = binding.etMessage.text.toString()


// dataRequest =DataGroupRequest(_groupName,_message,_studentList)


        Students("e", R.drawable.uncheck_black_checkbox)
//        studentList = mutableListOf(
//            Students("Eman", R.drawable.uncheck_black_checkbox),
//            Students("rana", R.drawable.uncheck_black_checkbox),
//            Students("rana", R.drawable.uncheck_black_checkbox),
//            Students("abd elrahman", R.drawable.uncheck_black_checkbox),
//            Students("sheref", R.drawable.uncheck_black_checkbox)
//        )

//        adapter = GroupStudentAdapter(studentList, this)
//        binding.rvListOfStudents.adapter = adapter
//        binding.rvListOfStudents.layoutManager = LinearLayoutManager(requireActivity())

        return binding.root
    }
    fun getStudent(request: ListStudentRequest) {
        RetrofitClientAdmin.api.getListStudents(request).enqueue(object :Callback<ListStudentResponse>{
            override fun onResponse(
                call: Call<ListStudentResponse>,
                response: Response<ListStudentResponse>
            ) {
                if(response.isSuccessful){
                    val data =response.body()
                    val listNames = data?.data?.map {
                        it.name
                    }?: emptyList()
                    val listIDs = data?.data?.map {
                        it.id
                    }?: emptyList()
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
        if (studentListNames.isNotEmpty()) {
            adapter = GroupStudentAdapter(studentListNames, this)
            binding.rvListOfStudents.adapter = adapter
            binding.rvListOfStudents.layoutManager = LinearLayoutManager(requireActivity())

        }
        else{
            Toast.makeText(context, "no Student added !", Toast.LENGTH_SHORT)
                .show()
            Log.e("department response", "Error : no Student added !")
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onItemClick(position: Int) {
        listSelectedStudentIDs.clear()
        Toast.makeText(requireActivity(), "$position clicked", Toast.LENGTH_LONG).show()
        val clickedItem = studentListNames[position]
        if (clickedItem.imgIsChecked == R.drawable.uncheck_black_checkbox) {
            Toast.makeText(requireActivity(), "$position uncheck clicked", Toast.LENGTH_LONG).show()
            listSelectedStudentIDs.add(studentListIDs[position].toString())
            clickedItem.imgIsChecked = R.drawable.check_black_checkbox
            adapter.notifyItemChanged(position)
        } else {

            Toast.makeText(requireActivity(), "$position check clicked", Toast.LENGTH_LONG).show()
            var id = studentListIDs[position]
            listSelectedStudentIDs.remove("$id")
            clickedItem.imgIsChecked = R.drawable.uncheck_black_checkbox
            adapter.notifyItemChanged(position)

        }
    }


}