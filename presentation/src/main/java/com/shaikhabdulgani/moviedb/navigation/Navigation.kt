package com.shaikhabdulgani.moviedb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.shaikhabdulgani.moviedb.screens.detail.DetailScreen
import com.shaikhabdulgani.moviedb.screens.home.HomeScreen

@Composable
fun AppNavigation() {
    val controller = rememberNavController()
    NavHost(navController = controller, startDestination = Screens.Home) {
        composable<Screens.Home> {
            HomeScreen(controller)
        }
        composable<Screens.Detail> {
            val screen = it.toRoute<Screens.Detail>()
            DetailScreen(controller, screen.id)
        }
    }
}

