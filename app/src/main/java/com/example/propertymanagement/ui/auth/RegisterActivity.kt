package com.example.propertymanagement.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityRegisterBinding
import com.example.propertymanagement.ui.home.MainActivity

class RegisterActivity : AppCompatActivity() {

    lateinit var mRegisterBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        var viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mRegisterBinding.registerInfo = viewModel
        viewModel.liveData.observe(this, Observer {
            when (it) {
                AuthViewModel.AuthAction.SUCCESS -> {
                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                        .show()

                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }

                AuthViewModel.AuthAction.FAILURE -> {
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                }

                AuthViewModel.AuthAction.REDIRECT -> {
                    var intent = Intent(
                        this,
                        LoginActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }
            }
        })
    }
}