package com.example.propertymanagement.ui.home

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.data.db.SessionManager
import com.example.propertymanagement.data.di.components.DaggerAppComponent
import com.example.propertymanagement.data.di.modules.AppModule
import com.example.propertymanagement.data.utils.Coroutines
import javax.inject.Inject

class HomeViewModel:ViewModel(){

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    enum class HomeAction{
        PROPERTY, LOGOUT
    }

    @Inject
    lateinit var sessionManager : SessionManager

    var liveData: MutableLiveData<HomeAction> = MutableLiveData()

    fun onLogoutButtonClicked(view:View){
        Coroutines.main {
            try{
            sessionManager.logout()
            liveData.value = HomeAction.LOGOUT

        }catch (e : Exception){
            Log.d("abc", "could not logout")}
        }

    }

    fun onPropertyButtonClicked(view:View){
        liveData.value = HomeAction.PROPERTY
    }

}