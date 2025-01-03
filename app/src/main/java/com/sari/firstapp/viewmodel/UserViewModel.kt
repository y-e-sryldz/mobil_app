package com.sari.firstapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sari.firstapp.data.repository.UserRepository
import com.sari.firstapp.models.ApiUserModel

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    // Kullanıcıları getir
    fun getAllUsers() = liveData {
        try {
            val result = userRepository.getAllUsers()
            emit(result)
        } catch (e: Exception) {
            emit(Result.failure<List<ApiUserModel>>(e))
        }
    }

    // ID'ye göre kullanıcıyı getir
    fun getUserById(id: String) = liveData {
        try {
            val result = userRepository.getUserById(id)
            emit(result)
        } catch (e: Exception) {
            emit(Result.failure<ApiUserModel>(e))
        }
    }

    // Yeni kullanıcı oluştur
    fun createUser(user: ApiUserModel) = liveData {
        try {
            val result = userRepository.createUser(user)
            emit(result)
        } catch (e: Exception) {
            emit(Result.failure<Boolean>(e))
        }
    }

    // Kullanıcıyı güncelle
    fun updateUser(id: String, user: ApiUserModel) = liveData {
        try {
            val result = userRepository.updateUser(id, user)
            emit(result)
        } catch (e: Exception) {
            emit(Result.failure<Boolean>(e))
        }
    }

    // Kullanıcıyı sil
    fun deleteUser(id: String) = liveData {
        try {
            val result = userRepository.deleteUser(id)
            emit(result)
        } catch (e: Exception) {
            emit(Result.failure<Boolean>(e))
        }
    }
}
