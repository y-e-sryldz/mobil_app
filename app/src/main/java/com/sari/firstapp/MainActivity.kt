package com.sari.firstapp

import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.sari.firstapp.data.repository.UserRepository
import com.sari.firstapp.feature.battery.BatteryLevelDisplay
import com.sari.firstapp.feature.wifi.WifiStatusDisplay
import com.sari.firstapp.models.ApiUserModel
import kotlinx.coroutines.launch

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
        Column {
            BatteryLevelDisplay(context = context)
            WifiStatusDisplay(context = context)
            Text(text = "Hello World!")
            UserDisplay()
        }
    }
}
@Composable
fun UserDisplay() {
    val repository = UserRepository()
    val scope = rememberCoroutineScope()

    // State for users and error messages
    var users by remember { mutableStateOf<List<ApiUserModel>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Kullanıcıları getir
    LaunchedEffect(Unit) {
        scope.launch {
            val result = repository.getAllUsers()
            result.onSuccess { fetchedUsers ->
                users = fetchedUsers
            }.onFailure { error ->
                errorMessage = error.message
                Log.e("UserFetchError", "Error fetching users", error)
            }
        }
    }

    // Hata mesajını göster veya kullanıcıları yazdır
    if (errorMessage != null) {
        Text(text = "Hata: $errorMessage")
    } else if (users.isEmpty()) {
        Text(text = "Kullanıcılar yükleniyor...")
    } else {
        users.forEach { user ->
            Text(text = "ID: ${user.id}, Name: ${user.name}, Email: ${user.email},Height:${user.height},Weight:${user.weight},BMI:${user.bmi}")
        }
    }
}