package com.example.promb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dorm_detail.view.*
import kotlinx.android.synthetic.main.item_room_detail.view.*

class RoomAdapter2(val item : List<Room>, val context: Context): RecyclerView.Adapter<RoomAdapter2.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter2.ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.item_room_detail, parent, false)
        return ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return item.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName?.text = item[position].room_name
        holder.tvPrice?.text = item[position].price.toString()
        holder.tvAvariable?.text = item[position].avariable.toString()
        holder.tvAmout?.text = item[position].amout_room.toString()
        holder.tvBed?.text = item[position].bed
        holder.tvAir?.text = item[position].air
        holder.tvFan?.text = item[position].fan
        holder.tvWh?.text = item[position].water_heater
        holder.tvBill?.text = item[position].bill
        holder.tvInsure?.text = item[position].insure
        holder.tvInternet?.text = item[position].internet
        holder.tvAnimal?.text = item[position].animal
        val link = item[position].room_img
        Picasso.get().load(link).into(holder.tvImg)

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.tv_name
        val tvPrice = view.tv_price
        val tvAvariable = view.tv_room_available
        val tvAmout = view.tv_amountroom
        val tvBed = view.tv_bed
        val tvAir = view.tv_air
        val tvFan = view.tv_fan
        val tvWh = view.tv_wh
        val tvBill = view.tv_bill
        val tvInsure = view.tv_insure
        val tvInternet = view.tv_internet
        val tvAnimal = view.tv_animal
        val tvImg=view.roomImage as ImageView
    }
}