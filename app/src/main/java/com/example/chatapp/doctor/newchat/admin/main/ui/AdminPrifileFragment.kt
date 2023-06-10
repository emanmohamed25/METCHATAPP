package com.example.chatapp.doctor.newchat.admin.main.ui

import android.content.Intent
import android.content.SharedPreferences

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.chatapp.HomepageActivity
import com.example.chatapp.databinding.FragmentAdminPrifileBinding
import com.example.chatapp.doctor.newchat.admin.util.Constants


class AdminPrifileFragment : Fragment() {
    lateinit var binding: FragmentAdminPrifileBinding
    var myshared: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAdminPrifileBinding.inflate(layoutInflater, container, false)
        myshared=requireActivity().getSharedPreferences(Constants.MY_SHARED,0)
        var editor: SharedPreferences.Editor = myshared!!.edit()
        Toast.makeText(context, "${myshared?.getString("adminName","").toString()}", Toast.LENGTH_SHORT)
            .show()
        Log.e("chat response","${myshared?.getString("adminName","").toString()}")
        binding.tvName.text= myshared?.getString("adminName","").toString()
binding.tvEmail.text=myshared?.getString("adminEmail","").toString()

        binding.ivBack.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(AdminPrifileFragmentDirections.actionAdminPrifileFragmentToHomeFragment())
        }

        binding.ivLogout.setOnClickListener {
            myshared = requireActivity().getSharedPreferences(Constants.MY_SHARED, 0)


            editor.remove("admintoken")
            editor.apply();
            startActivity(Intent(context, HomepageActivity::class.java))
            requireActivity().finish()
        }


        return binding.root
    }

}