package com.example.propertymanagement.data.di.components

import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.repositories.AuthRepository
import com.example.propertymanagement.data.di.modules.AppModule
import com.example.propertymanagement.ui.auth.AuthViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, User::class])
interface AppComponent {

    fun inject(authClass: AuthRepository)

    fun inject(authViewModel: AuthViewModel)

}