package com.sari.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.sari.firstapp.feature.battery.BatteryLevelDisplay
import com.sari.firstapp.feature.map.MapScreen
import com.sari.firstapp.feature.wifi.WifiStatusDisplay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val context = LocalContext.current

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BatteryLevelDisplay(context = context)
            WifiStatusDisplay(context = context)
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.weight(1f))
            MapScreen()
        }
    }
}
