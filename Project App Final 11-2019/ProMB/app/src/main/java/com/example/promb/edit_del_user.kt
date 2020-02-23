package com.example.promb

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_del_dorm.*
import kotlinx.android.synthetic.main.activity_edit_del_user.*
import kotlinx.android.synthetic.main.activity_edit_del_user.edit_userid
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class edit_del_user : AppCompatActivity() {
    val createClient : LoginAPI = LoginAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_del_user)

        val mUid = intent.getStringExtra("mUid")
        val mUsername = intent.getStringExtra("mUsername")
        val mPassword = intent.getStringExtra("mPassword")
        val mType = intent.getStringExtra("mType")
        val mEmail = intent.getStringExtra("mEmail")
        val mName = intent.getStringExtra("mName")

        edit_userid.setText(mUid)
        edit_userid.isEnabled = false
        edit_username.setText(mUsername)
        edit_password.setText(mPassword)
        edit_type.setText(mType)
        edit_email.setText(mEmail)
        edit_name.setText(mName)
    }
    fun saveUser(v: View){
        createClient.updateUser(
            edit_userid.text.toString().toInt(),
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
    fun deleteUser(v: View){
        val builder = AlertDialog.Builder(this)
        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            createClient.deleteUser(edit_userid.text.toString().toInt()).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful()) {
                        Toast.makeText(applicationContext, "Successfully Deleted", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
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
