package com.example.propertymanagement.data.db.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.propertymanagement.data.models.User

@Database(entities = [User::class],version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun getUserDao() : UserDao
}