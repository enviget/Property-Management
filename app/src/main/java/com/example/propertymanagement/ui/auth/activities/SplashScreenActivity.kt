package com.example.propertymanagement.ui.auth.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.propertymanagement.R
import com.example.propertymanagement.data.utils.MyApplication

import kotlinx.coroutines.*

class SplashScreenActivity : AppCompatActivity() {

    var coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        MyApplication.mContext = this
        coroutineScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }
}