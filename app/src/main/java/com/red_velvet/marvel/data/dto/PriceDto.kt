package com.red_velvet.marvel.data.dto


import com.google.gson.annotations.SerializedName

data class PriceDto(
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("type")
    val type: String? = null
)