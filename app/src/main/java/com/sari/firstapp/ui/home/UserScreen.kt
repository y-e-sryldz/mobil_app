package com.sari.firstapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sari.firstapp.models.ApiUserModel
import com.sari.firstapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(userViewModel: UserViewModel) {
    val usersResult by userViewModel.getAllUsers().observeAsState(initial = Result.success(emptyList()))
    val users = remember { mutableStateListOf<ApiUserModel>() }


    LaunchedEffect(usersResult) {
        usersResult.getOrNull()?.let {
            users.clear()
            users.addAll(it)
        }
    }


    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("User List & Add User") }) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                // Kullanıcı ekleme formu
                Text(text = "Add New User", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = height,
                    onValueChange = { height = it },
                    label = { Text("Height (meters)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = { Text("Weight (kg)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (name.isNotEmpty() && email.isNotEmpty() && height.isNotEmpty() && weight.isNotEmpty()) {
                            val heightValue = height.toDoubleOrNull()
                            val weightValue = weight.toDoubleOrNull()

                            if (heightValue != null && weightValue != null) {
                                val bmi = weightValue / (heightValue * heightValue)
                                val newUser = ApiUserModel(
                                    name = name,
                                    email = email,
                                    height = heightValue,
                                    weight = weightValue,
                                    bmi = bmi
                                )

                                userViewModel.createUser(newUser).observeForever {
                                    if (it.isSuccess) {
                                        users.add(newUser)
                                        name = ""
                                        email = ""
                                        height = ""
                                        weight = ""
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add User")
                }

                Spacer(modifier = Modifier.height(32.dp))


                if (users.isEmpty()) {
                    Text(
                        text = "No users found.",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(users) { user ->
                            UserRow(user)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun UserRow(user: ApiUserModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${user.name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Email: ${user.email}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Weight: ${user.weight} kg", style = MaterialTheme.typography.bodySmall)
            Text(text = "Height: ${user.height} m", style = MaterialTheme.typography.bodySmall)
            Text(text = "BMI: ${user.bmi}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
