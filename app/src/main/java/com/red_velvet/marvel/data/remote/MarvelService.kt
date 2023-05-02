package com.red_velvet.marvel.data.remote
import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.charsbycomicid.CharsByComicIdResponse
import com.red_velvet.marvel.data.model.Characters
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.model.StoryResponse

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("comics")
    fun getAllComics(
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("dateDescriptor") dateDescriptor: String? = null,
    ): Single<BaseResponse<ComicsResponse>>

    @GET("comics/{comicId}")
    fun getComicDetail(@Path("comicId") comicId: Int): Single<BaseResponse<ComicsResponse>>
    @GET("characters/{characterId}/comics")
    fun getComicsByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("dateDescriptor") dateDescriptor: String? = null
    ): Single<BaseResponse<ComicsResponse>>
    @GET("comics/{comicId}/creators")
    fun getComicCreatorByComicId(
        @Path("comicId") comicId: Int? = null,
    ): Single<BaseResponse<CreatorsResponse>>
    @GET("series")
    fun getAllSeries(
        @Query("startYear") startYear: Int? = null,
        @Query("contains") contains: String? = null
    ): Single<BaseResponse<SeriesResponse>>
    @GET("series/{seriesId}")
    fun getSerieDetails(
        @Path("seriesId") seriesId: Int
    ): Single<BaseResponse<SeriesResponse>>
    @GET("events")
    fun getAllEvents(
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): Single<BaseResponse<EventsResponse>>
   @GET("comics/{comicId}/characters")
    fun getCharsByComicId(
       @Path("comicId") comicId:Int?=null,
    ):Single<BaseResponse<Characters>>

    @GET("events/{eventId}/characters")
    fun getCharactersByEventId(
        @Path("eventId") eventId: Int,
    ): Single<BaseResponse<CharactersResponse>>

    @GET("events/{eventId}/creators")
    fun getCreatorsByEventId(
        @Path("eventId") eventId: Int
    ): Single<BaseResponse<CreatorsResponse>>

    @GET("stories")
    fun getAllStories(): Single<BaseResponse<StoryResponse>>

    @GET("stories/{storyId}")
    fun getStory(storyId: Int): Single<BaseResponse<StoryResponse>>

    @GET("stories/{storyId}/creators")
    fun getStoryCreatorsByStoryId(
        @Path("storyId") storyId: Int
    ): Single<BaseResponse<CreatorsResponse>>

    @GET("stories/{storyId}/comics")
    fun getComicsByStoryId(
        @Path("storyId") storyId: Int
    ): Single<BaseResponse<ComicsResponse>>

    @GET("characters")
    fun getCharacters(): Single<BaseResponse<Characters>>

    @GET("characters/{characterId}/series")
    fun getSeriesByCharacterId(
        @Path("characterId") characterId: Int
    ): Single<BaseResponse<BaseResponse<SeriesResponse>>>

    @GET("series/{seriesId}/creators")
    fun getSerieCreatorsBySeriesId(
        @Path("seriesId") seriesId: Int
    ): Single<BaseResponse<CreatorsResponse>>

    @GET("events/{eventId}")
    fun getEventDetails(
        @Path("eventId") eventId:Int
    ):Single<BaseResponse<EventsResponse>>
}