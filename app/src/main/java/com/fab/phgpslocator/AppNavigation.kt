package com.fab.phgpslocator

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fab.phgpslocator.components.FormDetail
//import com.fab.phgpslocator.viewModel.CoordinateViewModel
import com.fab.phgpslocator.viewModel.MapViewModel

@Composable
fun AppNavigation(navController: NavHostController, context: Context){

    val mapViewModel = MapViewModel()
    //val coordinateViewModel = CoordinateViewModel(context)

    NavHost(navController = navController, startDestination = Main.route) {
        composable(Main.route) { MainScreen() }
        composable(MapContainer.route) { MapScreen(mapViewModel) } //, coordinateViewModel
        composable(PositionGps.route) { PositionScreen() }
        composable(PhotoScreenDestination.route) { CameraAppScreen() }
        composable(BiometricDestination.route) { BiometricScreen() }
        composable(FormDestination.route) { FormDetail() {
            navController.navigate(Main.route)} }
    }

}