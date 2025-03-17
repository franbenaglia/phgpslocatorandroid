package com.fab.phgpslocator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController){

    NavHost(navController = navController, startDestination = Main.route) {
        composable(Main.route) { MainScreen() }
        composable(MapContainer.route) { MapScreen() }
        composable(PositionGps.route) { PositionScreen() }
    }

}