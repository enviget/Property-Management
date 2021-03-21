package com.example.propertymanagement.data.repositories

import android.util.Log
import com.example.propertymanagement.data.di.components.DaggerAppComponent
import com.example.propertymanagement.data.di.modules.AppModule
import com.example.propertymanagement.data.models.PostPropertyResponse
import com.example.propertymanagement.data.models.Property
import com.example.propertymanagement.data.models.PropertyResponse
import com.example.propertymanagement.data.networks.AuthApi
import com.example.propertymanagement.data.networks.PropertyApi
import retrofit2.Response
import javax.inject.Inject

class PropertyRepository{

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }
    @Inject
    lateinit var propertyApi: PropertyApi


    suspend fun getPropertyList(userId: String): Response<PropertyResponse> {
        return propertyApi.getProperty(userId)
    }

    suspend fun postNewProperty(property:Property): Response<PostPropertyResponse>{
        return propertyApi.postProperty(property)
    }
}