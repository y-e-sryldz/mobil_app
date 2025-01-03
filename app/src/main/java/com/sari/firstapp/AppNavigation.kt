package com.sari.firstapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sari.firstapp.viewmodel.UserViewModel
import com.sari.firstapp.data.repository.UserRepository
import com.sari.firstapp.ui.home.UserListScreen
import com.sari.firstapp.ui.screens.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Users : Screen("users")
}

@Composable
fun AppNavigation(navController: NavHostController) {
    val userRepository = UserRepository()
    val userViewModel = UserViewModel(userRepository)

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Users.route) { UserListScreen(userViewModel) }
    }
}
