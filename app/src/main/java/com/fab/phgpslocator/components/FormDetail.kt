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
import com.fab.phgpslocator.viewModel.FormViewModel

@Composable
fun FormDetail(
    mainViewModel: FormViewModel = viewModel()
) {
    val mainUiState by mainViewModel.uiState.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = mainUiState.currentName,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { mainViewModel.updateName(it) },
            label = { Text(text = "Enter your Name") },
            isError = mainUiState.currentNameErrors.isNotEmpty()
        )
        mainUiState.currentNameErrors.forEach {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = it,
                color = Color.Red
            )
        }
    }
}