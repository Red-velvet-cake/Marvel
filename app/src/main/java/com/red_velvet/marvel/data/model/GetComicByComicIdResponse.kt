package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class GetComicByComicIdResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<ComicsResponse>?,
    @SerializedName("total")
    val total: Int?
)