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
import com.wiserax.zodiac.ui.birthdate.BirthDateFragmentDirections
import com.wiserax.zodiac.ui.birthdate.DateFragment
import com.wiserax.zodiac.ui.birthdate.DateViewModel
import com.wiserax.zodiac.ui.horoscope.HoroscopeFragmentDirections

class MainActivity : AppCompatActivity(), DateFragment.Callbacks, BirthDateFragment.Callbacks {

    private lateinit var binding: ActivityMainBinding
    private val dateViewModel: DateViewModel by viewModels()

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        navControllerDestinationChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        navView.itemIconTintList = null
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main);


        if (!prefs.isDateInit() && (navController.currentDestination?.id != R.id.navigation_birthdate)) {
            navController.navigate(HoroscopeFragmentDirections.actionHoroscopeToBirthdate())
        } else {
            supportFragmentManager.commit {
                replace(R.id.container_fragment_date, DateFragment())
            }
        }
        navControllerDestinationChanged()
        navView.setupWithNavController(navController)
    }

    private fun navControllerDestinationChanged() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_birthdate -> hideBars()
                R.id.navigation_compatibility -> hideTopBar()
                R.id.navigation_compatibility2 -> hideTopBar()
                else -> showBars()
            }
        }
    }

    private fun showBars() {
        binding.navView.visibility = View.VISIBLE
        with(supportFragmentManager) {
            this.commit { findFragmentById(R.id.container_fragment_date)?.let { show(it) } }
        }
    }

    private fun hideBars() {
        binding.navView.visibility = View.GONE
        hideTopBar()
    }

    private fun hideTopBar() {
        with(supportFragmentManager) {
            this.commit { findFragmentById(R.id.container_fragment_date)?.let { hide(it) } }
        }
    }

    override fun onDateButtonPressed() {
        findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_birthdate)
    }

    override fun onDateSet() {
        findNavController(R.id.nav_host_fragment_activity_main).navigate(BirthDateFragmentDirections.actionBirthdateToHoroscope())
        supportFragmentManager.commit {
            replace(R.id.container_fragment_date, DateFragment())
        }
    }
}