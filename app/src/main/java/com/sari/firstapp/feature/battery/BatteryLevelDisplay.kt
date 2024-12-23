package com.sari.firstapp.feature.battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun BatteryLevelDisplay(context: Context) {
    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val batteryLevel = intent?.getIntExtra("level", -1) ?: -1
                if (batteryLevel != -1) {
                    Toast.makeText(context, "Battery Level: $batteryLevel%", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.registerReceiver(receiver, filter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
}