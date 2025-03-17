package com.fab.phgpslocator

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PositionScreen(
    modifier: Modifier = Modifier
) {
    Log.v("POSITIONSCREEEENN", "POSITIONSCREEEEEN")
    Text(
        text = "Hello position screen!",
        modifier = modifier
    )
}