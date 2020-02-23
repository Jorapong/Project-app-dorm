package com.example.promb

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_del_dorm.*
import kotlinx.android.synthetic.main.activity_edit_del_room.*
import kotlinx.android.synthetic.main.activity_edit_del_room.edit_dormid
import kotlinx.android.synthetic.main.activity_edit_del_room.edit_img
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class edit_del_room : AppCompatActivity() {
    val createClient  = RoomAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_del_room)

        val mRid = intent.getStringExtra("mRid")
        val mDid = intent.getStringExtra("mDid")
        val mRname = intent.getStringExtra("mRname")
        val mPrice = intent.getStringExtra("mPrice")
        val mAvariable = intent.getStringExtra("mAvariable")
        val mAmout_room = intent.getStringExtra("mAmout_room")
        val mRoom_img = intent.getStringExtra("mRoom_img")
        val mBed = intent.getStringExtra("mBed")
        val mAir = intent.getStringExtra("mAir")
        val mFan = intent.getStringExtra("mFan")
        val mWater_heater = intent.getStringExtra("mWater_heater")
        val mBill = intent.getStringExtra("mBill")
        val mInsure = intent.getStringExtra("mInsure")
        val mInternet = intent.getStringExtra("mInternet")
        val mAnimal = intent.getStringExtra("mAnimal")

        edit_roomid.setText(mRid)
        edit_roomid.isEnabled = false
        edit_dormid.setText(mDid)
        edit_roomname.setText(mRname)
        edit_price.setText(mPrice)
        edit_avariable.setText(mAvariable)
        edit_Amoutroom.setText(mAmout_room)
        edit_img.setText(mRoom_img)
        edit_bed.setText(mBed)
        edit_air.setText(mAir)
        edit_fan.setText(mFan)
        edit_wh.setText(mWater_heater)
        edit_bill.setText(mBill)
        edit_insure.setText(mInsure)
        edit_internet.setText(mInternet)
        edit_animal.setText(mAnimal)

    }
     fun saveRoom(v:View){
         createClient.updateRoom(
             edit_roomid.text.toString().toInt(),
             edit_dormid.text.toString().toInt(),
             edit_roomname.text.toString(),
             edit_price.text.toString().toInt(),
             edit_avariable.text.toString().toInt(),
             edit_Amoutroom.text.toString().toInt(),
             edit_img.text.toString()).enqueue(object : Callback<Room> {
             override fun onResponse(call: Call<Room>, response: Response<Room>){
                 if (response.isSuccessful) {
                     Toast.makeText(applicationContext,"Successfully Updated", Toast.LENGTH_SHORT).show()
                     finish()
                 }else {
                     Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                 }
             }
             override fun onFailure(call: Call<Room>, t: Throwable) {
                 Toast.makeText(applicationContext, "Error onFailure "+t.message, Toast. LENGTH_LONG).show()
             }
         })
         createClient.updateRoomdetail(
             edit_roomid.text.toString().toInt(),
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
                     Toast.makeText(applicationContext,"Successfully Updated", Toast.LENGTH_SHORT).show()
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
    fun deleteRoom(v: View){
        val builder = AlertDialog.Builder(this)
        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            createClient.deleteRoom(edit_roomid.text.toString().toInt()).enqueue(object : Callback<Room> {
                override fun onResponse(call: Call<Room>, response: Response<Room>) {
                    if (response.isSuccessful()) {
                        Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<Room>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
            })

            createClient.deleteRoomdetail(edit_roomid.text.toString().toInt()).enqueue(object : Callback<Room> {
                override fun onResponse(call: Call<Room>, response: Response<Room>) {
                    if (response.isSuccessful()) {
                        Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<Room>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
            finish()


        }
        val negativeButtonClick = { dialog: DialogInterface, which :Int-> dialog.cancel() }
        builder.setTitle("Warning")
        builder.setMessage("Do you want to delete the Dorm?")
        builder.setPositiveButton("No", negativeButtonClick)
        builder.setNegativeButton("Yes", positiveButtonClick)
        builder.show()
    }
}
