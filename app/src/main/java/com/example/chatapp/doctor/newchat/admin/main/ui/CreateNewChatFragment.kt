package com.example.chatapp.doctor.newchat.admin.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.FragmentCreateNewChatBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_create_new_chat.*


class CreateNewChatFragment : Fragment() {
lateinit var navController: NavController
lateinit var binding:FragmentCreateNewChatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCreateNewChatBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navView: BottomNavigationView = binding.bottomNavigationView
            //view.findViewById(R.id.bottomNavigationView)
        navController = findNavController()
        navView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {   menuItem ->
            when (menuItem.itemId) {
                R.id.departmentFragment -> {
                    Toast.makeText(context, "departmentFragment", Toast.LENGTH_LONG)
                        .show()
                    binding.fragmentContainerViewNewChat.findNavController().navigate(R.id.departmentFragment)
                    true
                }
                R.id.groupeFragment -> {
                    Toast.makeText(context, "groupeFragment", Toast.LENGTH_LONG)
                        .show()
                    binding.fragmentContainerViewNewChat.findNavController().navigate(R.id.groupeFragment)
                    true
                }R.id.allFragment -> {
                Toast.makeText(context, "allFragment", Toast.LENGTH_LONG)
                    .show()
                binding.fragmentContainerViewNewChat.findNavController().navigate(R.id.allFragment)
                true
            }R.id.stuffFragment -> {
                Toast.makeText(context, "stuffFragment", Toast.LENGTH_LONG)
                    .show()
                binding.fragmentContainerViewNewChat.findNavController().navigate(R.id.stuffFragment)
                true
            }
                else -> false
            }
        }

    }
}