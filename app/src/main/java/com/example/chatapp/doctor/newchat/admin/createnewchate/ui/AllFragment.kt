package com.example.chatapp.doctor.newchat.admin.createnewchate.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentAll2Binding
import com.example.chatapp.databinding.FragmentHomeBinding

class AllFragment : Fragment() {

  lateinit var binding: FragmentAll2Binding

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
        // Inflate the layout for this fragment
        binding=FragmentAll2Binding.inflate(layoutInflater,container,false)


        return  binding.root
    }

}