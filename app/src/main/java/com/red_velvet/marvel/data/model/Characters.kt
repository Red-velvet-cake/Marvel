package com.red_velvet.marvel.data.model
import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("id")
    var id : Int? = null,
    @SerializedName("name")
    var name : String? = null,
    @SerializedName("description")
    var description : String? = null,
    @SerializedName("modified")
    var modified : String? = null,
    @SerializedName("thumbnail")
    var thumbnail : Thumbnail? = Thumbnail(),
    @SerializedName("resourceURI")
    var resourceURI : String? = null,
    @SerializedName("comics") var comics: Content? = Content(),
    @SerializedName("series") var series : Content? = Content(),
    @SerializedName("stories") var stories : Content? = Content(),
    @SerializedName("events") var events : Content? = Content(),
    @SerializedName("urls") var urls : List<Url> = listOf()
)