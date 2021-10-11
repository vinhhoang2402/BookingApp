package com.example.bookingapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookingapp.R
import com.example.bookingapp.databinding.FragmentBoardingBinding
import com.example.bookingapp.ui.adapter.OnBoardingPagerAdapter
import com.example.bookingapp.ui.animation.DepthPageTransformer
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_boarding.*

class OnBoardFragment : Fragment() {

    private lateinit var homeBinding: FragmentBoardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = FragmentBoardingBinding.inflate(inflater)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewPager.adapter = ChildFragmentStateAdapter(this)
        viewPager.adapter = OnBoardingPagerAdapter()
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->

        }.attach()

        viewPager.setPageTransformer(DepthPageTransformer())
    }
}