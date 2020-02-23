package com.example.promb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert_room.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Insert_roomActivity : AppCompatActivity() {
    val createClient: RoomAPI = RoomAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_room)
    }
    fun AddRoom(v:View){
        createClient.insertRoom(
            edit_dormid.text.toString().toInt(),
            edit_roomname.text.toString(),
            edit_price.text.toString().toInt(),
            edit_avariable.text.toString().toInt(),
            edit_Amoutroom.text.toString().toInt(),
            edit_img.text.toString()).enqueue(object : Callback<Room> {
            override fun onResponse(call: Call<Room>, response: Response<Room>){
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext,"Successfully Add", Toast.LENGTH_SHORT).show()
                    finish()
                }else {
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Room>, t: Throwable) {
                Toast.makeText(applicationContext, "Error onFailure "+t.message, Toast. LENGTH_LONG).show()
            }
        })
        createClient.insertRoomdetail(
            edit_bed.text.toString(),
            edit_air.text.toString(),
            edit_fan.text.toString(),
            edit_wh.text.toString(),
            edit_bill.text.toString(),
            edit_insure.text.toString(),
            edit_internet.text.toString(),
            edit_animal.text.toString()).enqueue(object : Callback<Room> {
            override fun onResponse(call: Call<Room>, response: Response<Room>){
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext,"Successfully Add", Toast.LENGTH_SHORT).show()
                    finish()
                }else {
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Room>, t: Throwable) {
                Toast.makeText(applicationContext, "Error onFailure "+t.message, Toast. LENGTH_LONG).show()
            }
        })
    }
    fun Reset(v: View){
        edit_dormid.text.clear()
        edit_roomname.text.clear()
        edit_price.text.clear()
        edit_avariable.text.clear()
        edit_Amoutroom.text.clear()
        edit_img.text.clear()
        edit_bed.text.clear()
        edit_air.text.clear()
        edit_fan.text.clear()
        edit_wh.text.clear()
        edit_bill.text.clear()
        edit_insure.text.clear()
        edit_internet.text.clear()
        edit_animal.text.clear()
    }
}
