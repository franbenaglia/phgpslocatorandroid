package com.fab.phgpslocator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.fab.phgpslocator.ui.theme.PhGPSLocatorTheme
import com.fab.phgpslocator.viewModel.DatabaseViewModel
import com.google.android.libraries.places.api.Places
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() { //ComponentActivity()

   // @Inject
   // lateinit var databaseViewModel: DatabaseViewModel

    //val databaseViewModel = ViewModelProvider(this)[DatabaseViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the API key from the manifest file
        val apiKey = ManifestUtils.getApiKeyFromManifest(this)
        // Initialize the Places API with the retrieved API key
        if (!Places.isInitialized() && apiKey != null) {
            Places.initialize(applicationContext, apiKey)
        }

        enableEdgeToEdge()
        setContent {
            PhGPSLocatorTheme {
                NavigationDrawer()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhGPSLocatorTheme {
        NavigationDrawer()
    }
}