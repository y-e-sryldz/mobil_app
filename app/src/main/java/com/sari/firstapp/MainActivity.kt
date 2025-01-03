package com.sari.firstapp

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.sari.firstapp.models.ApiUserModel
import com.sari.firstapp.ui.home.UserScreen
import com.sari.firstapp.viewmodel.UserViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sari.firstapp.data.repository.UserRepository
import com.sari.firstapp.feature.wifi.WifiStatusDisplay
import com.sari.firstapp.feature.battery.BatteryLevelDisplay

import androidx.compose.foundation.background




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, UserScreen::class.java))  // UserScreen'e geçiş yapılıyor
    }
}

@Composable
fun MyApp() {
    val context = LocalContext.current

    MaterialTheme {
        Column {
            BatteryLevelDisplay(context = context)
            WifiStatusDisplay(context = context)
            UserDisplay()  // Kullanıcıları göstermek için UserDisplay çağrılıyor
        }
    }
}

@Composable
fun UserDisplay() {
    val userViewModel: UserViewModel = viewModel()  // UserViewModel'i kullanıyoruz

    // State for users and error messages
    var users by remember { mutableStateOf<List<ApiUserModel>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Fetch users
    LaunchedEffect(Unit) {
        userViewModel.getAllUsers().observeForever { result ->
            result.onSuccess { fetchedUsers ->
                users = fetchedUsers
            }.onFailure { error ->
                errorMessage = error.message
                Log.e("UserFetchError", "Error fetching users", error)
            }
        }
    }

    // Show error message or loading indicator
    if (errorMessage != null) {
        Text(text = "Error: $errorMessage", color = Color.Red)
    } else if (users.isEmpty()) {
        // Show loading indicator if no users are available
        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
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
