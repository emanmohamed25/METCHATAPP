package com.example.chatapp.doctor.newchat.createnewchate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentDepartment2Binding
import com.example.chatapp.doctor.newchat.createnewchate.data.DepartmentRequest

class DepartmentFragment : Fragment() {

    lateinit var binding: FragmentDepartment2Binding
    lateinit var departmentSelectedItem: String
    lateinit var levelSelectedItem: String
    lateinit var departmentRequest: DepartmentRequest
    lateinit var listSection: ArrayList<String>
    lateinit var listSectionBoolean: ArrayList<Int>
    var positionD:Int=null!!
    var positionL:Int=null!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDepartment2Binding.inflate(inflater, container, false)

        for (i in 0..3) {
            listSectionBoolean.add(i, 0)
        }
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
//        binding.btnAllCheck.setOnClickListener {
//
//            binding.btnSection1Check.setBackgroundResource(R.drawable.check_wite_checkbox)
//            binding.btnSection1Check.setBackgroundResource(R.drawable.check_black_checkbox)
//            binding.btnSection2Check.setBackgroundResource(R.drawable.check_black_checkbox)
//            binding.btnSection4Check.setBackgroundResource(R.drawable.check_black_checkbox)
//            binding.btnSection5Check.setBackgroundResource(R.drawable.check_black_checkbox)
//            listSection.add("1")
//            listSection.add("2")
//            listSection.add("4")
//            listSection.add("5")
//
//
//        }

        binding.btnSection1Check.setOnClickListener {
            if (listSectionBoolean[0] == 0) {
                listSectionBoolean[0] == 1
                binding.btnSection1Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                listSectionBoolean[0] == 0
                binding.btnSection1Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
            }

        }
        binding.btnSection2Check.setOnClickListener {
            if (listSectionBoolean[1] == 0) {
                listSectionBoolean[1] == 1
                binding.btnSection1Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                listSectionBoolean[1] == 0
                binding.btnSection1Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
            }

        }
        binding.btnSection4Check.setOnClickListener {
            if (listSectionBoolean[2] == 0) {
                listSectionBoolean[2] == 1
                binding.btnSection1Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                listSectionBoolean[2] == 0
                binding.btnSection1Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
            }

        }
        binding.btnSection5Check.setOnClickListener {
            if (listSectionBoolean[3] == 0) {
                listSectionBoolean[3] == 1
                binding.btnSection1Check.setBackgroundResource(R.drawable.check_black_checkbox)
            } else {
                listSectionBoolean[3] == 0
                binding.btnSection1Check.setBackgroundResource(R.drawable.uncheck_black_checkbox)
            }

        }


//send button
        binding.btnSend.setOnClickListener {

            var departmentIsSelected: Boolean = false
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
                        positionD=position
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
                        levelSelectedItem = adapterView?.getItemAtPosition(position).toString()
                    }

                }


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
                departmentRequest= DepartmentRequest("$positionD","$positionL",listSection,
                    binding.etEnterMessage.text.toString())

            }

        }




        return binding.root
    }

}