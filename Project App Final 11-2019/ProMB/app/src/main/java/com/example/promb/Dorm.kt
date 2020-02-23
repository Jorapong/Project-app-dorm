package com.example.promb

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Dorm (
    @Expose
    @SerializedName("dorm_id") val dorm_id: Int,

    @Expose
    @SerializedName("user_id") val user_id: Int,

    @Expose
    @SerializedName("dorm_name") val dorm_name: String,

    @Expose
    @SerializedName("dorm_amoutRoom") val dorm_amoutRoom: Int,

    @Expose
    @SerializedName("dorm_img") val dorm_img: String,

    @Expose
    @SerializedName("rate_price") val rate_price: Int,

    @Expose
    @SerializedName("map") val map: String,

    @Expose
    @SerializedName("zone") val zone: String,

    @Expose
    @SerializedName("tel") val tel: String,

    @Expose
    @SerializedName("page") val page: String){}