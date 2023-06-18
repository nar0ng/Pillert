package com.example.pillert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.example.pillert.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var viewPager: ViewPager2
    private lateinit var bottomNav: BottomNavigationView

    private lateinit var MyPillFragment: MyPillFragment
    private lateinit var PillAlarmFragment: PillAlarmFragment
    private lateinit var SearchFragment: SearchFragment
    private lateinit var SettingsFragment: SettingsFragment

    private lateinit var addButton: FloatingActionButton
    private lateinit var firebaseFirestore: FirebaseFirestore

    var mod = "myPill"
    var authMenuItem: MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프레그먼트 초기화
        MyPillFragment = MyPillFragment()
        PillAlarmFragment = PillAlarmFragment()
        SearchFragment = SearchFragment()
        SettingsFragment = SettingsFragment()

        // 툴바 설정
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 프래그먼트 이름 표시 제거

        // ViewPager 설정
        viewPager = binding.viewPager
        viewPager.adapter = MainPagerAdapter(this)

        // 처음 시작할 때 발리 프레그먼트를 보여주겠다
        supportActionBar?.title = "MyPill"

        firebaseFirestore = FirebaseFirestore.getInstance()

        // bottomNav 설정
        bottomNav = binding.bottomNavigationView
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_page1 -> {
                    viewPager.setCurrentItem(0, false)
                    Log.d("MainActivity", "Selected Fragment: MyPillFragment")
                    true
                }
                R.id.navigation_page2 -> {
                    viewPager.setCurrentItem(1, false)
                    Log.d("MainActivity", "Selected Fragment: PillAlarmFragment")
                    true
                }
                R.id.blank -> {
                    viewPager.setCurrentItem(2, false)
                    Log.d("MainActivity", "Selected Fragment: BlankFragment")
                    true
                }
                R.id.navigation_page3 -> {
                    viewPager.setCurrentItem(3, false)
                    Log.d("MainActivity", "Selected Fragment: SearchFragment")
                    true
                }
                R.id.navigation_page4 -> {
                    viewPager.setCurrentItem(4, false)
                    Log.d("MainActivity", "Selected Fragment: SettingsFragment")
                    true
                }
                else -> false
            }
        }

        addButton = findViewById<Button>(R.id.addButton) as FloatingActionButton
        addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

//        // onStart에서 실행될 때 초기화가 안 되었다라는 오류
//        authMenuItem = menu!!.findItem(R.id.menu_auth)
//        if (PillertApplication.checkAuth() == true) {
//            authMenuItem!!.title = "${PillertApplication.email}님"
//        } else {
//            authMenuItem!!.title = "인증"
//        }
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onStart() {
//        // Intent에서 finish() 된 다음에 돌아올 때 실행
//        // 앱 처음 실행될 때
//        // onCreate -> onStart -> onCreateOptionsMenu
//        super.onStart()
//        if (authMenuItem != null) {
//            if (PillertApplication.checkAuth() == true) {
//                authMenuItem!!.title = "${PillertApplication.email}님"
//            } else {
//                authMenuItem!!.title = "인증"
//            }
//        }
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                viewPager.setCurrentItem(3, false)
                Log.d("MainActivity", "Selected Fragment: SearchFragment")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    class MainPagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int {
            return 5
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> MyPillFragment()
                1 -> PillAlarmFragment()
                2 -> BlankFragment()
                3 -> SearchFragment()
                4 -> SettingsFragment()
                else -> throw IllegalArgumentException("Invalid position: $position")
            }
        }


    }
}
