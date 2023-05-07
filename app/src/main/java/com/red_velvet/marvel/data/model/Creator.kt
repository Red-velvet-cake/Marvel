package com.red_velvet.marvel.data.model
import com.google.gson.annotations.SerializedName
data class Creator(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("firstName")
    var firstName: String? = null,
    @SerializedName("middleName")
    var middleName: String? = null,
    @SerializedName("lastName")
    var lastName: String? = null,
    @SerializedName("suffix")
    var suffix: String? = null,
    @SerializedName("fullName")
    var fullName: String? = null,
    @SerializedName("modified")
    var modified: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("resourceURI")
    var resourceURI: String? = null,
    @SerializedName("comics")
    var comics: ResourceCollection? = ResourceCollection(),
    @SerializedName("series")
    var series: ResourceCollection? = ResourceCollection(),
    @SerializedName("stories")
    var stories: ResourceCollection? = ResourceCollection(),
    @SerializedName("events")
    var events: ResourceCollection? = ResourceCollection(),
    @SerializedName("urls")
    var urls: List<Url> = listOf()
)