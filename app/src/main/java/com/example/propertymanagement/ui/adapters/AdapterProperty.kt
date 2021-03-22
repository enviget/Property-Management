package com.example.propertymanagement.ui.adapters

import android.content.Context
import android.se.omapi.Session
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.propertymanagement.R
import com.example.propertymanagement.data.db.SessionManager
import com.example.propertymanagement.data.models.Property
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_property_list.view.*

class AdapterProperty(val context: Context) : RecyclerView.Adapter<AdapterProperty.MyViewHolder>() {

    var mList: ArrayList<Property> = ArrayList()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(property:Property){

//            val sessionManager = SessionManager()
//            if(property.userId ===sessionManager.getUserInfoByKey(sessionManager.KEY_ID) )
            Glide.with(itemView.context)
                .load(property.image)
                .placeholder(R.drawable.logo_background)
                .into(itemView.image_view_property_list);
            itemView.text_view_adapter_address.text = property.address

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.adapter_property_list, parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    fun setData(list: ArrayList<Property>) {
        Log.d("abc", list.toString())
        mList = list
        notifyDataSetChanged()
        //adapter property list has been set
        Log.d("abc", "set list")
    }
}