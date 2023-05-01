package com.red_velvet.marvel.data.remote

import com.red_velvet.marvel.data.model.BaseResponse
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
    fun getComics(
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("dateDescriptor") dateDescriptor: String? = null,
    ): Single<BaseResponse<ComicsResponse>>

    //TODO Add all required filtration query parameters QPs(for search, filter, etc...)


    //TODO Comic details(Comic by id)
    @GET("comics/{comicId}")
    fun getComicDetail(@Path("comicId") comicId: Int): Single<BaseResponse<ComicsResponse>>

    //TODO Comic details(Comic chars by comic id)


    //TODO Comics by Char id
    @GET("characters/{characterId}/comics")
    fun getComicsByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("dateDescriptor") dateDescriptor: String? = null
    ): Single<BaseResponse<ComicsResponse>>

    //TODO Comic creator by comic id
    @GET("comics/{comicId}/creators")
    fun getComicCreatorByComicId(
        @Path("comicId") comicId: Int? = null,
    ): Single<BaseResponse<CreatorsResponse>>

    //TODO Series(use **search starts with** and **contains** QP)
    @GET("series")
    fun getAllSeries(
        @Query("startYear") startYear: Int? = null,
        @Query("contains") contains: String? = null
    ): Single<BaseResponse<SeriesResponse>>


    @GET("series/{seriesId}")
    fun getSeriesDetails(
        @Path("seriesId") seriesId: Int
    ): Single<BaseResponse<SeriesResponse>>


    //TODO Serie details(Creators by serie id)


    @GET("events")
    fun getEvents(
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): Single<BaseResponse<EventsResponse>>


    //TODO Events(Characters by event id)
    @GET("events/{eventId}/characters")
    fun getCharactersByEventId(
        @Path("eventId") eventId: Int,
    ): Single<BaseResponse<CharactersResponse>>


    @GET("events/{eventId}/creators")
    fun getCreatorsByEventId(
        @Path("eventId") eventId: Int
    ): Single<BaseResponse<CreatorsResponse>>


    //TODO Stories


    //TODO Story by id
    fun getStory(storyId: Int): Single<BaseResponse<StoryResponse>>


    //TODO Story creators by story id

    @GET("stories/{storyId}/creators")
    fun getStoryCreatorsByStoryId(
        @Path("storyId") storyId: Int
    )
            : Single<BaseResponse<CreatorsResponse>>


    @GET("stories/{storyId}/comics")
    fun getComicsByStoryId(
        @Path("storyId") storyId: Int
    ): Single<BaseResponse<ComicsResponse>>


    //TODO Characters


    //TODO Character(character comics by char id)


    //TODO Character(char series by char id)
    @GET("characters/{characterId}/series")
    fun getSeriesByCharacterId(
        @Path("characterId") characterId: Int
    ): Single<BaseResponse<BaseResponse<SeriesResponse>>>

}