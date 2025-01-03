package com.sari.firstapp.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sari.firstapp.data.repository.UserRepository
import com.sari.firstapp.viewmodel.UserViewModel
import com.sari.firstapp.viewmodel.UserViewModelFactory

class UserScreen : ComponentActivity() {

    // UserViewModelFactory ile ViewModel'i başlatıyoruz
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserListScreen(userViewModel)
        }
    }
}
