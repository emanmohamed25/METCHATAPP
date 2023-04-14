package com.example.chatapp.doctor.newchat.admin.createnewchate.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentDepartment2Binding
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.DepartmentRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.DepartmentResponse
import com.example.chatapp.doctor.newchat.admin.util.Constants.Companion.MY_SHARED
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
    var listSectionBoolean = MutableList<Int>(8) { 0 }
    var allsectionIsCheck: Boolean = false
    var departmentIsSelected: Boolean = false
    var levelIsSelected: Boolean = false
    var myshared: SharedPreferences? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDepartment2Binding.inflate(inflater, container, false)

//get token
        myshared = requireActivity().getSharedPreferences(MY_SHARED, 0)
        var adtoken = myshared?.getString("admintoken", "")
        Toast.makeText(context, "" + adtoken, Toast.LENGTH_LONG)
            .show()


        // listSection= MutableList<String>(4){""}
        //custom spinner
        val customListDepartment =
            listOf<String>("Computer Science", "Information Systems", "Accounting")
        val customListLevel = listOf<String>("level 1", "level 2", "level 3", "level 4")

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
                allsectionIsCheck = false
                binding.btnAllCheck.setBackgroundResource(R.drawable.uncheck_wite_checkbox)
                binding.btnSection1Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                binding.btnSection2Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                binding.btnSection3Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                binding.btnSection4Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                binding.btnSection5Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                binding.btnSection6Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                binding.btnSection7Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                binding.btnSection8Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)

                listSection.clear()

                for (i in 0..listSectionBoolean.size) {
                    listSectionBoolean.set(i, 0)
                }
            } else {
                allsectionIsCheck = true
                binding.btnAllCheck.setBackgroundResource(R.drawable.check_wite_checkbox)
                binding.btnSection1Check.setBackgroundResource(R.drawable.check_black_checkbox)
                binding.btnSection2Check.setBackgroundResource(R.drawable.check_black_checkbox)
                binding.btnSection3Check.setBackgroundResource(R.drawable.check_black_checkbox)
                binding.btnSection4Check.setBackgroundResource(R.drawable.check_black_checkbox)
                binding.btnSection5Check.setBackgroundResource(R.drawable.check_black_checkbox)
                binding.btnSection6Check.setBackgroundResource(R.drawable.check_black_checkbox)
                binding.btnSection7Check.setBackgroundResource(R.drawable.check_black_checkbox)
                binding.btnSection8Check.setBackgroundResource(R.drawable.check_black_checkbox)
//                listSection.add("1")
//                listSection.add("2")
//                listSection.add("4")
//                listSection.add("5")

                for (i in 0..listSectionBoolean.size) {
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
        binding.btnSection3Check.setOnClickListener {
            if (listSectionBoolean[2] == 0) {
                listSectionBoolean.set(2, 1)
                binding.btnSection3Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                binding.btnSection3Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                listSectionBoolean.set(2, 0)

            }
            Toast.makeText(context, "" + listSectionBoolean, Toast.LENGTH_LONG)
                .show()
        }
        binding.btnSection4Check.setOnClickListener {
            if (listSectionBoolean[3] == 0) {
                listSectionBoolean.set(3, 1)
                binding.btnSection4Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                binding.btnSection4Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                listSectionBoolean.set(3, 0)

            }
            Toast.makeText(context, "" + listSectionBoolean, Toast.LENGTH_LONG)
                .show()

        }
        binding.btnSection5Check.setOnClickListener {
            if (listSectionBoolean[4] == 0) {
                listSectionBoolean.set(4, 1)
                binding.btnSection5Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                binding.btnSection5Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                listSectionBoolean.set(4, 0)

            }


        }
        binding.btnSection6Check.setOnClickListener {
            if (listSectionBoolean[5] == 0) {
                listSectionBoolean.set(5, 1)
                binding.btnSection6Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                binding.btnSection6Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                listSectionBoolean.set(5, 0)

            }
            Toast.makeText(context, "" + listSectionBoolean, Toast.LENGTH_LONG)
                .show()
        }
        binding.btnSection7Check.setOnClickListener {
            if (listSectionBoolean[6] == 0) {
                listSectionBoolean.set(6, 1)
                binding.btnSection7Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                binding.btnSection7Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                listSectionBoolean.set(6, 0)

            }
            Toast.makeText(context, "" + listSectionBoolean, Toast.LENGTH_LONG)
                .show()
        }
        binding.btnSection8Check.setOnClickListener {
            if (listSectionBoolean[7] == 0) {
                listSectionBoolean.set(7, 1)
                binding.btnSection8Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                binding.btnSection8Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
                listSectionBoolean.set(7, 0)

            }
            Toast.makeText(context, "" + listSectionBoolean, Toast.LENGTH_LONG)
                .show()
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
                    positionD = position + 1
                    Toast.makeText(
                        context,
                        "$positionD\n$departmentSelectedItem",
                        Toast.LENGTH_LONG
                    )
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
                    levelIsSelected = true
                    levelSelectedItem = adapterView?.getItemAtPosition(position).toString()
                    positionL = position + 1
                }

            }

        listSection = MutableList<String>(4) { "" }
//Button send Message
        binding.btnSend.setOnClickListener {
            listSection.clear()
            var _levelID:String=""
//            listSection= MutableList<String>(4){""}
//loop to add section has checked in list
            for (i in 1..3) {
                if (positionD == i) when (i) {
                    1 -> {
                        println("$positionD")
                        val levelID= mutableListOf<String>("1","4","7","10")
                        for (id in 1..4){
                            if (positionL==id)
                              _levelID=levelID[id-1]
                        }
                        println("$_levelID")

                    }
                    2 -> {
                        val levelID= mutableListOf<String>("2","5","8","11")
                        for (id in 1..4){
                            if (positionL==id)
                                _levelID=levelID[id-1]
                        }
                        println("$_levelID")


                    }
                    3 -> {
                        println("$positionD")
                        val levelID= mutableListOf<String>("3","6","9","12")
                        for (id in 1..4){
                            if (positionL==id)
                                _levelID=levelID[id-1]
                        }
                        println("$_levelID")

                    }

            }}

//            for (i in 0 until listSectionBoolean.size) {
//
//                if (listSectionBoolean[i] == 1) {
//
//                    listSection.add("" + (i + 1))
//
//                }
//            }
            if (binding.etEnterMessage.text.isNullOrEmpty()) {
                Toast.makeText(context, "please enter the message !", Toast.LENGTH_LONG)
                    .show()
            } else {
                departmentRequest = DepartmentRequest(
                    "" + positionD, "" + _levelID,
                    binding.etEnterMessage.text.toString()
                )

            }
            Log.e("department_id", departmentRequest.department_id)
            Log.e("level_id", departmentRequest.level_id.toString())
           // Log.e("section_id", departmentRequest.section_id.toString())
            Log.e("message", departmentRequest.message)

            Toast.makeText(
                context, "${departmentRequest.department_id}\n " +
                        "${departmentRequest.level_id}\n" +
                     //   "${departmentRequest.section_id}\n" +
                        "${departmentRequest.message}}", Toast.LENGTH_LONG
            ).show()


//var  token: String =LoginScreenActivity().getToken()
            if (adtoken != null) {
                Log.e("message", adtoken)
            } else
                Log.e("message", "token is null!")



            RetrofitClientAdmin.api.sendDepartmentMessage("Bearer $adtoken", departmentRequest)
                .enqueue(object :
                    Callback<DepartmentResponse> {

                    override fun onResponse(
                        call: Call<DepartmentResponse>,
                        response: Response<DepartmentResponse>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            Toast.makeText(
                                context,
                                data?.message ,
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            // Handle the error
                        }
                    }

                    override fun onFailure(call: Call<DepartmentResponse>, t: Throwable) {
                        Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                            .show()
                        Log.e("chat response", "Error : $t")

                    }
                })
        }


        return binding.root
    }

}