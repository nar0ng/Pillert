package com.example.pillert

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyPillFragment()
            1 -> PillAlarmFragment()
            2 -> SearchFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
