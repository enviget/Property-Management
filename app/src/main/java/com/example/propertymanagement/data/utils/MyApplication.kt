package com.example.propertymanagement.data.utils

import android.app.Application
import android.content.Context
import com.example.propertymanagement.data.di.components.AppComponent
import com.example.propertymanagement.data.di.components.DaggerAppComponent
import com.example.propertymanagement.data.di.modules.AppModule

class MyApplication : Application() {
    private var appComponent: AppComponent? = null
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule()).build()
    }
    companion object {
        lateinit var mContext: Context
    }
}