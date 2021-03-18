package com.example.propertymanagement.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dagger.Module
import java.io.Serializable

data class AuthResponse(
    val count: Int,
    val data: User,
    val error: Boolean
)

const val CURRENT_USER_ID = 0

@Entity
@Module
data class User(
    var __v: Int? = null,
    var _id: String? = null,
    var createdAt: String? = null,
    var email: String? = null,
    var landlordEmail: String? = null,
    var name: String? = null,
    var password: String? = null,
    var type: String? = null
){
    @PrimaryKey(autoGenerate = false)
    var uid = CURRENT_USER_ID
}