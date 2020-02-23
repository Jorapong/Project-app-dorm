package com.example.promb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_admin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminActivity : AppCompatActivity() {
    var dormList  = arrayListOf<Dorm>()
    val createClient  = DormAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)


        recycler_view.layoutManager = LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        recycler_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler_view.addItemDecoration(DividerItemDecoration(recycler_view.getContext(), DividerItemDecoration.VERTICAL))

        recycler_view.addOnItemTouchListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                Toast.makeText(
                    applicationContext, "You click on : " +dormList[position].dorm_id,
                    Toast.LENGTH_SHORT).show()
                val dorm = dormList[position]
                val intent = Intent(applicationContext, edit_del_dorm::class.java)
                intent.putExtra("mDid", dorm.dorm_id.toString())
                intent.putExtra("mUid", dorm.user_id.toString())
                intent.putExtra("mDname", dorm.dorm_name)
                intent.putExtra("mDamount", dorm.dorm_amoutRoom.toString())
                intent.putExtra("mImg",dorm.dorm_img)
                intent.putExtra("mRprice", dorm.rate_price.toString())
                intent.putExtra("mMap", dorm.map)
                intent.putExtra("mZone", dorm.zone)
                intent.putExtra("mTel", dorm.tel)
                intent.putExtra("mPage", dorm.page)
                startActivity(intent)
            }
        })
    }
    override fun onResume() {
        super.onResume()
        val mUid=intent.getStringExtra("mUid").toString().toInt()
        val mType=intent.getStringExtra("mType").toString().toInt()
        callDormData(mUid,mType)
    }
    fun ClickShowRoom(v: View){
        val intent = Intent(this,Adminroom_Activity::class.java)
        startActivity(intent)
    }
    fun ClickDorminsert(v: View){
        val intent = Intent(this,Insert_dorm::class.java)
        startActivity(intent)
    }
    fun Clicktoadduser(v: View){
        val intent = Intent(this,UserActivity::class.java)
        startActivity(intent)
    }
    fun callDormData(userid:Int,type:Int) {
        dormList.clear()
        if (type==0){
            createClient.retrieveDorm()
                .enqueue(object : Callback<List<Dorm>> {
                    override fun onResponse(call: Call<List<Dorm>>, response: Response<List<Dorm>>) {
                        response.body()?.forEach {
                            dormList.add(Dorm(it.dorm_id, it.user_id, it.dorm_name,it.dorm_amoutRoom,it.dorm_img,it.rate_price,it.map,it.zone,it.tel,it.page))
                        }
                        recycler_view.adapter = DormAdapter2(dormList, applicationContext)
                    }
                    override fun onFailure(call: Call<List<Dorm>>, t: Throwable){
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
        } else if (type==1){
            btnUser.setEnabled(false);
            createClient.retrieveDormbyU(userid)
                .enqueue(object : Callback<Dorm> {

                    override fun onResponse(call: Call<Dorm>, response: Response<Dorm>) {
                            dormList.add(Dorm(response.body()?.dorm_id.toString().toInt(),
                                response.body()?.user_id.toString().toInt(),
                                response.body()?.dorm_name.toString(),
                                response.body()?.dorm_amoutRoom.toString().toInt(),
                                response.body()?.dorm_img.toString(),
                                response.body()?.rate_price.toString().toInt(),
                                response.body()?.map.toString(),
                                response.body()?.zone.toString(),
                                response.body()?.tel.toString(),
                                response.body()?.page.toString()))

                        recycler_view.adapter = DormAdapter2(dormList, applicationContext)
                    }
                    override fun onFailure(call: Call<Dorm>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
        }
    }
}


interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}
fun RecyclerView.addOnItemTouchListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
            view?.setOnClickListener(null)
        }
        override fun onChildViewAttachedToWindow(view: View) {
            view?.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }
    })
}

