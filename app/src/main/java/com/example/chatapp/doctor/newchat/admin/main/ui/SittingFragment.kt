package com.example.chatapp.doctor.newchat.admin.main.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.chatapp.LoginScreenActivity
import com.example.chatapp.databinding.FragmentSittingBinding
import com.example.chatapp.doctor.newchat.admin.util.Constants.Companion.MY_SHARED


class SittingFragment : Fragment() {
   lateinit var binding :FragmentSittingBinding

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=FragmentSittingBinding.inflate(inflater,container,false)

        binding.ivBack.setOnClickListener{view:View->
            view.findNavController().navigate(SittingFragmentDirections.actionSittingFragmentToHomeFragment())
        }

        binding.ivLogout.setOnClickListener{
            val settings = context!!.getSharedPreferences(MY_SHARED, Context.MODE_PRIVATE)
            settings.edit().remove(MY_SHARED).commit()
            startActivity(Intent(context,LoginScreenActivity::class.java))
        }
        return binding.root
    }

}