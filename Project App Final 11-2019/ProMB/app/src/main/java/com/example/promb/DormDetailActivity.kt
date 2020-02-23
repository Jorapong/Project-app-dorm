package com.example.promb

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dorm_detail.*
import kotlinx.android.synthetic.main.activity_dorm_detail.recycler_view
import kotlinx.android.synthetic.main.item_room_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DormDetailActivity : AppCompatActivity() {
    var roomList  = arrayListOf<Room>()
    val createClient : RoomAPI = RoomAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dorm_detail)

        var mDid = intent.getStringExtra("mDid")
        val mUid = intent.getStringExtra("mUid")
        val mDname = intent.getStringExtra("mDname")
        val mDamount = intent.getStringExtra("mDamount")
        val mImg=intent.getStringExtra("mImg")
        val mprice = intent.getStringExtra("mprice")
        val mMap = intent.getStringExtra("mMap")
        val mZone = intent.getStringExtra("mZone")
        val mTel = intent.getStringExtra("mTel")
        val mPage = intent.getStringExtra("mPage")

        tvDormName.setText(mDname)
        amount.setText(mDamount)
        price.setText(mprice)
        tv_zone.setText(mZone)
        tv_page.setText(mPage)
        tv_tel.setText(mMap)
        tvDid.setText(mDid)
        tv_tel.setText(mTel)
        Picasso.get().load(mImg).into(dormImage)
        Picasso.get().load(mMap).into(map)


        recycler_view.layoutManager = LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        recycler_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler_view.addItemDecoration(DividerItemDecoration(recycler_view.getContext(), DividerItemDecoration.VERTICAL))



    }
    override fun onResume() {
        super.onResume()
        val mDid = intent.getStringExtra("mDid")
        callRoomData(mDid)
    }
    fun ContactDetail(v: View) {
        val builder = AlertDialog.Builder(this)
        val negativeButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(applicationContext, "Close Contact", Toast.LENGTH_SHORT).show()
        }
        builder.setTitle(" Contact Us")
        builder.setMessage("Develop by ZENZOMARU \nDebug Code : ASXIS\nData Base : Tee&Gad\nDoc : Kie&Pia")
        builder.setNegativeButton("Close", DialogInterface.OnClickListener(function = negativeButtonClick))
        builder.show()
    }
    fun ClickShowDetail(v: View){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
    fun ClickShowHome(v: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    fun callRoomData(Did: String) {
        roomList.clear();
        createClient.retrieveRbyD(Did.toString().toInt())
            .enqueue(object : Callback<Room> {
                override fun onResponse(call: Call<Room>, response: Response<Room>) {
                    roomList.add(Room(
                        response.body()?.room_id.toString().toInt(),
                        response.body()?.dorm_id.toString().toInt(),
                        response.body()?.room_name.toString(),
                        response.body()?.price.toString().toInt(),
                        response.body()?.avariable.toString().toInt(),
                        response.body()?.amout_room.toString().toInt(),
                        response.body()?.room_img.toString(),
                        response.body()?.bed.toString(),
                        response.body()?.air.toString(),
                        response.body()?.fan.toString(),
                        response.body()?.water_heater.toString(),
                        response.body()?.bill.toString(),
                        response.body()?.insure.toString(),
                        response.body()?.internet.toString(),
                        response.body()?.animal.toString()))

                    recycler_view.adapter = RoomAdapter2(roomList, applicationContext)
                }
                override fun onFailure(call: Call<Room>, t: Throwable) = t.printStackTrace()
            })
    }
}
