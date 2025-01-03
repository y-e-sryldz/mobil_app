package com.sari.firstapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sari.firstapp.feature.battery.BatteryLevelDisplay
import com.sari.firstapp.feature.map.MapScreen
import com.sari.firstapp.feature.wifi.WifiStatusDisplay

@Composable
fun HomeScreen(navController: NavController) {
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BatteryLevelDisplay(context = context)
        WifiStatusDisplay(context = context)

        Spacer(modifier = Modifier.height(16.dp))

        MapScreen()

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("users") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Go to User List")
        }
    }
}
