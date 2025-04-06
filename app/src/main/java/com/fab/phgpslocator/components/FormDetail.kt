package com.fab.phgpslocator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.fab.phgpslocator.AppDestination
import com.fab.phgpslocator.Main
import com.fab.phgpslocator.PositionGps
import com.fab.phgpslocator.viewModel.DatabaseViewModel
import com.fab.phgpslocator.viewModel.FormViewModel
//https://stackoverflow.com/questions/79100518/navigation-graph-has-not-been-set-for-navcontroller-compose
//https://stackoverflow.com/questions/77516144/jetpack-compose-error-navigation-graph-has-not-been-set-for-navcontroller
@Composable
fun FormDetail(
    mainViewModel: FormViewModel = viewModel(),
    //databaseViewModel: DatabaseViewModel = viewModel(),
    onCancelSelect: (name: String) -> Unit
) {
    val mainUiState by mainViewModel.uiState.collectAsState()
    val navController = rememberNavController()
    //val database by databaseViewModel.photoDetail.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = mainUiState.title,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { mainViewModel.updateTitle(it) },
            label = { Text(text = "Enter the title") },
            isError = mainUiState.titleErrors.isNotEmpty()
        )
        mainUiState.titleErrors.forEach {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = it,
                color = Color.Red
            )
        }

        Spacer(Modifier.height(12.dp))

        TextField(
            value = mainUiState.description,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { mainViewModel.updateDescription(it) },
            label = { Text(text = "Enter the description") },
            isError = mainUiState.descriptionErrors.isNotEmpty()
        )
        mainUiState.descriptionErrors.forEach {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = it,
                color = Color.Red
            )
        }

        Spacer(Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .padding(16.dp)
                    ,
                shape = RoundedCornerShape(8.dp),
                enabled = true,
                contentPadding = PaddingValues(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ) {
                Text(text = "Save")
            }
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .padding(16.dp)
                    ,
                shape = RoundedCornerShape(8.dp),
                enabled = true,
                contentPadding = PaddingValues(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text(text = "Take Photo")
            }

            Button(
                onClick =  { onCancelSelect(Main.route) },
                //onClick = { navController.navigate(Main.route) },
                modifier = Modifier
                    .padding(16.dp)
                ,
                shape = RoundedCornerShape(8.dp),
                enabled = true,
                contentPadding = PaddingValues(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text(text = "Cancel")
            }
        }


    }

}