package com.example.currencytracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.currencytracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView)  //navHostFragment: This retrieves the NavHostFragment by its ID (R.id.fragmentContainerView).
        // The NavHostFragment is a container that manages navigation within the app.

        val navController=navHostFragment!!.findNavController() //NavController is responsible for handling navigation within a NavHost

        //from documnetation as we are using smooth bottom bar, hence can't use traditional method of navigation:


        val popupMenu=PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_nav_menu)
        binding.bottomBar.setupWithNavController(popupMenu.menu, navController)






    }



}