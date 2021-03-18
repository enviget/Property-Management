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
import javax.inject.Inject

class AuthRepository {

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    @Inject
    lateinit var myApi: MyApi

    fun userLogin(user: User): Single<AuthResponse> {
        return myApi.postLogin(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun userRegister(user: User): Single<AuthResponse> {
        return myApi.postRegister(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}