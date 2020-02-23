package com.example.promb

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (
    @Expose
    @SerializedName("user_id") val user_id : Int,
    @Expose
    @SerializedName("username") val username : String,
    @Expose
    @SerializedName("password") val password : String,
    @Expose
    @SerializedName("type") val type : Int,
    @Expose
    @SerializedName("email") val email : String,
    @Expose
    @SerializedName("name") val name : String
){}