/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fab.phgpslocator

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


/**
 * Contract for information needed on every Rally navigation destination
 */
interface AppDestination {
    val icon: ImageVector
    val route: String
    val screen: @Composable () -> Unit
}

/**
 * Rally app navigation destinations
 */
object MapContainer : AppDestination {
    override val icon = Icons.Filled.Add
    override val route = "map"
    override val screen: @Composable () -> Unit = { MapScreen() }
}

object PositionGps : AppDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "position"
    override val screen: @Composable () -> Unit = { PositionScreen() }
}

object Main : AppDestination {
    override val icon = Icons.Filled.AccountCircle
    override val route = "main"
    override val screen: @Composable () -> Unit = { MainScreen() }
}

// Screens to be displayed in the top RallyTabRow
val appMenuRowScreens = listOf(PositionGps, MapContainer)
