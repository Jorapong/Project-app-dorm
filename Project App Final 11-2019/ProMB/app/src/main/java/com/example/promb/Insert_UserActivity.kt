package com.example.promb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_del_user.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Insert_UserActivity : AppCompatActivity() {
    val createClient : LoginAPI = LoginAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert__user)
    }
    fun insertUser(v: View){
        createClient.insertUser(
            edit_username.text.toString(),
            edit_password.text.toString(),
            edit_type.text.toString().toInt(),
            edit_email.text.toString(),
            edit_name.text.toString()).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>){
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext,"Successfully Updated", Toast.LENGTH_SHORT).show()
                    finish()
                }else {
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(applicationContext, "Error onFailure "+t.message, Toast. LENGTH_LONG).show()
            }
        })
    }
    fun Reset(v: View){
        edit_username.text.clear()
        edit_password.text.clear()
        edit_type.text.clear()
        edit_email.text.clear()
        edit_name.text.clear()
    }
}
