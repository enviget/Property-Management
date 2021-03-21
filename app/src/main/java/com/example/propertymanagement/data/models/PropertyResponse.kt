package com.example.propertymanagement.data.models

import androidx.lifecycle.MutableLiveData
import dagger.Module

data class PropertyResponse(
    val count: Int,
    val data: ArrayList<Property>,
    val error: Boolean
)

data class PostPropertyResponse(
    val data: Data,
    val error: Boolean,
    val message: String
)

data class Data(
    val __v: Int,
    val _id: String,
    val image: String,
    val mortageInfo: Boolean,
    val propertyStatus: Boolean
)

@Module
data class Property(
    var __v: Int?= null,
    var _id: String?= null,
    var address: String?= null,
    var city: String?= null,
    var country: String?= null,
    var image: String?= null,
    var latitude: String?= null,
    var longitude: String?= null,
    var mortageInfo: Boolean?= null,
    var propertyStatus: Boolean?= null,
    var purchasePrice: String?= null,
    var state: String?= null,
    var userId: String?= null,
    var userType: String?= null
)