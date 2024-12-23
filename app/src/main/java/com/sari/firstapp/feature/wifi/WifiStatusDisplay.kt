package com.sari.firstapp.feature.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect


@Composable
fun WifiStatusDisplay(context: Context) {
    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Toast.makeText(context, "Wi-Fi status changed!", Toast.LENGTH_SHORT).show()
            }
        }

        val filter = IntentFilter("android.net.wifi.STATE_CHANGE")
        context.registerReceiver(receiver, filter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
}