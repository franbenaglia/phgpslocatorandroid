package com.fab.phgpslocator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fab.phgpslocator.components.FormDetail
import com.fab.phgpslocator.viewModel.MapViewModel

@Composable
fun AppNavigation(navController: NavHostController){

    val mapViewModel = MapViewModel()

    NavHost(navController = navController, startDestination = Main.route) {
        composable(Main.route) { MainScreen() }
        composable(MapContainer.route) { MapScreen(mapViewModel) }
        composable(PositionGps.route) { PositionScreen() }
        composable(FormDestination.route) { FormDetail() {
            navController.navigate(Main.route)} }
    }

}