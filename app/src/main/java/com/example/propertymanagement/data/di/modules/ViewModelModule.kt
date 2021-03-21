package com.example.propertymanagement.data.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagement.data.di.viewmodel.ViewModelFactory
import com.example.propertymanagement.data.di.viewmodel.ViewModelKey
import com.example.propertymanagement.ui.auth.AuthViewModel
import com.example.propertymanagement.ui.home.HomeViewModel
import com.example.propertymanagement.ui.property.PropertyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindsAuthViewModel(authViewModel: AuthViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsHomeViewModel(homeViewModel: HomeViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PropertyViewModel::class)
    abstract fun bindsPropertyViewModel(propertyViewModel: PropertyViewModel): ViewModel


}