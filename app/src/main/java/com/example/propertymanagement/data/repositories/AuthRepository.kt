package com.example.propertymanagement.data.repositories


import com.example.propertymanagement.data.di.components.DaggerAppComponent
import com.example.propertymanagement.data.di.modules.AppModule
import com.example.propertymanagement.data.models.AuthResponse
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.networks.AuthApi
import com.example.propertymanagement.data.networks.SafeApiRequest
import javax.inject.Inject

class AuthRepository: SafeApiRequest() {
    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    @Inject
    lateinit var authApi: AuthApi

    suspend fun userLogin(user: User): AuthResponse {
        return apiRequest { authApi.postLogin(user) }


    }

    suspend fun userRegister(user: User): AuthResponse {
        return apiRequest { authApi.postRegister(user) }
    }

}