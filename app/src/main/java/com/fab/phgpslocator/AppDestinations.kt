package com.fab.phgpslocator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

interface AppDestination {
    val icon: ImageVector
    val route: String
    val label: String
    val screen: @Composable () -> Unit
}

object MapContainer : AppDestination {
    override val icon = Icons.Filled.Add
    override val route = "map"
    override val label = "Map"
    override val screen: @Composable () -> Unit = { MapScreen() }
}

object PositionGps : AppDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "position"
    override val label = "GPS Position"
    override val screen: @Composable () -> Unit = { PositionScreen() }
}

object Main : AppDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "main"
    override val label = "Main"
    override val screen: @Composable () -> Unit = { MainScreen() }
}

val appMenuRowScreens = listOf(PositionGps, MapContainer, Main)
