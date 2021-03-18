package com.example.propertymanagement.ui.auth

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.data.di.components.DaggerAppComponent
import com.example.propertymanagement.data.di.modules.AppModule
import com.example.propertymanagement.data.models.AuthResponse
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.repositories.AuthRepository
import io.reactivex.SingleObserver
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

    lateinit var disposal : Disposable

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

            var response = authRepository.userRegister(user)
            response.subscribeWith(AuthObserver())
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

            val response = authRepository.userLogin(user)
            response.subscribeWith(AuthObserver())
        }
    }

    inner class AuthObserver() : SingleObserver<AuthResponse> {
        override fun onSuccess(t: AuthResponse) {
            if (t.error) {
                liveData.value = AuthAction.FAILURE
                Log.d("abc", "error = true")

            } else {
                liveData.value = AuthAction.SUCCESS
                Log.d("abc", "error = false")
            }

        }

        override fun onSubscribe(d: Disposable) {
            disposal = d
            Log.d("abc", "Disposal")
        }

        override fun onError(e: Throwable) {
            liveData.value = AuthAction.FAILURE
            Log.d("abc", e.message)
            Log.d("abc", "exception called in Auth Observer")
        }

    }
    fun onNoAccountButtonClicked(view: View) {
        liveData.value = AuthAction.REDIRECT
    }

    fun onHaveAccountButtonClicked(view: View) {
        liveData.value = AuthAction.REDIRECT
    }

}