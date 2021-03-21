package com.example.propertymanagement.ui.auth

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.propertymanagement.data.db.SessionManager
import com.example.propertymanagement.data.di.components.DaggerAppComponent
import com.example.propertymanagement.data.di.modules.AppModule
import com.example.propertymanagement.data.models.User
import com.example.propertymanagement.data.repositories.AuthRepository
import com.example.propertymanagement.data.utils.ApiException
import com.example.propertymanagement.data.utils.Coroutines
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel : ViewModel() {

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    var liveData: MutableLiveData<AuthAction> = MutableLiveData()

    var email: String? = null
    var password: String? = null
    var name: String? = null
    var landlordEmail: String? = null

    var TAG_LANDLORD = "landlord"
    var TAG_TENANT = "tenant"


    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
     lateinit var user :User

    @Inject
    lateinit var authRepository: AuthRepository

    enum class AuthAction {
        SUCCESS,
        FAILURE,
        REDIRECT,
        LOGGEDIN,
    }

    fun onLandlordRegisterButtonClicked(view: View) {

        if (name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()) {
            liveData.value = AuthAction.FAILURE
            Log.d("abc", "Login edit text is null")
        } else {
            user.email = email
            user.name = name
            user.password = password
            user.type = TAG_LANDLORD
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

    fun onTenantRegisterButtonClicked(view: View) {

        if (landlordEmail.isNullOrEmpty() || name.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty()) {
            liveData.value = AuthAction.FAILURE
            Log.d("abc", "Login edit text is null")
        } else {
            user.email = email
            user.name = name
            user.password = password
            user.type = TAG_TENANT
            user.landlordEmail = landlordEmail
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
                        Log.d("abc", "${response.user}")
                        sessionManager.login(response.user!!)
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

    fun loggedIn(){
        viewModelScope.launch {
            try{
                if(sessionManager.isUserLoggedIn()){
                    Log.d("abc", "${sessionManager.isUserLoggedIn()}")
                    liveData.value = AuthAction.SUCCESS
                }
            }catch (e : Exception){
                Log.d("abc", "user is not logged in")
            }
        }
    }

}
