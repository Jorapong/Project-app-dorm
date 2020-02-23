package com.example.promb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemedit_dorm.view.*

class DormAdapter2(val item : List<Dorm>, val context: Context): RecyclerView.Adapter<DormAdapter2.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DormAdapter2.ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.itemedit_dorm, parent, false)
        return ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: DormAdapter2.ViewHolder, position: Int) {
        holder.tvID?.text = "ID :" + item[position].dorm_id.toString()
        holder.tvName?.text = "Name :" + item[position].dorm_name
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvID = view.tvID
        val tvName = view.tvName
    }
}