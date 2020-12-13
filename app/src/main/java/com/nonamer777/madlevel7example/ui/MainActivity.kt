package com.nonamer777.madlevel7example.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.nonamer777.madlevel7example.R
import com.nonamer777.madlevel7example.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    companion object {

        var actionBar: ActionBar? = null
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        MainActivity.actionBar = supportActionBar
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        android.R.id.home -> {
            findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_createQuizFragment_to_homeFragment)

            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
