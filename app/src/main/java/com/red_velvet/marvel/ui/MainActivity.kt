package com.red_velvet.marvel.ui

import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.ActivityMainBinding
import com.red_velvet.marvel.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    override val LOG_TAG: String = "MainActivity"

    override fun getLayoutResId() = R.layout.activity_main

    private val navController by lazy { findNavController(R.id.nav_host_fragment_content_main) }
    override fun setup() {
        setSupportActionBar(binding.toolbar)
        hideActionBar()
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.contentMain.bottomNav.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        return true
    }

    private fun hideActionBar() {
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            when (destination.id) {
                R.id.comicsFragment,
                R.id.charactersFragment,
                R.id.seriesFragment,
                R.id.eventsFragment, R.id.storiesFragment -> {
                    this.supportActionBar?.hide()
                }

                else -> {
                    supportActionBar?.show()
                }
            }
        }
    }
}