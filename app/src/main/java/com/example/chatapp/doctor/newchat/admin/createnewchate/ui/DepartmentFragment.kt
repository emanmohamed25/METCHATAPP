package com.example.chatapp.doctor.newchat.admin.createnewchate.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentDepartment2Binding
import com.example.chatapp.doctor.newchat.admin.createnewchate.adapter.SectionsAdapter
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.DepartmentRequest
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Sections
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.levelspinner.LevelsResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.section.SectionsResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.sendwithlevel.SendMsgWithLevel
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.sendwithsection.SendMsgWithSection
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.spinner.DepartmentSpinnerResponse
import com.example.chatapp.doctor.newchat.admin.util.Constants.Companion.MY_SHARED
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DepartmentFragment : Fragment(), SectionsAdapter.OnItemClickListener {

    lateinit var binding: FragmentDepartment2Binding
    lateinit var departmentSelectedItem: String
    lateinit var levelSelectedItem: String
    lateinit var departmentRequest: DepartmentRequest
    lateinit var adapter: SectionsAdapter

    //     var customListDepartmentNames :MutableList<String> = mutableListOf()
    var customListDepartmentIDs: MutableList<Int> = mutableListOf()
    var customListLevelIDs: MutableList<Int> = mutableListOf()
    var sectionListNames: MutableList<Sections> = mutableListOf()
    var sectionListIDs: MutableList<Int> = mutableListOf()
    var listSelectedSectionIDs: MutableList<String> = mutableListOf()

    //    var positionD: Int? = null
//    var positionL: Int? = null
    var listSectionBoolean = MutableList<Int>(8) { 0 }
    var allsectionIsCheck: Boolean = false
    var departmentIsSelected: Boolean = false
    var levelIsSelected: Boolean = false
    var myshared: SharedPreferences? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("department response", "Error1 : ${customListDepartmentIDs.size}")
//        GlobalScope.launch {
            fillSpinnerDepartment(binding.spinDepartment)
            Log.e("department response", "Error2 : ${customListDepartmentIDs.size}")

//        }

    }

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
        Log.e("department response", "Error3 : ${customListDepartmentIDs.size}")


        var positionD: Int? = null
        var positionL: Int? = null


