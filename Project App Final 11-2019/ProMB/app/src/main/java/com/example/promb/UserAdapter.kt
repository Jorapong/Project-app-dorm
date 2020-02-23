package com.example.promb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_showdorm.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(val item : List<User>, val context: Context): RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserAdapter.ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return item.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName?.text="ID :"+item[position].name
        holder.tvID?.text="Name :"+item[position].user_id.toString()
        holder.tvUsername?.text="Username :"+item[position].username
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.tvName
        val tvID = view.tvID
        val tvUsername=view.tvUserName
    }
}