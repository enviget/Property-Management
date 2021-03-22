package com.example.propertymanagement.data.di.modules

import com.example.propertymanagement.data.db.SessionManager
import com.example.propertymanagement.data.models.Property
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.networks.AuthApi
import com.example.propertymanagement.data.networks.PropertyApi
import com.example.propertymanagement.data.repositories.AuthRepository
import com.example.propertymanagement.data.repositories.PropertyRepository
import com.example.propertymanagement.data.utils.Dexters
import com.karumi.dexter.Dexter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(/*private val application : MyApplication*/) {

//    @Provides @Singleton fun provideApplicationContext(): Context = application

    //libraries
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://apolis-property-management.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideSessionManager(): SessionManager {
        return SessionManager()
    }

    @Singleton
    @Provides
    fun provideDexter(): Dexters {
        return Dexters()
    }


    //API
    @Singleton
    @Provides
    fun provideMyApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    fun provideProductApi(retrofit: Retrofit): PropertyApi {
        return retrofit.create(PropertyApi::class.java)
    }


    //Repository
    @Singleton
    @Provides
    fun getAuthRepo(): AuthRepository {
        return AuthRepository()
    }

    @Singleton
    @Provides
    fun getPropertyRepo(): PropertyRepository {
        return PropertyRepository()
    }


    //Data class
    @Provides
    fun getUser(): User {
        return User()
    }

    @Provides
    fun getProperty(): Property {
        return Property()
    }


}
