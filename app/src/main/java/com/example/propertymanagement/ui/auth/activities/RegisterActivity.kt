package com.example.propertymanagement.ui.auth.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityRegisterBinding
import com.example.propertymanagement.ui.adapters.AdapterFragment
import com.example.propertymanagement.ui.auth.AuthViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var mRegisterBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        var viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mRegisterBinding.registerInfo = viewModel

        var adapterFragment = AdapterFragment(supportFragmentManager)
        view_pager.adapter = adapterFragment
        tab_layout.setupWithViewPager(view_pager)
    }
}