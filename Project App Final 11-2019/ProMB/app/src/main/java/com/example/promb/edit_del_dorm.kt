package com.example.promb

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_del_dorm.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class edit_del_dorm : AppCompatActivity() {
    val createClient : DormAPI = DormAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_del_dorm)

        val mDid = intent.getStringExtra("mDid")
        val mUid = intent.getStringExtra("mUid")
        val mDname = intent.getStringExtra("mDname")
        val mDamount = intent.getStringExtra("mDamount")
        val mImg=intent.getStringExtra("mImg")
        val mRprice = intent.getStringExtra("mRprice")
        val mMap = intent.getStringExtra("mMap")
        val mZone = intent.getStringExtra("mZone")
        val mTel = intent.getStringExtra("mTel")
        val mPage = intent.getStringExtra("mPage")

        edit_dormid.setText(mDid)
        edit_dormid.isEnabled = false
        edit_userid.setText(mUid)
        edit_dormmname.setText(mDname)
        edit_Damount.setText(mDamount)
        edit_img.setText(mImg)
        edit_Rprice.setText(mRprice)
        edit_map.setText(mMap)
        edit_Zone.setText(mZone)
        edit_Tel.setText(mTel)
        edit_page.setText(mPage)
    }

        fun saveDorm(v: View){
            createClient.updateDorm(
                edit_dormid.text.toString().toInt(),
                edit_userid.text.toString().toInt(),
                edit_dormmname.text.toString(),
                edit_Damount.text.toString().toInt(),
                edit_img.text.toString(),
                edit_Rprice.text.toString().toInt(),
                edit_map.text.toString(),
                edit_Zone.text.toString(),
                edit_Tel.text.toString(),
                edit_page.text.toString()).enqueue(object : Callback<Dorm> {
                override fun onResponse(call: Call<Dorm>, response: Response<Dorm>){
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext,"Successfully Updated", Toast.LENGTH_SHORT).show()
                        finish()
                    }else {
                        Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<Dorm>, t: Throwable) {
                    Toast.makeText(applicationContext, "Error onFailure "+t.message, Toast. LENGTH_LONG).show()
                }
            })
        }

    fun deleteDorm (v: View){
        val builder = AlertDialog.Builder(this)
        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            createClient.deleteDorm(edit_dormid.text.toString().toInt()).enqueue(object : Callback<Dorm> {
                override fun onResponse(call: Call<Dorm>, response: Response<Dorm>) {
                    if (response.isSuccessful()) {
                        Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<Dorm>, t: Throwable) {
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
