package com.example.propertymanagement.ui.property

import android.Manifest
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertymanagement.data.db.SessionManager
import com.example.propertymanagement.data.di.components.DaggerAppComponent
import com.example.propertymanagement.data.di.modules.AppModule
import com.example.propertymanagement.data.models.Property
import com.example.propertymanagement.data.repositories.PropertyRepository
import com.example.propertymanagement.data.utils.Coroutines
import com.example.propertymanagement.data.utils.Dexters
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import javax.inject.Inject

class PropertyViewModel() : ViewModel() {

    var propertyList = ArrayList<Property>()
    var liveData: MutableLiveData<PropertyAction> = MutableLiveData()
    var permissionLiveData: MutableLiveData<Permissions> = MutableLiveData()

    init {
        DaggerAppComponent.builder().appModule(AppModule()).build().inject(this)
    }

    @Inject
    lateinit var propertyAuth: PropertyRepository

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var newProperty: Property

    @Inject
    lateinit var dexters: Dexters

    var userId: String? = null
    var address: String? = null
    var city: String? = null
    var state: String? = null
    var zipcode: String? = null
    var country: String? = null


    enum class PropertyAction {
        SUCCESS, FAILURE, REDIRECT
    }

    enum class Permissions {
        ACCEPTED, DENIED, REDIRECT
    }

    fun getProperty() {
        Coroutines.main {
            try {
                val response =
                    propertyAuth.getPropertyList(sessionManager.getUserInfoByKey(sessionManager.KEY_ID)!!)
                if (response.isSuccessful) {
                    propertyList = response.body()!!.data
                    liveData.value = PropertyAction.SUCCESS
                } else {
                    liveData.value = PropertyAction.FAILURE
                }
            } catch (e: Exception) {
                Log.d("abc", e.message)
                liveData.value = PropertyAction.FAILURE
            }
        }
    }

    fun onAddPropertyButtonClicked(view: View) {
        liveData.value = PropertyAction.REDIRECT
    }

    fun onSaveNewProperty(view: View) {
        Coroutines.main {
            userId = sessionManager.getUserInfoByKey(sessionManager.KEY_ID)
            newProperty.userId = userId
            newProperty.address = address
            newProperty.city = city
            newProperty.country = country
            Log.d("abc", "new property $newProperty")

            val response = propertyAuth.postNewProperty(newProperty)
            if (response.isSuccessful) {
                liveData.value = PropertyAction.SUCCESS
            }
        }
    }

    fun onAddPropertyPicture(view: View) {
        dexters.requestSinglePermission().withPermission(Manifest.permission.CAMERA).withListener(
            object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                    permissionLiveData.value = Permissions.ACCEPTED
                }
                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    TODO("Not yet implemented")
                }
                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                    permissionLiveData.value = Permissions.DENIED
                }
            }).check()
    }
}




