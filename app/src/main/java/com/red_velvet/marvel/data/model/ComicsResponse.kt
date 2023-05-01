package com.red_velvet.marvel.data.model


import com.google.gson.annotations.SerializedName

data class ComicsResponse(
    @SerializedName("characters")
    val characters: Content? = Content(),
    @SerializedName("collectedIssues")
    val collectedIssues: List<CollectedIssue>? = listOf(),
    @SerializedName("collections")
    val collections: List<Any>? = listOf(),
    @SerializedName("creators")
    val creators: Content? = Content(),
    @SerializedName("dates")
    val dates: List<Date>? = listOf(),
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("diamondCode")
    val diamondCode: String? = "",
    @SerializedName("digitalId")
    val digitalId: Int? = 0,
    @SerializedName("ean")
    val ean: String? = "",
    @SerializedName("events")
    val events: Content? = Content(),
    @SerializedName("format")
    val format: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("images")
    val images: List<Image>? = listOf(),
    @SerializedName("isbn")
    val isbn: String? = "",
    @SerializedName("issn")
    val issn: String? = "",
    @SerializedName("issueNumber")
    val issueNumber: Int? = 0,
    @SerializedName("modified")
    val modified: String? = "",
    @SerializedName("pageCount")
    val pageCount: Int? = 0,
    @SerializedName("prices")
    val prices: List<Price>? = listOf(),
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("series")
    val series: Content? = Content(),
    @SerializedName("stories")
    val stories: Content? = Content(),
    @SerializedName("textObjects")
    val textObjects: List<TextObject>? = listOf(),
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("upc")
    val upc: String? = "",
    @SerializedName("urls")
    val urls: List<Url>? = listOf(),
    @SerializedName("variantDescription")
    val variantDescription: String? = "",
    @SerializedName("variants")
    val variants: List<Variant>? = listOf()
)