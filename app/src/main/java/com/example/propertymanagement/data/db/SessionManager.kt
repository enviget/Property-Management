package com.example.propertymanagement.data.db

import android.content.Context
import android.content.SharedPreferences
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.utils.MyApplication

class SessionManager(){

     val KEY_ID = "userId"
     val KEY_EMAIL = "email"
     val KEY_PASSWORD = "password"
     val KEY_NAME = "name"
     var KEY_IS_LOGGED_IN = "user is logged in"

    lateinit var sharedPreferences : SharedPreferences
    lateinit var editor : SharedPreferences.Editor
    lateinit var mContext: Context

    init {
        mContext= MyApplication.mContext
        sharedPreferences = mContext.getSharedPreferences("sharedPreference_db", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun login(user: User){
        editor.putString(KEY_ID, user._id)
        editor.putString(KEY_EMAIL, user.email)
        editor.putString(KEY_NAME, user.name)
        editor.putString(KEY_PASSWORD, user.password)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.commit()
    }

    fun getUserInfoByKey(key:String):String?{
        return sharedPreferences.getString(key, null)
    }

    fun isUserLoggedIn():Boolean{
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun logout(){
        editor.clear()
        editor.commit()
    }
}