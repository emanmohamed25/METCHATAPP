package com.example.chatapp.doctor.newchat.admin.createnewchate.ui

import android.app.Activity
import android.app.AlertDialog
import com.example.chatapp.R

class LoadingDialog(val mActivity: Activity) {
    private lateinit var isdialog:AlertDialog
    fun startLoading(){
        //set view
        val infalter=mActivity.layoutInflater
        val dialogView = infalter.inflate(R.layout.custom_dialod,null)
        //set dialog
        val bulider =AlertDialog.Builder(mActivity)
        bulider.setView(dialogView)
        bulider.setCancelable(false)
        isdialog=bulider.create()
        isdialog.show()
    }
    fun isDismiss(){
        isdialog.dismiss()
    }
}