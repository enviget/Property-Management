package com.example.propertymanagement.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.data.di.components.DaggerAppComponent
import com.example.propertymanagement.data.di.modules.AppModule
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.repositories.AuthRepository
import com.example.propertymanagement.data.utils.ApiException
import com.example.propertymanagement.data.utils.Coroutines
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class AuthViewModel : ViewModel() {

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    var liveData: MutableLiveData<AuthAction> = MutableLiveData()

    var email: String? = null
    var password: String? = null
    var name: String? = null
    var type: String? = null

    lateinit var disposal: Disposable

    @Inject
    lateinit var user: User

    @Inject
    lateinit var authRepository: AuthRepository

    enum class AuthAction {
        SUCCESS,
        FAILURE,
        REDIRECT
    }

    fun onRegisterButtonClicked(view: View) {

        if (type.isNullOrEmpty() || name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()) {
            liveData.value = AuthAction.FAILURE
            Log.d("abc", "Login edit text is null")
        } else {
            user.email = email
            user.name = name
            user.password = password
            user.type = type
            Log.d("abc", user.toString())

            Coroutines.main {
                try {
                    val response = authRepository.userRegister(user)
                    if (response.error) {
                        liveData.value = AuthAction.FAILURE
                    } else {
                        liveData.value = AuthAction.SUCCESS
                    }
                } catch (e: ApiException) {
                    liveData.value = AuthAction.FAILURE
                    Log.d("abc", "catch ApiException ${e.message}")
                }
            }
        }
    }

    fun onLoginButtonClicked(view: View) {

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            liveData.value = AuthAction.FAILURE
            Log.d("abc", "Login edit text is null")
        } else {
            user.email = email
            user.password = password
            Log.d("abc", user.toString())

            Coroutines.main {
                try {
                    val response = authRepository.userLogin(user)
                    if (response.error) {
                        liveData.value = AuthAction.FAILURE
                    } else {

                        liveData.value = AuthAction.SUCCESS
                    }
                } catch (e: ApiException) {
                    liveData.value = AuthAction.FAILURE
                    Log.d("abc", "catch ApiException ${e.message}")
                }
            }
        }
    }

    fun onNoAccountButtonClicked(view: View) {
        liveData.value = AuthAction.REDIRECT
    }

    fun onHaveAccountButtonClicked(view: View) {
        liveData.value = AuthAction.REDIRECT
    }

}