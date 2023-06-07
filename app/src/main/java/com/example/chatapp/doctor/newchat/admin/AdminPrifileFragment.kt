package com.example.chatapp.doctor.newchat.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentAdminPrifileBinding


class AdminPrifileFragment : Fragment() {
   lateinit var binding :FragmentAdminPrifileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentAdminPrifileBinding.inflate(layoutInflater,container,false)



        return binding.root
    }

}