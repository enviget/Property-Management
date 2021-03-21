package com.example.propertymanagement.data.di.components

import com.example.propertymanagement.data.di.modules.AppModule
import com.example.propertymanagement.data.di.modules.ViewModelModule
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.repositories.AuthRepository
import com.example.propertymanagement.data.repositories.PropertyRepository
import com.example.propertymanagement.ui.auth.AuthViewModel
import com.example.propertymanagement.ui.auth.activities.LoginActivity
import com.example.propertymanagement.ui.home.HomeViewModel
import com.example.propertymanagement.ui.property.PropertyViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class,
        User::class,
        ViewModelModule::class]
)
interface AppComponent {

    //activities
    fun inject(activity: LoginActivity)

    //repositories
    fun inject(authClass: AuthRepository)
    fun inject(propertyClass: PropertyRepository)

    //ViewModel
    fun inject(homeViewModel: HomeViewModel)
    fun inject(propertyViewModel: PropertyViewModel)
    fun inject(authViewModel: AuthViewModel)

}