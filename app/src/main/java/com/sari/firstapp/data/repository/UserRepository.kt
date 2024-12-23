package com.sari.firstapp.data.repository

import com.sari.firstapp.data.local.UserDao
import com.sari.firstapp.data.remote.ApiService

class UserRepository(private val userDao: UserDao, private val apiService: ApiService) {

}
