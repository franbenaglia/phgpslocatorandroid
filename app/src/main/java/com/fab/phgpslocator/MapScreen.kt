package com.fab.phgpslocator

import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.fab.phgpslocator.components.SearchBar
import com.fab.phgpslocator.viewModel.MapViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

//https://medium.com/@ridvanozcan48/how-to-use-google-maps-in-jetpack-compose-step-by-step-android-guide-55aedac89e43
//https://medium.com/@karollismarmokas/integrating-google-maps-in-android-with-jetpack-compose-user-location-and-search-bar-a432c9074349
//https://github.com/Karoliukas29/GoogleMapsJetpackCompose
@Composable
fun MapScreen(mapViewModel: MapViewModel) {

    val atasehir = LatLng(40.9971, 29.1007)

    val atasehirb = LatLng(40.9071, 29.2007)

    val atasehirc = LatLng(
        40.9571,
        29.1507
    )// Initialize the camera position state, which controls the camera's position on the map

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(atasehir, 15f)
    }
    // Obtain the current context
    val context = LocalContext.current

    // Observe the user's location from the ViewModel
    val userLocation by mapViewModel.userLocation

    // Observe the selected location from the ViewModel
    val selectedLocation by mapViewModel.selectedLocation

    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var uiSettings by remember {
        mutableStateOf(MapUiSettings(zoomControlsEnabled = true, scrollGesturesEnabled = true))
    }
    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
    }


    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
    ) {


        userLocation?.let {
            Marker(
                state = MarkerState(position = it), // Place the marker at the user's location
                title = "Your Location", // Set the title for the marker
                snippet = "This is where you are currently located." // Set the snippet for the marker
            )
            // Move the camera to the user's location with a zoom level of 10f
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 10f)
        }

        // If a location was selected from the search bar, place a marker there
        selectedLocation?.let {
            Marker(
                state = MarkerState(position = it), // Place the marker at the selected location
                title = "Selected Location", // Set the title for the marker
                snippet = "This is the place you selected." // Set the snippet for the marker
            )
            // Move the camera to the selected location with a zoom level of 15f
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
        }

        Marker(
            state = MarkerState(position = atasehir),
            title = "One Marker"
        )


        MarkerComposable(
            state = MarkerState(position = atasehirb),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "custom marker",
            )
        }

        MarkerInfoWindow(
            state = MarkerState(position = atasehirc),
            //icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_foreground)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "custom marker",
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        RoundedCornerShape(10)
                    )
                    .clip(RoundedCornerShape(10))
                    .background(Color.Blue)
                    .padding(20.dp)
            ) {
                Text("Title", fontWeight = FontWeight.Bold, color = Color.White)
                Text("Description", fontWeight = FontWeight.Medium, color = Color.White)
            }
        }

    }

    // Layout that includes the search bar and the map, arranged in a vertical column
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(1.dp)) // Add a spacer with a height of 18dp to push the search bar down

        // Add the search bar component

        SearchBar(
            onPlaceSelected = { place ->
                // When a place is selected from the search bar, update the selected location
                mapViewModel.selectLocation(place, context)
            }
        )
    }

    // Handle permission requests for accessing fine location
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Fetch the user's location and update the camera if permission is granted
            mapViewModel.fetchUserLocation(context, fusedLocationClient)
        } else {
            // Handle the case when permission is denied
            Log.v("MapScreen", "Location permission was denied by the user.")
        }
    }

// Request the location permission when the composable is launched
    LaunchedEffect(Unit) {
        when (PackageManager.PERMISSION_GRANTED) {
            // Check if the location permission is already granted
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                // Fetch the user's location and update the camera
                mapViewModel.fetchUserLocation(context, fusedLocationClient)
            }

            else -> {
                // Request the location permission if it has not been granted
                permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

}