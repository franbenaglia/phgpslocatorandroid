package com.fab.phgpslocator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import com.fab.phgpslocator.viewModel.MapViewModel
import com.fab.phgpslocator.components.FormDetail
//import com.fab.phgpslocator.viewModel.CoordinateViewModel

interface AppDestination {
    val icon: ImageVector
    val route: String
    val label: String
    val screen: @Composable () -> Unit
}

object MapContainer : AppDestination {
    private val mapViewModel = MapViewModel()
    //private val coordinateViewModel = CoordinateViewModel()
    override val icon = Icons.Filled.Add
    override val route = "map"
    override val label = "Map"
    override val screen: @Composable () -> Unit = { MapScreen(mapViewModel) } //, CoordinateViewModel(LocalContext.current)
}

object PositionGps : AppDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "position"
    override val label = "GPS Position"
    override val screen: @Composable () -> Unit = { PositionScreen() }
}

object PhotoScreenDestination : AppDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "photo"
    override val label = "Photo"
    override val screen: @Composable () -> Unit = { CameraAppScreen() }
}

object Main : AppDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "main"
    override val label = "Main"
    override val screen: @Composable () -> Unit = { MainScreen() }
}

object FormDestination : AppDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "form"
    override val label = "Form Detail"
    override val screen: @Composable () -> Unit = { FormDetail(){
    }
    }
}

object BiometricDestination : AppDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "biometric"
    override val label = "Biometric Test"
    override val screen: @Composable () -> Unit = { BiometricScreen()
    }
}

val appMenuRowScreens = listOf(PositionGps, MapContainer, Main, BiometricDestination)
