package com.fab.phgpslocator.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fab.phgpslocator.viewModel.DatabaseViewModel
import com.fab.phgpslocator.viewModel.FormViewModel

@Composable
fun FormDetail(
    mainViewModel: FormViewModel = viewModel()
) {//,  databaseViewModel: DatabaseViewModel = viewModel()
    val mainUiState by mainViewModel.uiState.collectAsState()

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
    }

    Column(modifier = Modifier.padding(16.dp)) {
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
    }
}