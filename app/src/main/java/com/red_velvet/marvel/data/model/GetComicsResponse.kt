package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class GetComicsResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String? = "",
    @SerializedName("attributionText")
    val attributionText: String? = "",
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("copyright")
    val copyright: String? = "",
    @SerializedName("data")
    val `data`: Data? = Data(),
    @SerializedName("etag")
    val etag: String? = "",
    @SerializedName("status")
    val status: String? = ""
)