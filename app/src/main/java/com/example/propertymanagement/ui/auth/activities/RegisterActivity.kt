package com.example.propertymanagement.ui.auth.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityRegisterBinding
import com.example.propertymanagement.ui.adapters.AdapterFragment
import com.example.propertymanagement.ui.auth.AuthViewModel
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.app_bar.*

class RegisterActivity : AppCompatActivity() {

    lateinit var mRegisterBinding: ActivityRegisterBinding
    val viewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        mRegisterBinding.registerInfo = viewModel

        var adapterFragment = AdapterFragment(supportFragmentManager)
        view_pager.adapter = adapterFragment
        tab_layout.setupWithViewPager(view_pager)
        setupToolbar()

        viewModel.liveData.observe(
            this, Observer {
                when (it) {
                    AuthViewModel.AuthAction.SUCCESS -> {

                        Toast.makeText(
                            this,
                            "Register Successful",
                            Toast.LENGTH_SHORT
                        )
                            .show()

                        startActivity(
                            Intent(
                                this,
                                LoginActivity::class.java
                            )
                        )
                        finish()
                    }

                    AuthViewModel.AuthAction.FAILURE -> {
                        Toast.makeText(
                            this,
                            "Register Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    AuthViewModel.AuthAction.REDIRECT -> {
                        var intent =
                            Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            })
    }

    private fun setupToolbar() {
        toolbar.title = "Register"
        setSupportActionBar(toolbar)
    }
}
