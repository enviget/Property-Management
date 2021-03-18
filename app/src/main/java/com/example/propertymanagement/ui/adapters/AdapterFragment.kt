package com.example.propertymanagement.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.propertymanagement.ui.auth.fragments.LandlordRegisterFragment
import com.example.propertymanagement.ui.auth.fragments.TenantRegisterFragment

class AdapterFragment(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LandlordRegisterFragment()
            else -> TenantRegisterFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Landlord"
            else -> "Tenant"
        }
    }

}