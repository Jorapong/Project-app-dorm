package com.example.promb

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeActivity : AppCompatActivity() {
    var dormList  = arrayListOf<Dorm>()
    val createClient  = DormAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recycler_view.layoutManager = LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        recycler_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler_view.addItemDecoration(DividerItemDecoration(recycler_view.getContext(), DividerItemDecoration.VERTICAL))

        recycler_view.addOnItemTouchListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                Toast.makeText(
                    applicationContext, "You click on : " +dormList[position].dorm_id,
                    Toast.LENGTH_SHORT).show()
                val dorm = dormList[position]
                val intent = Intent(applicationContext, DormDetailActivity::class.java)
                intent.putExtra("mDid", dorm.dorm_id.toString())
                intent.putExtra("mUid", dorm.user_id.toString())
                intent.putExtra("mDname", dorm.dorm_name)
                intent.putExtra("mDamount", dorm.dorm_amoutRoom.toString())
                intent.putExtra("mImg",dorm.dorm_img)
                intent.putExtra("mprice", dorm.rate_price.toString())
                intent.putExtra("mMap", dorm.map)
                intent.putExtra("mZone", dorm.zone).toString()
                intent.putExtra("mTel", dorm.tel)
                intent.putExtra("mPage", dorm.page)
                startActivity(intent)
            }
        })
    }
    fun showPopupMenu(v:View){
        val popup = PopupMenu(this, btnZone)
        popup.inflate(R.menu.zonemenu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
                item: MenuItem? ->
            when (item!!.itemId) {
                R.id.menu1 -> Clickzone("Kungsadan")
                R.id.menu2 -> Clickzone("LungMor")
                R.id.menu3 -> Clickzone("Columbo")}
            true })
        popup.show()
    }

    override fun onResume() {
        super.onResume()
        callDormData()
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
    fun Clickzone(zone:String){
        if (zone=="Kungsadan") {
            Toast.makeText(applicationContext,zone,Toast.LENGTH_LONG).show()
            dormList.clear()
            createClient.retrieveDbyZone1()
                .enqueue(object : Callback<List<Dorm>> {
                    override fun onResponse(call: Call<List<Dorm>>, response: Response<List<Dorm>>) {
                        response.body()?.forEach {
                            dormList.add(
                                Dorm(
                                    it.dorm_id,
                                    it.user_id,
                                    it.dorm_name,
                                    it.dorm_amoutRoom,
                                    it.dorm_img,
                                    it.rate_price,
                                    it.map,
                                    it.zone,
                                    it.tel,
                                    it.page
                                )
                            )
                        }
                        recycler_view.adapter = DormAdapter(dormList, applicationContext)
                    }

                    override fun onFailure(call: Call<List<Dorm>>, t: Throwable) = t.printStackTrace()
                })
        }
        else if(zone=="LungMor") {
            dormList.clear()
            createClient.retrieveDbyZone2()
                .enqueue(object : Callback<List<Dorm>> {
                    override fun onResponse(call: Call<List<Dorm>>, response: Response<List<Dorm>>) {
                        response.body()?.forEach {
                            dormList.add(
                                Dorm(
                                    it.dorm_id,
                                    it.user_id,
                                    it.dorm_name,
                                    it.dorm_amoutRoom,
                                    it.dorm_img,
                                    it.rate_price,
                                    it.map,
                                    it.zone,
                                    it.tel,
                                    it.page
                                )
                            )
                        }
                        recycler_view.adapter = DormAdapter(dormList, applicationContext)
                    }

                    override fun onFailure(call: Call<List<Dorm>>, t: Throwable) = t.printStackTrace()
                })
        }
        else if(zone=="Columbo") {
            dormList.clear()
            createClient.retrieveDbyZone3()
                .enqueue(object : Callback<List<Dorm>> {
                    override fun onResponse(call: Call<List<Dorm>>, response: Response<List<Dorm>>) {
                        response.body()?.forEach {
                            dormList.add(
                                Dorm(
                                    it.dorm_id,
                                    it.user_id,
                                    it.dorm_name,
                                    it.dorm_amoutRoom,
                                    it.dorm_img,
                                    it.rate_price,
                                    it.map,
                                    it.zone,
                                    it.tel,
                                    it.page
                                )
                            )
                        }
                        recycler_view.adapter = DormAdapter(dormList, applicationContext)
                    }

                    override fun onFailure(call: Call<List<Dorm>>, t: Throwable) = t.printStackTrace()
                })
        }

    }
    fun callDormData() {
        dormList.clear();
        createClient.retrieveDorm()
            .enqueue(object : Callback<List<Dorm>> {
                override fun onResponse(call: Call<List<Dorm>>, response: Response<List<Dorm>>) {
                    response.body()?.forEach {
                        dormList.add(Dorm(it.dorm_id, it.user_id, it.dorm_name,it.dorm_amoutRoom,it.dorm_img,it.rate_price,it.map,it.zone,it.tel,it.page))
                    }
                    recycler_view.adapter = DormAdapter(dormList, applicationContext)
                }
                override fun onFailure(call: Call<List<Dorm>>, t: Throwable) = t.printStackTrace()
            })
    }
}

