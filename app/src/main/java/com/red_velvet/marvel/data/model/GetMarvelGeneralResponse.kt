package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class GetMarvelGeneralResponse<T>(
    @SerializedName("attributionHTML")
    val attributionHTML: String? = "",
    @SerializedName("attributionText")
    val attributionText: String? = "",
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("copyright")
    val copyright: String? = "",
    @SerializedName("data")
    val `data`: GeneralDataResponse<T>? = GeneralDataResponse(),
    @SerializedName("etag")
    val etag: String? = "",
    @SerializedName("status")
    val status: String? = ""
)