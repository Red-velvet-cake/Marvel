package com.red_velvet.marvel.ui


import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.ActivityMainBinding
import com.red_velvet.marvel.ui.base.BaseActivity
import com.red_velvet.marvel.ui.utils.hideView
import com.red_velvet.marvel.ui.utils.showView

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val LOG_TAG: String = "MainActivity"

    override fun getLayoutResId() = R.layout.activity_main

    private val navController by lazy { findNavController(R.id.nav_host_fragment_content_main) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        initNavigationDestinationListener()
        setupActionBarWithNavController(this, navController)
        binding.contentMain.bottomNav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment_content_main).navigateUp()
                || super.onSupportNavigateUp()
    }

    private fun initNavigationDestinationListener() {
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            when (destination.id) {
                R.id.comicsFragment,
                R.id.charactersFragment,
                R.id.seriesFragment,
                R.id.eventsFragment,
                R.id.storiesFragment -> {
                    this.supportActionBar?.hide()
                    binding.contentMain.bottomNav.showView()
                }

                else -> {
                    binding.contentMain.bottomNav.hideView()
                    supportActionBar?.show()
                }
            }
        }
    }
}