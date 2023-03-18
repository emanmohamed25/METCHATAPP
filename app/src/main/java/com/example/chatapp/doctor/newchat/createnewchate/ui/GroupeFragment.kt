package com.example.chatapp.doctor.newchat.createnewchate.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentGroupeBinding

class GroupeFragment : Fragment() {
   lateinit var binding: FragmentGroupeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentGroupeBinding.inflate(inflater,container,false)

        binding.ivNext.setOnClickListener{ view: View ->
            view.findNavController()
                .navigate(com.example.chatapp.doctor.newchat.ui.GroupeFragmentDirections.actionGroupeFragmentToDataGroupFragment())

        }
        return binding.root
    }

}