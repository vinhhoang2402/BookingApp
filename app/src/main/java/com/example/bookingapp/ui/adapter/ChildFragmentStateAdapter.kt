package com.example.bookingapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bookingapp.ui.fragment.*

class ChildFragmentStateAdapter(private val fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> SignInFragment()
            2 -> Home3Fragment()
            3 -> Home4Fragment()
            4 -> Home5Fragment()
            else -> OnBoardFragment()
        }
    }

}