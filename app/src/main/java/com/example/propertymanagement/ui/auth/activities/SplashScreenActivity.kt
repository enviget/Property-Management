package com.example.propertymanagement.ui.auth.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivitySplashScreenBinding
import com.example.propertymanagement.ui.auth.AuthViewModel
import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {
    var coroutineScope = CoroutineScope(Dispatchers.Main)

    lateinit var mSplashBinding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        var viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mSplashBinding.splashScreen = viewModel

        coroutineScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            finish()
        }


//        viewModel.liveData.observe(this, Observer{
//            if(it == AuthViewModel.AuthAction.REDIRECT){
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//            }
//        })
    }

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }
}