//Button send Message
        binding.btnSend.setOnClickListener {
            var _levelID: String = ""
            Log.e("department response",
                "departmentSelectedItem : ${departmentSelectedItem}\n" +
                        "levelSelectedItem : $levelSelectedItem\n" +
                        "listSelectedSectionIDs  : $listSelectedSectionIDs")


            if (binding.etEnterMessage.text.isNullOrEmpty()) {
                Toast.makeText(context, "please enter the message !", Toast.LENGTH_LONG)
                    .show()
            } else {
                departmentRequest = DepartmentRequest(
                    departmentSelectedItem, levelSelectedItem,listSelectedSectionIDs,
                    binding.etEnterMessage.text.toString()
                )

            }
            if (adtoken != null) {
                Log.e("message", adtoken)
            } else
                Log.e("message", "token is null!")
            if (departmentRequest.level_id!!.isNotEmpty()
                && departmentRequest.section_id!!.isNotEmpty() ){
               RetrofitClientAdmin.api
                    .sendDepartmentMessageSection("Bearer $adtoken"
                        ,departmentRequest)

                    .enqueue(object :Callback<SendMsgWithSection>{
                    override fun onResponse(
                        call: Call<SendMsgWithSection>,
                        response: Response<SendMsgWithSection>
                    ) {
                        if (response.isSuccessful) {

                            val data=response.body()
                            for (i in 0..data!!.sections.size-1){
                            Toast.makeText(
                                context,
                                "message send to : ${data?.sections?.get(i)?.name}\n",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            Log.e("chat response", "message send to : ${data?.sections?.get(i)?.name}\n")
                        }}
                    }

                    override fun onFailure(call: Call<SendMsgWithSection>, t: Throwable) {
                        Toast.makeText(context, "onFailure: $t", Toast.LENGTH_SHORT)
                            .show()
                        Log.e("chat response", "onFailure : $t")
                    }


                    })
            }else if(departmentRequest.section_id.isNullOrEmpty()){
                RetrofitClientAdmin.api.sendDepartmentMessageLevel("Bearer $adtoken"
                    ,departmentRequest).enqueue(object :Callback<SendMsgWithLevel>{
                    override fun onResponse(
                        call: Call<SendMsgWithLevel>,
                        response: Response<SendMsgWithLevel>
                    ) {
                        if(response.isSuccessful){
                        val data = response.body()
                            Toast.makeText(context, "message send to : ${data?.yearLevel?.name}", Toast.LENGTH_SHORT)
                                .show()
                            Log.e("chat response", "message send to : ${data?.yearLevel?.name}")

                        }

                    }

                    override fun onFailure(call: Call<SendMsgWithLevel>, t: Throwable) {
                        Toast.makeText(context, "onFailure: $t", Toast.LENGTH_SHORT)
                            .show()
                        Log.e("chat response", "onFailure : $t")                    }

                })
            }


//            RetrofitClientAdmin.api.sendDepartmentMessage("Bearer $adtoken", departmentRequest)
//                .enqueue(object :
//                    Callback<DepartmentResponse> {
//
//                    override fun onResponse(
//                        call: Call<DepartmentResponse>,
//                        response: Response<DepartmentResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val data = response.body()
//                            Toast.makeText(
//                                context,
//                                data?.message,
//                                Toast.LENGTH_LONG
//                            ).show()
//                        } else {
//                            // Handle the error
//                        }
//                    }
//
//                    override fun onFailure(call: Call<DepartmentResponse>, t: Throwable) {
//                        Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
//                            .show()
//                        Log.e("chat response", "Error : $t")
//
//                    }
//                })
        }
        return binding.root
    }

     fun fillSpinnerDepartment(spinner: Spinner) {

        var customListDepartmentNames: MutableList<String> = mutableListOf()

        RetrofitClientAdmin.api.getDepartment().enqueue(
            object : Callback<DepartmentSpinnerResponse> {

                override fun onResponse(
                    call: Call<DepartmentSpinnerResponse>,
                    response: Response<DepartmentSpinnerResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()

                        Log.e("department response", data?.data.toString())

                        val listNames = data?.data?.map {
                            it.name
                        } ?: emptyList()
                        val listIDs = data?.data?.map {
                            it.id
                        } ?: emptyList()
                        customListDepartmentNames.addAll(listNames)
                        // customListDepartmentIDs.addAll(listIDs)
                        setList(listIDs)
                        Log.e("department response", "Error4 : ${customListDepartmentIDs.size}")

                        Toast.makeText(
                            context,
                            data?.message + "\n ${customListDepartmentNames[0]}",
                            Toast.LENGTH_LONG
                        ).show()
                        val spinnerAdapterDepartment =
                            ArrayAdapter<String>(
                                spinner.context, R.layout.spin_text_style,
                                customListDepartmentNames
                            )
                        spinnerAdapterDepartment.setDropDownViewResource(R.layout.spinner_item)
                        binding.spinDepartment.adapter = spinnerAdapterDepartment

                    } else {
                        Log.e("department response", "Error !")

                        // Handle the error
                    }
                }

                override fun onFailure(call: Call<DepartmentSpinnerResponse>, t: Throwable) {
                    Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("department response", "Error : $t")

                }
            })
    }

    fun setList(listId: List<Int>) {
        customListDepartmentIDs.clear()
        customListDepartmentIDs.addAll(listId)
        Log.e("department response", "Error5 : ${customListDepartmentIDs.size}")
        spinDepartment()
    }

    fun spinDepartment() {
        binding.spinDepartment.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val selectedCountry = parent.getItemAtPosition(position) as String
                    var id = customListDepartmentIDs.get(position)
                    departmentSelectedItem = id.toString()

                    customListLevelIDs.clear()
                    Log.e(
                        "department response",
                        "departmentSelectedItem  :  $departmentSelectedItem  ;  " +
                                "id : $id ; onItemSelected : ${customListDepartmentIDs.size}"
                    )
                    fillSpinnerLevel(id)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(context, "Error:no item selected ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

    }

    private fun fillSpinnerLevel(id: Int) {
        var customListLevelsNames: MutableList<String> = mutableListOf()
        RetrofitClientAdmin.api.getLevels(id).enqueue(object : Callback<LevelsResponse> {
            override fun onResponse(
                call: Call<LevelsResponse>,
                response: Response<LevelsResponse>
            ) {
                if (response.isSuccessful) {

                    var data = response.body()
                    val listNames = data?.data?.map {
                        it.name
                    } ?: emptyList()
                    val listIDs = data?.data?.map {
                        it.id
                    } ?: emptyList()
                    sectionListNames.clear()

                    customListLevelsNames.addAll(listNames)
                    Log.e("department response", "Error : $listIDs")
                    setLevelIDList(listIDs)
                    //customListLevelIDs.addAll(listIDs)
                    val spinnerAdapterLevel =
                        ArrayAdapter<String>(
                            requireContext(),
                            R.layout.spin_text_style,
                            customListLevelsNames
                        )
                    spinnerAdapterLevel.setDropDownViewResource(R.layout.spinner_item)
                    binding.spinLevels.adapter = spinnerAdapterLevel

                }
            }
            override fun onFailure(call: Call<LevelsResponse>, t: Throwable) {
                Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                    .show()
                Log.e("department response", "Error : $t")
            }
        })
    }
    fun setLevelIDList(listId: List<Int>) {
        customListLevelIDs.addAll(listId)
        spinLevels()
    }

    fun spinLevels() {
        binding.spinLevels.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val selectedCountry = parent.getItemAtPosition(position) as String
                    var level_id = customListLevelIDs.get(position)
                    levelSelectedItem=level_id.toString()
                    Log.e("department response",
                        "levelSelectedItem :  $levelSelectedItem  ;  " +
                            "onItemSelected$selectedCountry")
                    getSections(level_id)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(context, "Error:no item selected ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

    }

    fun getSections(id: Int) {
//        var customListSectionsNames: MutableList<String> = mutableListOf()

        RetrofitClientAdmin.api.getSections(id).enqueue(object : Callback<SectionsResponse> {
            override fun onResponse(
                call: Call<SectionsResponse>,
                response: Response<SectionsResponse>
            ) {
                if (response.isSuccessful) {
                    sectionListIDs.clear()
                    var response = response.body()
                    var listName = response?.data?.map {
                        it.name
                    } ?: emptyList()
                    var lisId = response?.data?.map {
                        it.id
                    } ?: emptyList()
                    Log.e("department response", "list id section  : $lisId")
                    sectionListIDs.addAll(lisId)
                    Log.e("department response", "list id section sectionListIDs : $sectionListIDs")

                    sectionListNames.clear()
                    Log.e("department response", "sectionList  : $sectionListNames")

                    fillRecyclerView(listName)
                } else {
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("department response", "Error!")

                }
            }

            override fun onFailure(call: Call<SectionsResponse>, t: Throwable) {
                Toast.makeText(context, "Error: $t", Toast.LENGTH_SHORT)
                    .show()
                Log.e("department response", "Error : $t")
            }

        })
    }

    fun fillRecyclerView(listName: List<String>) {
        sectionListNames.clear()
        for (i in 0..listName.size - 1) {
            sectionListNames.add(Sections(listName[i], R.drawable.uncheck_black_checkbox))
        }
        if (sectionListNames.isNotEmpty()) {
            adapter = SectionsAdapter(sectionListNames, this)
            binding.rvSections.adapter = adapter
            binding.rvSections.layoutManager = LinearLayoutManager(requireActivity())

        }
    }

    override fun onItemClick(position: Int) {
        Log.e("department response", "sectionList  : $sectionListNames")
        Log.e("department response", "list id section  : $sectionListIDs")

        var itemClicked = sectionListNames[position]
        if (itemClicked.imgIsChecked == R.drawable.uncheck_black_checkbox) {
            listSelectedSectionIDs.add(sectionListIDs[position].toString())
            Log.e("department response", "listSelectedSectionIDs  : $listSelectedSectionIDs")
            itemClicked.imgIsChecked = R.drawable.check_black_checkbox
            adapter.notifyItemChanged(position)
        } else {
            var id = sectionListIDs[position]
            listSelectedSectionIDs.remove("$id")
            Log.e("department response", "listSelectedSectionIDs  : $listSelectedSectionIDs")

            itemClicked.imgIsChecked = R.drawable.uncheck_black_checkbox
            adapter.notifyItemChanged(position)
        }
    }

}

