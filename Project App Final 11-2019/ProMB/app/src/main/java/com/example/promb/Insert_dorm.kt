package com.example.promb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert_dorm.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Insert_dorm : AppCompatActivity() {
    val createClient : DormAPI = DormAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_dorm)
    }
    fun AddDorm(v: View){
        createClient.insertDorm(
            edit_userid.text.toString().toInt(),
            edit_dormmname.text.toString(),
            edit_Damount.text.toString().toInt(),
            edit_img.text.toString(),
            edit_Rprice.text.toString().toInt(),
            edit_map.text.toString(),
            edit_Zone.text.toString(),
            edit_Tel.text.toString(),
            edit_page.text.toString()) .enqueue(object : Callback<Dorm> {
            override fun onResponse(call: Call<Dorm>, response: Response<Dorm>){
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext,"Successfully Save", Toast.LENGTH_SHORT).show()
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
    fun Reset(v: View){
        edit_userid.text.clear()
        edit_Damount.text.clear()
        edit_img.text.clear()
        edit_Rprice.text.clear()
        edit_Tel.text.clear()
        edit_Zone.text.clear()
        edit_dormmname.text.clear()
        edit_map.text.clear()
        edit_page.text.clear()
    }

}
