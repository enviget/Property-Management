package com.example.propertymanagement.ui.auth.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.FragmentLandlordRegisterBinding
import com.example.propertymanagement.ui.auth.AuthViewModel
import com.example.propertymanagement.ui.auth.activities.LoginActivity

class LandlordRegisterFragment : Fragment() {
    lateinit var mLandlordBinding: FragmentLandlordRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mLandlordBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_landlord_register,
            container,
            false
        )
        var viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mLandlordBinding.landlordReg = viewModel
        viewModel.liveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                AuthViewModel.AuthAction.SUCCESS -> {
                    Toast.makeText(
                        activity!!.applicationContext,
                        "Register Successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(Intent(activity!!.applicationContext, LoginActivity::class.java))
                    activity!!.finish()
                }

                AuthViewModel.AuthAction.FAILURE -> {
                    Toast.makeText(
                        activity!!.applicationContext,
                        "Register Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                AuthViewModel.AuthAction.REDIRECT -> {
                    var intent = Intent(activity!!.applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    activity!!.finish()
                }
            }
        })
        return mLandlordBinding.root
    }

}