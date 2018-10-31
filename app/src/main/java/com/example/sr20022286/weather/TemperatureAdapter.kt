package com.example.sr20022286.weather

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_list_temperature.view.*


class TemperatureAdapter(val items: MutableList<String>, val context: Context)
    : RecyclerView.Adapter<TemperatureAdapter.ViewHolder>(){


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).
                inflate(R.layout.activity_list_temperature,parent,false))
    }

    override fun onBindViewHolder(holder: TemperatureAdapter.ViewHolder, position: Int) {
        holder.tempType.text = items.get(position)

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tempType = view.tv_list
    }



}
