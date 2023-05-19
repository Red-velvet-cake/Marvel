package com.red_velvet.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DataContainerDto<T>(
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