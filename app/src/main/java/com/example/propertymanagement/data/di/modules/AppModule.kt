package com.example.propertymanagement.data.di.modules

import android.content.Context
import android.provider.Settings.Global.getString
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.propertymanagement.R
import com.example.propertymanagement.data.db.entities.AppDataBase
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.networks.MyApi
import com.example.propertymanagement.data.repositories.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

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
    fun provideMyApi(retrofit: Retrofit): MyApi {
        return retrofit.create(MyApi::class.java)
    }

    @Provides
    fun getAuthRepo(): AuthRepository {
        return AuthRepository()
    }

    @Provides
    fun getUser(): User {
        return User()
    }

//    @Provides
//    fun provideRoomDatabase(context:Context): RoomDatabase{
//        return Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, "").build()
//    }
//
//    @Provides
//    fun provideGoogleSignInOptions(context:Context) : GoogleSignInOptions{
//        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(context.applicationContext.getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//    }

}
