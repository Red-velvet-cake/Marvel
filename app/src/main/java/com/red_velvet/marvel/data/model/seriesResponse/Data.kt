package com.red_velvet.marvel.data.model.seriesResponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val seriesByCharIdResponses: List<SeriesByCharIdResponse>?,
    @SerializedName("total")
    val total: Int?
)