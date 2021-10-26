package com.wiserax.zodiac

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wiserax.zodiac.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        if (!prefs.getDateInitFlag()) {
            Log.d("lol", prefs.getFullDate().toString() + " " + prefs.sex)
            navController.navigate(R.id.action_horoscope_to_birthdate)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_birthdate -> hideBottomNav()
                else -> showBottomNav()
            }
        }
        navView.setupWithNavController(navController)
    }


    private fun showBottomNav() {
        binding.navView.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        binding.navView.visibility = View.GONE
    }
}