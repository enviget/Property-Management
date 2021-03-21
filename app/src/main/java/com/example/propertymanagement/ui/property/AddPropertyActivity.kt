package com.example.propertymanagement.ui.property

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityAddPropertyBinding

class AddPropertyActivity : AppCompatActivity() {

    lateinit var addPropertyBinding: ActivityAddPropertyBinding
    val viewModel by viewModels<PropertyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPropertyBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_property)
        addPropertyBinding.addPropertyInfo = viewModel
        viewModel.liveData.observe(this, Observer{
            when(it){
                PropertyViewModel.PropertyAction.SUCCESS ->{
                    Log.d("abc", "new property added")
                }
            }
        }
        )
    }
}