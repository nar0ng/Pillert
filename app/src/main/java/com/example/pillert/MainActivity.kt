package com.example.pillert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.example.pillert.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
        private lateinit var toolbar: Toolbar
        private lateinit var viewPager: ViewPager2
        private lateinit var bottomNav: BottomNavigationView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // 툴바 설정
            toolbar = binding.toolbar
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false) // 프래그먼트 이름 표시 제거

            // ViewPager 설정
            viewPager = binding.viewPager
            val pagerAdapter = MainPagerAdapter(this)
            viewPager.adapter = pagerAdapter


            // bottomNav 설정
            bottomNav = binding.bottomNavigationView
            bottomNav.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.navigation_page1 -> {
                        viewPager.setCurrentItem(0, false)
                        true
                    }
                    R.id.navigation_page2 -> {
                        viewPager.setCurrentItem(1, false)
                        true
                    }
                    R.id.blank -> {
                        viewPager.setCurrentItem(2, false)
                        true
                    }
                    R.id.navigation_page3 -> {
                        viewPager.setCurrentItem(3, false)
                        true
                    }
                    R.id.navigation_page4 -> {
                        viewPager.setCurrentItem(4, false)
                        true
                    }
                    else -> false
                }
            }

        }

        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_menu, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.menu_item1 -> {
                    // 메뉴 아이템 1 클릭 시 동작
                    return true
                }
                R.id.menu_item2 -> {
                    // 메뉴 아이템 2 클릭 시 동작
                    return true
                }
                else -> return super.onOptionsItemSelected(item)
            }
        }

        class MainPagerAdapter(fragmentActivity: AppCompatActivity) :
            FragmentStateAdapter(fragmentActivity) {

            override fun getItemCount(): Int {
                return 5
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> MyPillFragment()
                    1 -> PillAlarmFragment()
                    2 -> MyPillFragment()
                    3 -> MapFragment()
                    4-> SettingsFragment()
                    else -> throw IllegalArgumentException("Invalid position: $position")
                }
            }

            override fun onBindViewHolder(
                holder: FragmentViewHolder,
                position: Int,
                payloads: MutableList<Any>
            ) {
                super.onBindViewHolder(holder, position, payloads)
                Log.d("MainPagerAdapter", "onBindViewHolder - position: $position")
            }

        }
    

    }
