package com.example.tema3dam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.tema3dam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.bottomNavigation.menu.findItem(R.id.homeFragment).setOnMenuItemClickListener {

            if(!binding.hostFragment.findNavController().popBackStack(R.id.homeFragment,false)){
                binding.hostFragment.findNavController().navigate(R.id.homeFragment)
            }
            true
        }

        binding.bottomNavigation.menu.findItem(R.id.emailFragment).setOnMenuItemClickListener {

            if(!binding.hostFragment.findNavController().popBackStack(R.id.emailFragment,false)){
                binding.hostFragment.findNavController().navigate(R.id.emailFragment)
            }
            true
        }

        binding.bottomNavigation.menu.findItem(R.id.historyFragment).setOnMenuItemClickListener {

            if(!binding.hostFragment.findNavController().popBackStack(R.id.historyFragment,false)){
                binding.hostFragment.findNavController().navigate(R.id.historyFragment)
            }
            true
        }

        binding.bottomNavigation.menu.findItem(R.id.logsFragment).setOnMenuItemClickListener {

            if(!binding.hostFragment.findNavController().popBackStack(R.id.logsFragment,false)){
                binding.hostFragment.findNavController().navigate(R.id.logsFragment)
            }
            true
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment.navController)
    }
}