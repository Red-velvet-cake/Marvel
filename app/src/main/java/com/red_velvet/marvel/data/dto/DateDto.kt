package com.red_velvet.marvel.data.dto


import com.google.gson.annotations.SerializedName

data class DateDto(
    @SerializedName("date")
    val date: String? = null,
    @SerializedName("type")
    val type: String? = null
)