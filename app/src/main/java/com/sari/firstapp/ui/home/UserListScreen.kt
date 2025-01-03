package com.sari.firstapp.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.sari.firstapp.models.ApiUserModel
import com.sari.firstapp.viewmodel.UserViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun UserListScreen(userViewModel: UserViewModel) {
    // Kullanıcı verisini gözlemliyoruz
    val usersResult = userViewModel.getAllUsers().observeAsState(initial = Result.success(emptyList()))
    var users by remember { mutableStateOf<List<ApiUserModel>>(emptyList()) }
    val context = LocalContext.current

    // Kullanıcı verisi geldiğinde güncelle
    LaunchedEffect(usersResult.value) {
        if (usersResult.value.isSuccess) {
            users = usersResult.value.getOrNull() ?: emptyList()
        }
    }

    // Kullanıcı eklemek için form inputları
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("User Management") }) },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                // Yeni kullanıcı ekleme formu
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier.padding(8.dp)
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.padding(8.dp)
                )

                // Kullanıcı ekleme butonu
                Button(
                    onClick = {
                        if (name.isNotEmpty() && email.isNotEmpty()) {
                            val newUser = ApiUserModel(name = name, email = email)
                            userViewModel.createUser(newUser).observe(context as LifecycleOwner) {
                                if (it.isSuccess) {
                                    Toast.makeText(context, "User Created", Toast.LENGTH_SHORT).show()
                                    name = ""
                                    email = ""
                                    // Kullanıcı ekledikten sonra listeyi güncellemek için
                                    userViewModel.getAllUsers() // Listeyi yeniden al
                                } else {
                                    Toast.makeText(context, "Error: ${it.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Add User")
                }

                // Kullanıcı listesi
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    if (users.isNotEmpty()) {
                        items(users.size) { index ->
                            // Her kullanıcıyı bir konteynıra yerleştiriyoruz
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                elevation = 4.dp
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    UserRow(
                                        user = users[index],
                                        onDelete = {
                                            userViewModel.deleteUser(users[index].id!!).observe(context as LifecycleOwner) {
                                                if (it.isSuccess) {
                                                    Toast.makeText(context, "User Deleted", Toast.LENGTH_SHORT).show()
                                                    // Silme sonrası listeyi güncellemek için
                                                    userViewModel.getAllUsers() // Listeyi yeniden al
                                                } else {
                                                    Toast.makeText(context, "Error: ${it.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
                                                }
                                            }
                                        },
                                        onUpdate = {
                                            val updatedUser = users[index].copy(name = "Updated Name")
                                            userViewModel.updateUser(users[index].id!!, updatedUser).observe(context as LifecycleOwner) {
                                                if (it.isSuccess) {
                                                    Toast.makeText(context, "User Updated", Toast.LENGTH_SHORT).show()
                                                    // Güncelleme sonrası listeyi güncellemek için
                                                    userViewModel.getAllUsers() // Listeyi yeniden al
                                                } else {
                                                    Toast.makeText(context, "Error: ${it.exceptionOrNull()?.message}", Toast.LENGTH_SHORT).show()
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    } else {
                        item {
                            Text("Error loading users: ${usersResult.value.exceptionOrNull()?.message}")
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun UserRow(user: ApiUserModel, onDelete: () -> Unit, onUpdate: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = user.name, style = MaterialTheme.typography.body1)
            Text(text = user.email, style = MaterialTheme.typography.body2)
        }
        Row {
            IconButton(onClick = onUpdate) {
                Icon(Icons.Default.Edit, contentDescription = "Update")
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
