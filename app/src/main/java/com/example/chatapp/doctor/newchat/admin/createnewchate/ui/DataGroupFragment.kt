package com.example.chatapp.doctor.newchat.admin.createnewchate.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentDataGroupBinding
import com.example.chatapp.doctor.newchat.admin.createnewchate.adapter.GroupStudentAdapter
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Students
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin

class DataGroupFragment : Fragment(), GroupStudentAdapter.OnItemClickListener {

    lateinit var binding: FragmentDataGroupBinding

    //  lateinit var dataGroupViewModel: DataGroupViewModel
    lateinit var studentList: MutableList<Students>
    lateinit var adapter: GroupStudentAdapter
    lateinit var _groupName: String
    lateinit var _message: String

    var _studentList: MutableList<String> = mutableListOf()

    //  lateinit var dataRequest :DataGroupRequest
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataGroupBinding.inflate(inflater, container, false)
//        dataGroupViewModel=ViewModelProviders.of(requireActivity()).get(DataGroupViewModel::class.java)
        _groupName = binding.etGroupName.text.toString()
        _message = binding.etMessage.text.toString()


// dataRequest =DataGroupRequest(_groupName,_message,_studentList)


        Students("e", R.drawable.uncheck_black_checkbox)
        studentList = mutableListOf(
            Students("Eman", R.drawable.uncheck_black_checkbox),
            Students("rana", R.drawable.uncheck_black_checkbox),
            Students("rana", R.drawable.uncheck_black_checkbox),
            Students("abd elrahman", R.drawable.uncheck_black_checkbox),
            Students("sheref", R.drawable.uncheck_black_checkbox)
        )

        adapter = GroupStudentAdapter(studentList, this)
        binding.rvListOfStudents.adapter = adapter
        binding.rvListOfStudents.layoutManager = LinearLayoutManager(requireActivity())

        return binding.root
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onItemClick(position: Int) {
        _studentList.add((position + 1).toString())

        Toast.makeText(requireActivity(), "$position clicked", Toast.LENGTH_LONG).show()
        val clickedItem = studentList[position]
        if (clickedItem.imgIsChecked == R.drawable.uncheck_black_checkbox) {
            Toast.makeText(requireActivity(), "$position uncheck clicked", Toast.LENGTH_LONG).show()

            clickedItem.imgIsChecked = R.drawable.check_black_checkbox
            adapter.notifyItemChanged(position)
            println(_studentList)
        } else {

            Toast.makeText(requireActivity(), "$position check clicked", Toast.LENGTH_LONG).show()

//            _studentList.removeAt(_studentList.indexOf(""+position+1))
//            _studentList.remove((position+1).toString())
            clickedItem.imgIsChecked = R.drawable.uncheck_black_checkbox
            adapter.notifyItemChanged(position)
            println(_studentList)

        }
    }
    fun getStudents(){
       // RetrofitClientAdmin.api.sendDataGroupMessage()

    }

}