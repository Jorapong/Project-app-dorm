package com.example.promb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_showdorm.view.*
import com.squareup.picasso.Picasso

class DormAdapter (val item : List<Dorm>, val context: Context): RecyclerView.Adapter<DormAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DormAdapter.ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.item_showdorm, parent, false)
        return ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val zonetype = ""
        holder.tvName?.text = item[position].dorm_name
        holder.tvZone?.text = item[position].zone
        holder.tvPrice?.text = item[position].rate_price.toString()
        holder.tvAmount?.text = item[position].dorm_amoutRoom.toString()
        val link = item[position].dorm_img
        Picasso.get().load(link).into(holder.tvImg)

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.tvDormName
        val tvZone = view.tv_zone
        val tvPrice = view.tv_price
        val tvAmount = view.tv_amount
        val tvImg = view.dormImage as ImageView
    }
}