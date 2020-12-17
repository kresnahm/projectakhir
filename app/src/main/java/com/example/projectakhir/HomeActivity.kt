package com.example.projectakhir

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            ListFragment()
        ).commit()
    }
    private val navListener: BottomNavigationView.OnNavigationItemSelectedListener =
        object : OnNavigationItemSelectedListener() {
            fun onNavigationItemSelected(@NonNull menuItem: MenuItem): Boolean {
                var selectedFragment: Fragment? = null
                when (menuItem.itemId) {
                    R.id.nav_home -> selectedFragment = HomeFragment()
                    R.id.nav_courses -> selectedFragment = CoursesFragment()
                    R.id.nav_profile -> selectedFragment = ProfileFragment()
                    R.id.nav_event -> selectedFragment = EventsFragment()
                }
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    selectedFragment
                ).commit()
                return true
            }
        }
}