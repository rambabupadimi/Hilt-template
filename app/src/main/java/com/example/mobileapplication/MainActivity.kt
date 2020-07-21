package com.example.mobileapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.name
    lateinit var navController: NavController


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navController = Navigation.findNavController(this,R.id.fragment)
        bottomNavigationView.setBackgroundColor(getColor(R.color.colorPrimaryDark))
        NavigationUI.setupWithNavController(bottomNavigationView,navController)
    }
}