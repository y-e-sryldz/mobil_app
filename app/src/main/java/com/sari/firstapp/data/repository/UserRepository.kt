package com.sari.firstapp.data.repository

import com.sari.firstapp.api.ApiService
import com.sari.firstapp.models.ApiUserModel
import com.sari.firstapp.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {

    private val apiService: ApiService = RetrofitClient.instance

    suspend fun getAllUsers(): Result<List<ApiUserModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAllUsers().execute()
                if (response.isSuccessful) {
                    Result.success(response.body() ?: emptyList())
                } else {
                    val errorBody = response.errorBody()?.string()
                    Result.failure(Exception("Hata kodu: ${response.code()} - $errorBody"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getUserById(id: String): Result<ApiUserModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getUserById(id).execute()
                if (response.isSuccessful) {
                    Result.success(response.body() ?: throw Exception("Kullanıcı bulunamadı"))
                } else {
                    val errorBody = response.errorBody()?.string()
                    Result.failure(Exception("Hata kodu: ${response.code()} - $errorBody"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun createUser(apiUserModel: ApiUserModel): Result<ApiUserModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.createUser(apiUserModel).execute()
                if (response.isSuccessful) {
                    Result.success(response.body() ?: throw Exception("Kullanıcı oluşturulamadı"))
                } else {
                    val errorBody = response.errorBody()?.string()
                    Result.failure(Exception("Hata kodu: ${response.code()} - $errorBody"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun updateUser(id: String, updatedApiUserModel: ApiUserModel): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.updateUser(id, updatedApiUserModel).execute()
                if (response.isSuccessful) {
                    Result.success(Unit)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Result.failure(Exception("Hata kodu: ${response.code()} - $errorBody"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun deleteUser(id: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.deleteUser(id).execute()
                if (response.isSuccessful) {
                    Result.success(Unit)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Result.failure(Exception("Hata kodu: ${response.code()} - $errorBody"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
