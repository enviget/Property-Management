package com.example.propertymanagement.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Property
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.propertymanagement.R
import com.example.propertymanagement.data.utils.MyApplication
import com.example.propertymanagement.databinding.ActivityMainBinding
import com.example.propertymanagement.ui.auth.activities.LoginActivity
import com.example.propertymanagement.ui.property.PropertyActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var homeBinding: ActivityMainBinding
    val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.mContext= this
        homeBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        homeBinding.homeInfo = viewModel
        viewModel.liveData.observe(this, Observer {
            when(it){
                HomeViewModel.HomeAction.LOGOUT -> {
                    Log.d("abc", "user logout")
                    startActivity(Intent(this,LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                }
                HomeViewModel.HomeAction.PROPERTY -> {
                    Log.d("abc", "Property clicked")
                    startActivity(Intent(this,PropertyActivity::class.java))
                }
            }
        })

    }
}