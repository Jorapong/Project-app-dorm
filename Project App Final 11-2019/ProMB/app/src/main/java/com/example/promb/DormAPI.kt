package com.example.promb


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface DormAPI {
    @GET("alldorm")
    fun retrieveDorm(): Call<List<Dorm>>

    @GET("dorm/{user_id}")
    fun retrieveDormbyU(
        @Path("user_id")user_id: Int):Call<Dorm>

    @GET("dormz1")
    fun retrieveDbyZone1(): Call<List<Dorm>>

    @GET("dormz2")
    fun retrieveDbyZone2(): Call<List<Dorm>>

    @GET("dormz3")
    fun retrieveDbyZone3(): Call<List<Dorm>>


    @FormUrlEncoded
    @POST("dorm")
    fun insertDorm(
        @Field("user_id")user_id: Int,
        @Field("dorm_name")dorm_name: String,
        @Field("dorm_amoutRoom")dorm_amoutRoom: Int,
        @Field("dorm_img")dorm_img:String,
        @Field("rate_price")rate_price: Int,
        @Field("zone")zone: String,
        @Field("tel")tel: String,
        @Field("map")map: String,
        @Field("page")page: String): Call<Dorm>

    @FormUrlEncoded
    @PUT("dorm/{dorm_id}")
    fun updateDorm(
        @Path("dorm_id")dorm_id:Int,
        @Field("user_id")user_id: Int,
        @Field("dorm_name")dorm_name: String,
        @Field("dorm_amoutRoom")dorm_amoutRoom: Int,
        @Field("dorm_img")dorm_img:String,
        @Field("rate_price")rate_price: Int,
        @Field("zone")zone: String,
        @Field("tel")tel: String,
        @Field("map")map: String,
        @Field("page")page: String): Call<Dorm>

    @DELETE("dorm/{dorm_id}")
    fun deleteDorm(
        @Path("dorm_id")dorm_id: Int): Call<Dorm>

    companion object{
        fun create(): DormAPI{
            val dormClient : DormAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DormAPI ::class.java)
            return dormClient
        }
    }

}