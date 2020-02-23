package com.example.promb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemedit_room.view.*

class RoomAdapter(val item : List<Room>, val context: Context): RecyclerView.Adapter<RoomAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter.ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.itemedit_room, parent, false)
        return ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return item.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvID?.text = "ID :" + item[position].room_id.toString()
        holder.tvName?.text = item[position].room_name
        holder.tvPrice?.text = item[position].price.toString()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvID = view.tvID
        val tvName = view.tvName
        val tvPrice = view.tvPrice
    }
}