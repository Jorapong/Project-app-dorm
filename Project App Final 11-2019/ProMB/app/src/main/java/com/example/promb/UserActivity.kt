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

class UserActivity : AppCompatActivity() {
    var userList  = arrayListOf<User>()
    val createClient  = LoginAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        recycler_view.layoutManager = LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        recycler_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler_view.addItemDecoration(DividerItemDecoration(recycler_view.getContext(), DividerItemDecoration.VERTICAL))

        recycler_view.addOnItemTouchListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                Toast.makeText(
                    applicationContext, "You click on : " +userList[position].user_id,
                    Toast.LENGTH_SHORT).show()
                val user = userList[position]
                val intent = Intent(applicationContext, edit_del_user::class.java)
                intent.putExtra("mUid", user.user_id.toString())
                intent.putExtra("mUsername", user.username)
                intent.putExtra("mPassword", user.password)
                intent.putExtra("mType", user.type.toString())
                intent.putExtra("mEmail",user.email)
                intent.putExtra("mName", user.name)
                startActivity(intent)
            }
        })
    }
    override fun onResume() {
        super.onResume()
        callDormData()
    }

    fun Clicktoadduser(v: View){
        val intent = Intent(this,Insert_UserActivity::class.java)
        startActivity(intent)
    }

    fun callDormData() {
        userList.clear()
        createClient.reretrieveUser()
            .enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    response.body()?.forEach {
                        userList.add(
                            User(
                                it.user_id,
                                it.username,
                                it.password,
                                it.type,
                                it.email,
                                it.name
                            )
                        )
                    }
                    recycler_view.adapter = UserAdapter(userList, applicationContext)
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
    }
}
