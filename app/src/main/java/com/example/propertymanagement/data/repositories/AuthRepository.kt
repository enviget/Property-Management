package com.example.propertymanagement.data.repositories


import android.util.Log
import com.example.propertymanagement.data.di.components.DaggerAppComponent
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.networks.MyApi
import com.example.propertymanagement.data.di.modules.AppModule
import com.example.propertymanagement.data.models.AuthResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class AuthRepository {

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    @Inject
    lateinit var myApi: MyApi

    suspend fun userLogin(user: User): Response<AuthResponse> {
        return myApi.postLogin(user)
    }

    suspend fun userRegister(user: User): Response<AuthResponse> {
        return myApi.postRegister(user)
    }

}