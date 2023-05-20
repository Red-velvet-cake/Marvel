package com.red_velvet.marvel.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("type")
    val type: String? = null
)