package com.red_velvet.marvel.data.model
import com.google.gson.annotations.SerializedName
data class SerieCreatorsResponse(
    @SerializedName("id" )
    var id : Int? = null,
    @SerializedName("firstName")
    var firstName : String? = null,
    @SerializedName("middleName")
    var middleName : String? = null,
    @SerializedName("lastName")
    var lastName : String? = null,
    @SerializedName("suffix")
    var suffix : String? = null,
    @SerializedName("fullName")
    var fullName : String? = null,
    @SerializedName("resourceURI")
    var resourceURI : String? = null,
    @SerializedName("urls")
    var urls : List<Url> = listOf(),
    @SerializedName("modified")
    var modified : String? = null,
    @SerializedName("thumbnail" )
    var thumbnail : Thumbnail? = Thumbnail(),
    @SerializedName("series")
    var series : Content?= Content(),
    @SerializedName("stories")
    var stories : Content?= Content(),
    @SerializedName("comics")
    var comics: Content? = Content(),
    @SerializedName("events")
    var events : Content?= Content(),
)