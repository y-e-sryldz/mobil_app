package com.sari.firstapp.api

import com.sari.firstapp.models.ApiUserModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {


    @GET("api/user/getAllUsers")
    fun getAllUsers(): Call<List<ApiUserModel>>

    @GET("api/user/getAllUsersBogus")
    fun getAllUsersBogus(): Call<List<ApiUserModel>>

    @GET("api/user/{id}")
    fun getUserById(@Path("id") id: String): Call<ApiUserModel>

    @POST("api/user/createUser")
    fun createUser(@Body apiUserModel: ApiUserModel): Call<ApiUserModel>

    @PUT("api/user/updateUser/{id}")
    fun updateUser(@Path("id") id: String, @Body updatedApiUserModel: ApiUserModel): Call<Void>

    @DELETE("api/user/deleteUser/{id}")
    fun deleteUser(@Path("id") id: String): Call<Void>

    @POST("api/user/calculateBmi")
    fun calculateUserBmi(@Body apiUserModel: ApiUserModel):Call<ApiUserModel>
}