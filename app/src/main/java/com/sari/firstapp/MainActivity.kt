package com.sari.firstapp

import android.os.Bundle

import android.util.Log


import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items


import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.items

import androidx.compose.ui.Modifier

import kotlinx.coroutines.launch
import com.sari.firstapp.data.repository.UserRepository
import com.sari.firstapp.feature.battery.BatteryLevelDisplay
import com.sari.firstapp.feature.map.MapScreen
import com.sari.firstapp.feature.wifi.WifiStatusDisplay
import com.sari.firstapp.models.ApiUserModel



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

            UserDisplay()

        }
    }
}
@Composable
fun UserDisplay(repository: UserRepository) {

    // Kullanıcı ve hata durumları için state
    var users by remember { mutableStateOf<List<ApiUserModel>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Fetch users
    LaunchedEffect(Unit) {
        try {
            val result = repository.getAllUsers()
            result.onSuccess { fetchedUsers ->
                users = fetchedUsers
            }.onFailure { error ->
                errorMessage = error.message
                Log.e("UserFetchError", "Error fetching users", error)
            }
        } catch (e: Exception) {
            errorMessage = e.message
            Log.e("UserFetchError", "Exception occurred", e)

        }
    }


    // Show error message or loading indicator
    if (errorMessage != null) {
        Text(text = "Error: $errorMessage")
    } else if (users.isEmpty()) {

    } else {

        LazyColumn(

        ) {
            items(users) { user ->
                UserCard(user)
            }
        }
    }
}

@Composable
fun UserCard(user: ApiUserModel) {
    // Container background color
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFEFEFEF))  // Light grey background for the container
            .padding(8.dp)
    ) {
        // Card with elevation
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Name: ${user.name}",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Email: ${user.email}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "ID: ${user.id}",
                    style = MaterialTheme.typography.body2
                )
            }

        }
    }
}

