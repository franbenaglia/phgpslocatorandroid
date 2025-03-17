package com.fab.phgpslocator

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MapScreen(
    modifier: Modifier = Modifier
) {
    Log.v("MAPSCREEENNNN", "MAPSCREEENNN")
    Text(
        text = "Hello map screen!",
        modifier = modifier
    )
}