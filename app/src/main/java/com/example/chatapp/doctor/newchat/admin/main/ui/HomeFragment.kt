package com.example.chatapp.doctor.newchat.admin.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentCreateNewChatBinding
import com.example.chatapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        binding.btnCreateNewChat.setOnClickListener{view:View->

            view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCreateNewChatFragment2())

        }



        return binding.root
    }

}