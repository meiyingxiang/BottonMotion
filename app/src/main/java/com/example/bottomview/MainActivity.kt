package com.example.bottomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.account_icon_layout.*
import kotlinx.android.synthetic.main.content_icon_layout.*
import kotlinx.android.synthetic.main.explore_icon_layout.*
import kotlinx.android.synthetic.main.message_icon_layout.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

        val destinationMap = mapOf(
            R.id.messageFragment to messageMotion,
            R.id.accountFragment to accountMotion,
            R.id.contactFragment to contentMotion,
            R.id.exploreFragment to exploreMotion
        )
//        val set = setOf(R.id.messageFragment,R.id.accountFragment,R.id.contactFragment,R.id.exploreFragment)
        appBarConfiguration = AppBarConfiguration(destinationMap.keys)
        setupActionBarWithNavController(navController, appBarConfiguration)

        destinationMap.forEach { map ->
            map.value.setOnClickListener {
                navController.navigate(map.key)
            }
        }

//        messageMotion.setOnClickListener {
//            navController.navigate(R.id.messageFragment)
//        }
//        accountMotion.setOnClickListener {
//            navController.navigate(R.id.accountFragment)
//        }
//        contentMotion.setOnClickListener {
//            navController.navigate(R.id.contactFragment)
//        }
//        exploreMotion.setOnClickListener {
//            navController.navigate(R.id.exploreFragment)
//        }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            //将返回栈清空
            controller.popBackStack()
            destinationMap.values.forEach { it.progress = 0.1f }
//            messageMotion.progress = 0.1f
//            accountMotion.progress = 0.1f
//            contentMotion.progress = 0.1f
//            exploreMotion.progress = 0.1f
            destinationMap[destination.id]?.transitionToEnd()
//            when (destination.id) {
//                R.id.messageFragment -> messageMotion.transitionToEnd()
//                R.id.accountFragment -> accountMotion.transitionToEnd()
//                R.id.contactFragment -> contentMotion.transitionToEnd()
//                R.id.exploreFragment -> exploreMotion.transitionToEnd()
//            }
        }
    }
}