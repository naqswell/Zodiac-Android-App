package com.wiserax.zodiac

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wiserax.zodiac.databinding.ActivityMainBinding
import com.wiserax.zodiac.ui.birthdate.BirthDateFragment
import com.wiserax.zodiac.ui.birthdate.DateFragment
import com.wiserax.zodiac.ui.birthdate.DateViewModel

class MainActivity : AppCompatActivity(), DateFragment.Callbacks, BirthDateFragment.Callbacks {

    private lateinit var binding: ActivityMainBinding
    private val dateViewModel: DateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_birthdate -> hideBars()
                R.id.navigation_compatibility -> hideBars()
                R.id.action_horoscope_to_birthdate -> hideBars()
                else -> showBars()
            }
        }

        if (!prefs.isDateInit()) {
            navController.navigate(R.id.action_horoscope_to_birthdate)
        } else {
            supportFragmentManager.commit {
                replace(R.id.container_fragment_date, DateFragment())
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

    override fun onDateSet() {
        supportFragmentManager.commit {
            replace(R.id.container_fragment_date, DateFragment())
        }
    }
}