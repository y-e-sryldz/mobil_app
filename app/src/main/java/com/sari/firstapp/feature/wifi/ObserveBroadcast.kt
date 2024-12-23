package com.sari.firstapp.feature.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun ObserveBroadcast(
    context: Context,
    intentFilter: IntentFilter,
    onReceive: (Intent) -> Unit
) {
    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent != null) onReceive(intent)
            }
        }
        context.registerReceiver(receiver, intentFilter)

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }
}
