package com.example.promb

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface LoginAPI {
    @GET("alluser")
    fun reretrieveUser(): Call<List<User>>

    @FormUrlEncoded
    @POST("user")
    fun insertUser(
        @Field("username")username:String,
        @Field("password")password: String,
        @Field("type")type: Int,
        @Field("email")email: String,
        @Field("name")name: String):Call<User>

    @FormUrlEncoded
    @POST("user/{user_id}")
    fun updateUser(
        @Path("user_id")user_id:Int,
        @Field("username")username:String,
        @Field("password")password: String,
        @Field("type")type: Int,
        @Field("email")email: String,
        @Field("name")name: String):Call<User>

    @DELETE("user/{user_id}")
    fun deleteUser(
        @Path("user_id")user_id: Int): Call<User>

    @FormUrlEncoded
    @POST ("login")
    fun loginUser(
        @Field("username")username: String,
        @Field("password")password: String):Call<User>

    companion object{
        fun create(): LoginAPI{
            val userClient : LoginAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginAPI ::class.java)
            return userClient
        }
    }

}