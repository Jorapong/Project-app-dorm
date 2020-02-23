package com.example.promb

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RoomAPI {
    @GET("allroom")
    fun retrieveRoom(): Call<List<Room>>

    @GET("room/{dorm_id}")
    fun retrieveRbyD(
        @Path("dorm_id")dorm_id:Int): Call<Room>

    @FormUrlEncoded
    @POST("room")
    fun insertRoom(
        @Field("dorm_id")dorm_id:Int,
        @Field("room_name")room_name:String,
        @Field("price")price:Int,
        @Field("avariable")avariable:Int,
        @Field("amout_room")amout_room:Int,
        @Field("room_img")room_img:String):Call<Room>

    @FormUrlEncoded
    @POST("roomdetail")
    fun insertRoomdetail(
        @Field("bed")bed:String,
        @Field("air")air:String,
        @Field("fan")fan:String,
        @Field("water_heater")water_heater:String,
        @Field("bill")bill:String,
        @Field("insure")insure:String,
        @Field("internet")internet:String,
        @Field("animal")animal:String):Call<Room>

    @FormUrlEncoded
    @PUT("room/{room_id}")
    fun updateRoom(
        @Path ("room_id")room_id:Int,
        @Field("dorm_id")dorm_id:Int,
        @Field("room_name")room_name:String,
        @Field("price")price:Int,
        @Field("avariable")avariable:Int,
        @Field("amout_room")amout_room:Int,
        @Field("room_img")room_img:String):Call<Room>

    @FormUrlEncoded
    @PUT("roomdetail/{room_id}")
    fun updateRoomdetail(
        @Path ("room_id")room_id:Int,
        @Field("bed")bed:String,
        @Field("air")air:String,
        @Field("fan")fan:String,
        @Field("water_heater")water_heater:String,
        @Field("bill")bill:String,
        @Field("insure")insure:String,
        @Field("internet")internet:String,
        @Field("animal")animal:String):Call<Room>

    @DELETE("room/{room_id}")
    fun deleteRoom(
        @Path("room_id")room_id: Int): Call<Room>

    @DELETE("roomdetail/{room_id}")
    fun deleteRoomdetail(
        @Path("room_id")room_id: Int): Call<Room>

    companion object{
        fun create(): RoomAPI {
            val roomClient : RoomAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RoomAPI ::class.java)
            return roomClient
        }
    }
}