package com.example.chatapp.doctor.newchat.admin.createnewchate.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.doctor.newchat.admin.createnewchate.data.Students
import com.example.chatapp.doctor.newchat.network.RetrofitClientAdmin

class DataGroupViewModel : ViewModel() {

    var studentListMutableLiveData=MutableLiveData<List<Students>>()


//    public fun getStudents(){
//        RetrofitClientAdmin.api.sendDataGroupMessage()
//
//    }

}