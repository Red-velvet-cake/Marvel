package com.red_velvet.marvel.data.remote.dtos


import com.google.gson.annotations.SerializedName

data class ComicDto(
    @SerializedName("characters")
    val characters: ResourceCollection? = ResourceCollection(),
    @SerializedName("collectedIssues")
    val collectedIssues: List<Resource>? = listOf(),
    @SerializedName("collections")
    val collections: List<ResourceCollection>? = listOf(),
    @SerializedName("creators")
    val creators: ResourceCollection? = ResourceCollection(),
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
    val events: ResourceCollection? = ResourceCollection(),
    @SerializedName("format")
    val format: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("images")
    val images: List<Thumbnail>? = listOf(),
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
    val series: ResourceCollection? = ResourceCollection(),
    @SerializedName("stories")
    val stories: ResourceCollection? = ResourceCollection(),
    @SerializedName("textObjects")
    val textBlurbs: List<TextBlurb>? = listOf(),
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
    val variants: List<Resource>? = listOf()
)