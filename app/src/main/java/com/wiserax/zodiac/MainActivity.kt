package com.wiserax.zodiac

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wiserax.zodiac.databinding.ActivityMainBinding
import com.wiserax.zodiac.ui.birthdate.DateFragment

class MainActivity : AppCompatActivity(), DateFragment.Callbacks {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit {
            replace(R.id.container_fragment_date, DateFragment())
        }

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        if (!prefs.getDateInitFlag()) {
            navController.navigate(R.id.action_horoscope_to_birthdate)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_birthdate -> hideBars()
                R.id.navigation_compatibility -> hideBars()
                else -> showBars()
            }
        }
        navView.setupWithNavController(navController)
    }


    private fun showBars() {
        binding.navView.visibility = View.VISIBLE
        with(supportFragmentManager) {
            this.commit { findFragmentById(R.id.container_fragment_date)?.let { show(it) } }
        }
    }

    private fun hideBars() {
        binding.navView.visibility = View.GONE
        with(supportFragmentManager) {
            this.commit { findFragmentById(R.id.container_fragment_date)?.let { hide(it) } }
        }
    }

    override fun onDateButtonPressed() {
        findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_birthdate)
    }
}