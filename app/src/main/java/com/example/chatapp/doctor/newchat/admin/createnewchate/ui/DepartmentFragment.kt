package com.example.chatapp.doctor.newchat.admin.createnewchate.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.chatapp.LoginScreenActivity
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentDepartment2Binding
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.DepartmentRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.DepartmentResponse
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DepartmentFragment : Fragment() {

    lateinit var binding: FragmentDepartment2Binding
    lateinit var departmentSelectedItem: String
    lateinit var levelSelectedItem: String
    lateinit var departmentRequest: DepartmentRequest
    lateinit var listSection: MutableList<String>
//    var positionD: Int? = null
//    var positionL: Int? = null
    var listSectionBoolean = MutableList<Int>(4) { 0 }
    var allsectionIsCheck: Boolean = false
    var departmentIsSelected:Boolean=false
    var levelIsSelected:Boolean=false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDepartment2Binding.inflate(inflater, container, false)

       // listSection= MutableList<String>(4){""}
        //custom spinner
        val customListDepartment = listOf<String>("Computer Science", "IT")
        val customListLevel = listOf<String>("level 4", "level 5", "all")

        val spinnerAdapterLevel =
            ArrayAdapter<String>(requireContext(), R.layout.spin_text_style, customListLevel)
        spinnerAdapterLevel.setDropDownViewResource(R.layout.spinner_item)
        val spinnerAdapterDepartment =
            ArrayAdapter<String>(requireContext(), R.layout.spin_text_style, customListDepartment)
        spinnerAdapterDepartment.setDropDownViewResource(R.layout.spinner_item)
        binding.spinDepartment.adapter = spinnerAdapterDepartment
        binding.spinLevels.adapter = spinnerAdapterLevel


        //functionality of checkBox
        binding.btnAllCheck.setOnClickListener {
            if (allsectionIsCheck) {
                allsectionIsCheck=false
                binding.btnAllCheck.setBackgroundResource(R.drawable.uncheck_wite_checkbox)
                binding.btnSection1Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                binding.btnSection2Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                binding.btnSection4Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                binding.btnSection5Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                listSection.clear()

                for (i in 0..3) {
                    listSectionBoolean.set(i, 0)
                }
            } else {
                allsectionIsCheck=true
                binding.btnAllCheck.setBackgroundResource(R.drawable.check_wite_checkbox)
                binding.btnSection1Check.setBackgroundResource(R.drawable.check_black_checkbox)
                binding.btnSection2Check.setBackgroundResource(R.drawable.check_black_checkbox)
                binding.btnSection4Check.setBackgroundResource(R.drawable.check_black_checkbox)
                binding.btnSection5Check.setBackgroundResource(R.drawable.check_black_checkbox)
//                listSection.add("1")
//                listSection.add("2")
//                listSection.add("4")
//                listSection.add("5")

                for (i in 0..3) {
                    listSectionBoolean.set(i, 1)
                }

            }
        }

        binding.btnSection1Check.setOnClickListener {
            if (listSectionBoolean[0] == 0) {
                listSectionBoolean.set(0, 1)
                binding.btnSection1Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                binding.btnSection1Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                listSectionBoolean.set(0, 0)

            }
            Toast.makeText(context, "" + listSectionBoolean, Toast.LENGTH_LONG)
                .show()
        }
        binding.btnSection2Check.setOnClickListener {
            if (listSectionBoolean[1] == 0) {
                listSectionBoolean.set(1, 1)
                binding.btnSection2Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                binding.btnSection2Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                listSectionBoolean.set(1, 0)

            }
            Toast.makeText(context, "" + listSectionBoolean, Toast.LENGTH_LONG)
                .show()


        }
        binding.btnSection4Check.setOnClickListener {
            if (listSectionBoolean[2] == 0) {
                listSectionBoolean.set(2, 1)
                binding.btnSection4Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                binding.btnSection4Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                listSectionBoolean.set(2, 0)

            }
            Toast.makeText(context, "" + listSectionBoolean, Toast.LENGTH_LONG)
                .show()

        }
        binding.btnSection5Check.setOnClickListener {
            if (listSectionBoolean[3] == 0) {
                listSectionBoolean.set(3, 1)
                binding.btnSection5Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                binding.btnSection5Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                listSectionBoolean.set(3, 0)

            }


        }

        var positionD: Int? = null
        var positionL: Int? = null
//spinner for department&&level handling
        binding.spinDepartment.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    Toast.makeText(context, "please choose the department!", Toast.LENGTH_LONG)
                        .show()

                }

                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                     departmentIsSelected = true
                     departmentSelectedItem = adapterView?.getItemAtPosition(position).toString()
                    positionD=position+1
                    Toast.makeText(context, "$positionD\n$departmentSelectedItem", Toast.LENGTH_LONG)
                        .show()

                }

            }
        binding.spinLevels.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        Toast.makeText(context, "please choose the level !", Toast.LENGTH_LONG)
                            .show()

                    }

                    override fun onItemSelected(
                        adapterView: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        levelIsSelected=true
                        levelSelectedItem = adapterView?.getItemAtPosition(position).toString()
                        positionL=position+1
                    }

                }

        listSection= MutableList<String>(4){""}

        binding.btnSend.setOnClickListener {
            listSection.clear()
//            listSection= MutableList<String>(4){""}
//loop to add section has checked in list
            for (i in 0..3) {

                if (listSectionBoolean[i] == 1) {
                    if (i <= 1) {
                        listSection.add("" + (i + 1))
                    } else if (i == 2) {
                        listSection.add("4")
                    } else if (i == 3) {
                        listSection.add("5")
                    }
                }
            }
            if (binding.etEnterMessage.text.isNullOrEmpty()) {
                Toast.makeText(context, "please enter the message !", Toast.LENGTH_LONG)
                    .show()
            } else {
                departmentRequest= DepartmentRequest(""+positionD,""+positionL,listSection,
                    binding.etEnterMessage.text.toString())

            }
            Log.e("department_id",departmentRequest.department_id)
            Log.e("level_id", departmentRequest.level_id.toString())
            Log.e("section_id", departmentRequest.section_id.toString())
            Log.e("message",departmentRequest.message)

            Toast.makeText(context, "${departmentRequest.department_id}\n " +
                    "${departmentRequest.level_id}\n${departmentRequest.section_id}\n" +
                    "${departmentRequest.message}}", Toast.LENGTH_LONG)
                .show()


var  token: String =LoginScreenActivity().getToken()
            Log.e("message",token)


            RetrofitClientAdmin.api.sendDepartmentMessage("Bearer $token",departmentRequest).enqueue(object :
                Callback<DepartmentResponse> {

                override fun onResponse(call: Call<DepartmentResponse>, response: Response<DepartmentResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        Toast.makeText(context,  ""+data?.status , Toast.LENGTH_LONG)
                            .show()

                    } else {
                        // Handle the error
                    }
                }
                override fun onFailure(call: Call<DepartmentResponse>, t: Throwable) {
                    Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                        .show()                }
            })
        }


            return binding.root
    }

}