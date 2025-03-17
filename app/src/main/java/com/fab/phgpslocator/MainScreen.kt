package com.fab.phgpslocator

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    Log.v("MAinSCREEENNNN", "MaiunSCREEENNN")
    Text(
        text = "Hello main screen!",
        modifier = modifier
    )
}