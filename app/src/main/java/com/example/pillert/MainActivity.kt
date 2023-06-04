package com.example.pillert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.pillert.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator



    class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
        private lateinit var toolbar: Toolbar
        private lateinit var viewPager: ViewPager2
        private lateinit var tabLayout: TabLayout

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

            // TabLayout 설정
            tabLayout = binding.tabLayout
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = pagerAdapter.getPageTitle(position)
            }.attach()
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

        inner class MainPagerAdapter(fragmentActivity: AppCompatActivity) :
            FragmentStateAdapter(fragmentActivity) {

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

            fun getPageTitle(position: Int): String {
                return when (position) {
                    0 -> "나의 약"
                    1 -> "복약 알림"
                    2 -> "약 검색"
                    else -> throw IllegalArgumentException("Invalid position: $position")
                }
            }
        }
    }
