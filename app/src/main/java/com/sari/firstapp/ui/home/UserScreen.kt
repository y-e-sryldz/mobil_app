package com.sari.firstapp.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.sari.firstapp.models.ApiUserModel
import com.sari.firstapp.viewmodel.UserViewModel


class UserScreen : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserListScreen(userViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(userViewModel: UserViewModel) {
    val users = userViewModel.getAllUsers().observeAsState(initial = Result.success(emptyList()))

    Scaffold(
        topBar = { TopAppBar(title = { Text("User List") }) },
        content = { padding ->
            LazyColumn(modifier = Modifier.padding(padding)) {
                if (users.value.isSuccess) {
                    val userList = users.value.getOrNull() ?: emptyList()
                    items(userList.size) { index ->
                        UserRow(userList[index])
                    }
                } else {
                    item {
                        BasicText("Error loading users")
                    }
                }
            }
        }
    )
}

@Composable
fun UserRow(user: ApiUserModel) {
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = user.name, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = user.email, style = MaterialTheme.typography.bodyMedium)
    }
}
