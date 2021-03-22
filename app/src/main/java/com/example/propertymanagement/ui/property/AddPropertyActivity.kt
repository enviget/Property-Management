package com.example.propertymanagement.ui.property

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityAddPropertyBinding
import kotlinx.android.synthetic.main.activity_add_property.*

class AddPropertyActivity : AppCompatActivity() {

    lateinit var addPropertyBinding: ActivityAddPropertyBinding
    val REQUEST_GALLERY_CODE = 1
    val viewModel by viewModels<PropertyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPropertyBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_property)
        addPropertyBinding.addPropertyInfo = viewModel
        viewModel.liveData.observe(this, Observer {
            when (it) {
                PropertyViewModel.PropertyAction.SUCCESS -> {
                    Log.d("abc", "new property added")
                }
            }
        }
        )
        viewModel.permissionLiveData.observe(this, Observer {
            when (it) {
                PropertyViewModel.Permissions.ACCEPTED -> {
                    Log.d("abc", "Permission accepted")
                    openGallery()

                }
                PropertyViewModel.Permissions.DENIED -> {
                    Log.d("abc", "Permission denied")
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, REQUEST_GALLERY_CODE)
        Log.d("abc", "Gallery Opened")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_GALLERY_CODE -> {
                if (data?.data != null) {
                    text_view_uri.text = data.data!!.toString()

                } else {
                    Toast.makeText(applicationContext, "Image has not selected", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }


}