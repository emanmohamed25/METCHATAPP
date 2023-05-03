package com.example.chatapp.doctor.newchat.admin.createnewchate.ui
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentGroupeBinding
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.levelspinner.LevelsResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.response.responsedepartment.spinner.DepartmentSpinnerResponse
import com.example.chatapp.doctor.newchat.admin.createnewchate.ui.GroupeFragmentDirections.ActionGroupeFragmentToDataGroupFragment
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GroupeFragment : Fragment() {
    lateinit var binding: FragmentGroupeBinding
    var customListDepartmentIDs: MutableList<Int> = mutableListOf()
    var customListLevelIDs: MutableList<Int> = mutableListOf()
    lateinit var departmentSelectedItem: String
    lateinit var levelSelectedItem: String
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

        },3500 )
        binding = FragmentGroupeBinding.inflate(inflater, container, false)

        fillSpinnerDepartment(binding.spinDepartment)
        binding.ivNext.setOnClickListener { view: View ->
            Log.e(
                " click Button ",
                "departmentSelectedItem : $departmentSelectedItem\n levelSelectedItem : $levelSelectedItem"
            )

            view.findNavController().navigate(
                GroupeFragmentDirections.actionGroupeFragmentToDataGroupFragment(
                    departmentSelectedItem.toInt(),
                    levelSelectedItem.toInt()
                )
            )
//            view.findNavController()
//                .navigate(GroupeFragmentDirections.actionGroupeFragmentToDataGroupFragment())

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
                    levelSelectedItem = level_id.toString()
                    Log.e(
                        "department response",
                        "levelSelectedItem :  $levelSelectedItem  ;  " +
                                "onItemSelected$selectedCountry"
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(context, "Error:no item selected ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

    }

}