package com.example.propertymanagement.ui.auth.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityLoginBinding
import com.example.propertymanagement.ui.auth.AuthViewModel
import com.example.propertymanagement.ui.home.MainActivity


class LoginActivity : AppCompatActivity() {

    lateinit var mLoginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        var viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mLoginBinding.loginInfo = viewModel
        viewModel.liveData.observe(this, Observer {
            when (it) {
                AuthViewModel.AuthAction.SUCCESS -> {
                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                        .show()

                    startActivity(Intent(this.applicationContext, MainActivity::class.java))
                    finish()
                }

                AuthViewModel.AuthAction.FAILURE -> {
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                }
                AuthViewModel.AuthAction.REDIRECT -> {
                    var intent = Intent(
                        this,
                        RegisterActivity::class.java
                    ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }
            }
        })
    }
}