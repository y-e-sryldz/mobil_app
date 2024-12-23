package com.sari.firstapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {
    Scaffold(

    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Welcome to Fitness App!",
                style = MaterialTheme.typography.titleLarge // Material 3 typography
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* TODO: Navigate to Exercise Screen */ }) {
                Text("Start Exercise")
            }
        }
    }
}
