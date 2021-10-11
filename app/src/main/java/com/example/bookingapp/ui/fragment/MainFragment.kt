package com.example.bookingapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.PrimaryKey
import com.example.bookingapp.R
import com.example.bookingapp.databinding.ActivityMainBinding
import com.example.bookingapp.databinding.FragmentMainBinding
import com.example.bookingapp.ui.base.adapter.bindImage

class MainFragment : Fragment() {

    private lateinit var mainBinding : FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainBinding = FragmentMainBinding.inflate(inflater)
        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainBinding.btnHome.setOnClickListener { navController(R.id.action_mainFragment_to_homeFragment) }
        mainBinding.btnList.setOnClickListener { navController(R.id.action_mainFragment_to_homeFragment) }
        mainBinding.btnLocation.setOnClickListener { navController(R.id.action_mainFragment_to_mapsFragment) }
        mainBinding.btnOnBoarding.setOnClickListener { navController(R.id.action_mainFragment_to_onBoardFragment) }
        mainBinding.btnSignIn.setOnClickListener { navController(R.id.action_mainFragment_to_signInFragment) }
        mainBinding.btnSignUp.setOnClickListener { navController(R.id.action_mainFragment_to_signInFragment) }
    }

    private fun navController(resId : Int) {
        findNavController().navigate(resId)
    }
}