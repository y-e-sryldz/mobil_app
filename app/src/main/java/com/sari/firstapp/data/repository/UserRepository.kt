package com.sari.firstapp.data.repository

import com.sari.firstapp.data.local.UserDao
import com.sari.firstapp.data.service.ApiService

class UserRepository(private val userDao: UserDao, private val apiService: ApiService) {

}
