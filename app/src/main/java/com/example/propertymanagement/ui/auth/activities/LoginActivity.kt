package com.example.propertymanagement.ui.auth.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityLoginBinding
import com.example.propertymanagement.ui.auth.AuthViewModel
import com.example.propertymanagement.ui.home.MainActivity
import kotlinx.android.synthetic.main.app_bar.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    lateinit var mLoginBinding: ActivityLoginBinding
    val viewModel by viewModels<AuthViewModel>()

//    @Inject
//    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        if (sessionManager.isUserLoggedIn()) {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mLoginBinding.loginInfo = viewModel
        viewModel.loggedIn()
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
        setupToolbar()
    }

    private fun setupToolbar() {
        toolbar.title = "Login"
        setSupportActionBar(toolbar)
    }

}