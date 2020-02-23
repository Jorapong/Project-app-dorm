package com.example.promb

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_adminroom_.*
import com.example.promb.Room
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Adminroom_Activity : AppCompatActivity() {
    var roomList  = arrayListOf<Room>()
    val createClient  = RoomAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminroom_)

        recycler_view.layoutManager = LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        recycler_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler_view.addItemDecoration(DividerItemDecoration(recycler_view.getContext(), DividerItemDecoration.VERTICAL))

        recycler_view.addOnItemTouchListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                Toast.makeText(
                    applicationContext, "You click on : " +roomList[position].room_id,
                    Toast.LENGTH_SHORT).show()
                val room = roomList[position]
                val intent = Intent(applicationContext, edit_del_room::class.java)
                intent.putExtra("mRid", room.room_id.toString())
                intent.putExtra("mDid", room.dorm_id.toString())
                intent.putExtra("mRname", room.room_name)
                intent.putExtra("mPrice", room.price.toString())
                intent.putExtra("mAvariable", room.avariable.toString())
                intent.putExtra("mAmout_room", room.amout_room.toString())
                intent.putExtra("mRoom_img", room.room_img)
                intent.putExtra("mBed", room.bed)
                intent.putExtra("mAir", room.air)
                intent.putExtra("mFan", room.fan)
                intent.putExtra("mWater_heater",room.water_heater)
                intent.putExtra("mBill", room.bill)
                intent.putExtra("mInsure", room.insure)
                intent.putExtra("mInternet", room.internet)
                intent.putExtra("mAnimal", room.animal)
                startActivity(intent)
            }
        })
    }
    override fun onResume() {
        super.onResume()
        callRoomData()
    }

    fun ClickAddRoom(v: View){
        val intent = Intent(this,Insert_roomActivity::class.java)
        startActivity(intent)
    }

    fun callRoomData() {
        roomList.clear();
        createClient.retrieveRoom()
            .enqueue(object : Callback<List<Room>> {
                override fun onResponse(call: Call<List<Room>>, response: Response<List<Room>>) {
                    response.body()?.forEach {
                        roomList.add(Room(it.room_id, it.dorm_id, it.room_name,it.price,it.avariable,it.amout_room,it.room_img,it.bed,it.air,it.fan,it.water_heater,it.bill,it.insure,it.internet,it.animal))
                    }
                    recycler_view.adapter = RoomAdapter(roomList, applicationContext)
                }
                override fun onFailure(call: Call<List<Room>>, t: Throwable) = t.printStackTrace()
            })
    }

}
