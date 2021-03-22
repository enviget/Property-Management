package com.example.propertymanagement.ui.property

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityPropertyBinding
import com.example.propertymanagement.ui.adapters.AdapterProperty

class PropertyActivity : AppCompatActivity() {

    lateinit var propertyBinding: ActivityPropertyBinding
    lateinit var adapter: AdapterProperty
    val viewModel by viewModels<PropertyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        propertyBinding = DataBindingUtil.setContentView(this, R.layout.activity_property)
        propertyBinding.propertyList = viewModel

        getData()
        viewModel.getProperty()
        viewModel.liveData.observe(this, Observer {
            when (it) {
                PropertyViewModel.PropertyAction.SUCCESS -> {
                    Log.d("abc", "Property list SUCCESS")
                    adapter.setData(viewModel.propertyList)
                }
                PropertyViewModel.PropertyAction.FAILURE -> {
                    Log.d("abc", "Property list FAILURE")
                    Toast.makeText(applicationContext, "Network Error", Toast.LENGTH_LONG).show()
                }
                PropertyViewModel.PropertyAction.REDIRECT ->{
                    startActivity(Intent(this,AddPropertyActivity::class.java))
                    Log.d("abc", "Intent Add new property")
                }
            }
        })
    }


    fun getData() {
        adapter = AdapterProperty(this)
        propertyBinding.recyclerViewProperty.adapter = adapter
        propertyBinding.recyclerViewProperty.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        adapter.setData(viewModel.propertyList)
    }

}