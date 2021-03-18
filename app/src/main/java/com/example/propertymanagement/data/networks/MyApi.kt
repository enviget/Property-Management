package com.example.propertymanagement.data.networks

import com.example.propertymanagement.data.models.AuthResponse
import com.example.propertymanagement.data.models.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApi {


    @POST("auth/login")
    fun postLogin(@Body user:User): Single<AuthResponse>

    @POST("auth/register")
    fun postRegister(@Body user:User): Single<AuthResponse>

}