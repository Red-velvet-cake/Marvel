package com.red_velvet.marvel.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ComicDto(
    @SerializedName("characters")
    val characters: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("collectedIssues")
    val collectedIssues: List<ResourceDto>? = listOf(),
    @SerializedName("collections")
    val collections: List<ResourceCollectionDto>? = listOf(),
    @SerializedName("creators")
    val creators: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("dates")
    val dates: List<DateDto>? = listOf(),
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("diamondCode")
    val diamondCode: String? = "",
    @SerializedName("digitalId")
    val digitalId: Int? = 0,
    @SerializedName("ean")
    val ean: String? = "",
    @SerializedName("events")
    val events: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("format")
    val format: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("images")
    val images: List<ThumbnailDto>? = listOf(),
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
    val prices: List<PriceDto>? = listOf(),
    @SerializedName("resourceURI")
    val resourceURI: String? = "",
    @SerializedName("series")
    val series: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("stories")
    val stories: ResourceCollectionDto? = ResourceCollectionDto(),
    @SerializedName("textObjects")
    val textBlurbs: List<TextBlurbDto>? = listOf(),
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailDto? = ThumbnailDto(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("upc")
    val upc: String? = "",
    @SerializedName("urls")
    val urls: List<UrlDto>? = listOf(),
    @SerializedName("variantDescription")
    val variantDescription: String? = "",
    @SerializedName("variants")
    val variants: List<ResourceDto>? = listOf()
)