package com.example.propertymanagement.data.repositories


import android.util.Log
import com.example.propertymanagement.data.di.components.DaggerAppComponent
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.networks.MyApi
import com.example.propertymanagement.data.di.modules.AppModule
import com.example.propertymanagement.data.models.AuthResponse
import com.example.propertymanagement.data.networks.SafeApiRequest
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class AuthRepository: SafeApiRequest() {

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    @Inject
    lateinit var myApi: MyApi

    suspend fun userLogin(user: User): AuthResponse {
        return apiRequest { myApi.postLogin(user) }
    }

    suspend fun userRegister(user: User): AuthResponse {
        return apiRequest { myApi.postRegister(user) }
    }

}