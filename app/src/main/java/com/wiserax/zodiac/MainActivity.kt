package com.wiserax.zodiac

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wiserax.zodiac.databinding.ActivityMainBinding
import com.wiserax.zodiac.ui.DateFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            replace(R.id.fragment_date, DateFragment())
        }

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        if (!prefs.getDateInitFlag()) {
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