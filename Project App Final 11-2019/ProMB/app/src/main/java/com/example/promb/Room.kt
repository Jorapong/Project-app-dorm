package com.example.promb

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Room(
    @Expose
    @SerializedName("room_id") val room_id: Int,
    @Expose
    @SerializedName("dorm_id") val dorm_id: Int,
    @Expose
    @SerializedName("room_name") val room_name: String,
    @Expose
    @SerializedName("price") val price: Int,
    @Expose
    @SerializedName("avariable") val avariable: Int,
    @Expose
    @SerializedName("amout_room") val amout_room: Int,
    @Expose
    @SerializedName("room_img") val room_img: String,
    @Expose
    @SerializedName("bed") val bed: String,
    @Expose
    @SerializedName("air") val air: String,
    @Expose
    @SerializedName("fan") val fan: String,
    @Expose
    @SerializedName("water_heater") val water_heater: String,
    @Expose
    @SerializedName("bill") val bill: String,
    @Expose
    @SerializedName("insure") val insure: String,
    @Expose
    @SerializedName("internet") val internet: String,
    @Expose
    @SerializedName("animal") val animal: String

    ) {
}