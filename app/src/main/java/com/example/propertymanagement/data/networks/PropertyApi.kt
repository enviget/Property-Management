package com.example.propertymanagement.data.networks

import com.example.propertymanagement.data.models.PostPropertyResponse
import com.example.propertymanagement.data.models.Property
import com.example.propertymanagement.data.models.PropertyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PropertyApi{
    @GET("property/user/{id}")
    suspend fun getProperty(@Path("id") userId:String):Response<PropertyResponse>

    @POST("property")
    suspend fun postProperty(@Body property:Property):Response<PostPropertyResponse>

}