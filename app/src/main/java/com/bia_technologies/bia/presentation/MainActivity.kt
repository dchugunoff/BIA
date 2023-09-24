package com.bia_technologies.bia.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.bia_technologies.bia.R
import com.bia_technologies.bia.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavMenu: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomNavMenu = binding.bottomNavMenu
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_item_task -> {
                    navController.navigate(R.id.taskFragment)
                    true
                }

                R.id.menu_item_graphics -> {
                    navController.navigate(R.id.scheduleFragment)
                    true
                }

                R.id.menu_item_chat -> {
                    navController.navigate(R.id.chatFragment)
                    true
                }

                R.id.menu_item_profile -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }

                else -> false
            }
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.taskFragment -> {
                    bottomNavMenu.visibility = View.VISIBLE
                }

                R.id.chatFragment -> {
                    bottomNavMenu.visibility = View.VISIBLE
                }

                R.id.profileFragment -> {
                    bottomNavMenu.visibility = View.VISIBLE
                }

                R.id.scheduleFragment -> {
                    bottomNavMenu.visibility = View.VISIBLE
                }

                else -> {
                    bottomNavMenu.visibility = View.GONE
                }
            }
        }
    }


}