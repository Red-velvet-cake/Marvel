package com.red_velvet.marvel.data.model
import com.google.gson.annotations.SerializedName
data class SeriesDetailsResponse(
    @SerializedName("id" )
    var id : Int? = null,
    @SerializedName("title")
    var title  : String? = null,
    @SerializedName("description")
    var description : String?= null,
    @SerializedName("resourceURI")
    var resourceURI : String? = null,
    @SerializedName("urls")
    var urls : List<Url> = listOf(),
    @SerializedName("startYear")
    var startYear: Int?= null,
    @SerializedName("endYear")
    var endYear: Int?= null,
    @SerializedName("rating")
    var rating: String?= null,
    @SerializedName("type")
    var type: String?= null,
    @SerializedName("modified")
    var modified : String? = null,
    @SerializedName("thumbnail" )
    var thumbnail : Thumbnail? = Thumbnail(),
    @SerializedName("creators")
    var creators : Content? = Content(),
    @SerializedName("characters")
    var characters  : Content? = Content(),
    @SerializedName("stories")
    var stories : Content?= Content(),
    @SerializedName("comics")
    var comics: Content? = Content(),
    @SerializedName("events")
    var events : Content?= Content(),
    @SerializedName("next")
    var next : String?= null,
    @SerializedName("previous")
    var previous: String? = null
)
