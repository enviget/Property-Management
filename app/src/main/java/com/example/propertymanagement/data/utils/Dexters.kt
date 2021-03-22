package com.example.propertymanagement.data.utils

import android.content.Context
import com.karumi.dexter.Dexter
import com.karumi.dexter.DexterBuilder
import dagger.Module

@Module
class Dexters() {

    lateinit var mContext: Context
    val REQUEST_CAMERA_CODE = 1
    val REQUEST_GALLERY_CODE = 2

    init {
        mContext = MyApplication.mContext
    }

    fun requestSinglePermission(): DexterBuilder.Permission {
        return Dexter.withContext(mContext)
    }

}