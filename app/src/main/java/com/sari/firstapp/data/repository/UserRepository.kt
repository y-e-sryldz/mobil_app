package com.sari.firstapp.data.repository
import com.sari.firstapp.models.ApiUserModel
import com.sari.firstapp.utils.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository
{
    suspend fun getAllUsers(): Result<List<ApiUserModel>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitClient.instance.getAllUsers().execute()
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
    //TODO BURADAN SONRA GEREKLI ISLEMLER DAHIL EDILECEK
    //TODO BURAYA API SERVICE DEKI DIGER ENDPOINTLER IMPLEMENT EDILMELI

}
