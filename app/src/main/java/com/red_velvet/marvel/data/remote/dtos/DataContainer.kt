package com.red_velvet.marvel.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class DataContainer<T>(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("offset")
    val offset: Int? = 0,
    @SerializedName("results")
    val results: T? = null,
    @SerializedName("total")
    val total: Int? = 0
)