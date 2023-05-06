package com.red_velvet.marvel.data.remote

import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.model.MarvelResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.model.StoryResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("comics")
    fun getAllComics(
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("dateDescriptor") dateDescriptor: String? = null,
    ): Single<Response<MarvelResponse<List<ComicsResponse>>>>

    @GET("comics/{comicId}")
    fun getComicDetail(@Path("comicId") comicId: Int): Single<Response<MarvelResponse<List<ComicsResponse>>>>

    @GET("characters/{characterId}/comics")
    fun getComicsByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("dateDescriptor") dateDescriptor: String? = null
    ): Single<Response<MarvelResponse<List<ComicsResponse>>>>

    @GET("comics/{comicId}/creators")
    fun getComicCreatorByComicId(
        @Path("comicId") comicId: Int? = null,
    ): Single<Response<MarvelResponse<List<CreatorsResponse>>>>

    @GET("series")
    fun getAllSeries(
        @Query("startYear") startYear: Int? = null,
        @Query("contains") contains: String? = null
    ): Single<Response<MarvelResponse<List<SeriesResponse>>>>

    @GET("series/{seriesId}")
    fun getSerieDetails(
        @Path("seriesId") seriesId: Int
    ): Single<Response<MarvelResponse<List<SeriesResponse>>>>

    @GET("events")
    fun getAllEvents(
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): Single<Response<MarvelResponse<List<EventsResponse>>>>

    @GET("comics/{comicId}/characters")
    fun getCharsByComicId(
        @Path("comicId") comicId: Int? = null,
    ): Single<Response<MarvelResponse<List<CharactersResponse>>>>

    @GET("events/{eventId}/characters")
    fun getCharactersByEventId(
        @Path("eventId") eventId: Int,
    ): Single<Response<MarvelResponse<List<CharactersResponse>>>>

    @GET("events/{eventId}/creators")
    fun getCreatorsByEventId(
        @Path("eventId") eventId: Int
    ): Single<Response<MarvelResponse<List<CreatorsResponse>>>>

    @GET("stories")
    fun getAllStories(): Single<Response<MarvelResponse<List<StoryResponse>>>>

    @GET("stories/{storyId}")
    fun getStory(@Path("storyId")storyId: Int): Single<Response<MarvelResponse<List<StoryResponse>>>>

    @GET("stories/{storyId}/creators")
    fun getStoryCreatorsByStoryId(
        @Path("storyId") storyId: Int
    ): Single<Response<MarvelResponse<List<CreatorsResponse>>>>

    @GET("stories/{storyId}/comics")
    fun getComicsByStoryId(
        @Path("storyId") storyId: Int
    ): Single<Response<MarvelResponse<List<ComicsResponse>>>>

    @GET("characters")
    fun getCharacters(): Single<Response<MarvelResponse<List<CharactersResponse>>>>

    @GET("characters/{characterId}")
    fun getCharacterByCharacterId(
        @Path("characterId") characterId: Int
    ): Single<Response<MarvelResponse<List<CharactersResponse>>>>

    @GET("characters/{characterId}/series")
    fun getSeriesByCharacterId(
        @Path("characterId") characterId: Int
    ): Single<Response<MarvelResponse<List<SeriesResponse>>>>

    @GET("series/{seriesId}/creators")
    fun getSerieCreatorsBySeriesId(
        @Path("seriesId") seriesId: Int
    ):Single<Response<MarvelResponse<List<CreatorsResponse>>>>

    @GET("events/{eventId}")
    fun getEventDetails(
        @Path("eventId") eventId:Int
    ): Single<Response<MarvelResponse<List<EventsResponse>>>>

    @GET("characters")
    fun searchCharacters(
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): Single<Response<MarvelResponse<List<CharactersResponse>>>>
}