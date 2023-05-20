package com.red_velvet.marvel.data.remote.service

import com.red_velvet.marvel.data.remote.dto.BaseResponse
import com.red_velvet.marvel.data.remote.dto.CharacterDto
import com.red_velvet.marvel.data.remote.dto.ComicDto
import com.red_velvet.marvel.data.remote.dto.CreatorDto
import com.red_velvet.marvel.data.remote.dto.EventDto
import com.red_velvet.marvel.data.remote.dto.SeriesDto
import com.red_velvet.marvel.data.remote.dto.StoryDto
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
    ): Single<Response<BaseResponse<List<ComicDto>>>>

    @GET("comics/{comicId}")
    fun getComicDetailById(
        @Path("comicId") comicId: Int
    ): Single<Response<BaseResponse<List<ComicDto>>>>

    @GET("characters/{characterId}/comics")
    fun getComicsByCharacterId(
        @Path("characterId") characterId: Int,
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("dateDescriptor") dateDescriptor: String? = null
    ): Single<Response<BaseResponse<List<ComicDto>>>>

    @GET("comics/{comicId}/creators")
    fun getCreatorByComicId(
        @Path("comicId") comicId: Int? = null,
    ): Single<Response<BaseResponse<List<CreatorDto>>>>

    @GET("series")
    fun getAllSeries(
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("contains") contains: String? = null
    ): Single<Response<BaseResponse<List<SeriesDto>>>>

    @GET("series/{seriesId}")
    fun getSeriesById(
        @Path("seriesId") seriesId: Int
    ): Single<Response<BaseResponse<List<SeriesDto>>>>

    @GET("events")
    fun getAllEvents(
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): Single<Response<BaseResponse<List<EventDto>>>>

    @GET("comics/{comicId}/characters")
    fun getCharactersByComicId(
        @Path("comicId") comicId: Int? = null,
    ): Single<Response<BaseResponse<List<CharacterDto>>>>

    @GET("events/{eventId}/characters")
    fun getCharactersByEventId(
        @Path("eventId") eventId: Int,
    ): Single<Response<BaseResponse<List<CharacterDto>>>>

    @GET("events/{eventId}/creators")
    fun getCreatorsByEventId(
        @Path("eventId") eventId: Int
    ): Single<Response<BaseResponse<List<CreatorDto>>>>

    @GET("stories")
    fun getAllStories(): Single<Response<BaseResponse<List<StoryDto>>>>

    @GET("stories/{storyId}")
    fun getStoryById(
        @Path("storyId") storyId: Int
    ): Single<Response<BaseResponse<List<StoryDto>>>>

    @GET("stories/{storyId}/creators")
    fun getCreatorsByStoryId(
        @Path("storyId") storyId: Int
    ): Single<Response<BaseResponse<List<CreatorDto>>>>

    @GET("stories/{storyId}/comics")
    fun getComicsByStoryId(
        @Path("storyId") storyId: Int
    ): Single<Response<BaseResponse<List<ComicDto>>>>

    @GET("characters")
    fun getAllCharacters(
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): Single<Response<BaseResponse<List<CharacterDto>>>>

    @GET("characters/{characterId}")
    fun getCharacterById(
        @Path("characterId") characterId: Int
    ): Single<Response<BaseResponse<List<CharacterDto>>>>

    @GET("characters/{characterId}/series")
    fun getSeriesByCharacterId(
        @Path("characterId") characterId: Int
    ): Single<Response<BaseResponse<List<SeriesDto>>>>

    @GET("series/{seriesId}/creators")
    fun getCreatorsBySeriesId(
        @Path("seriesId") seriesId: Int
    ): Single<Response<BaseResponse<List<CreatorDto>>>>

    @GET("events/{eventId}")
    fun getEventById(
        @Path("eventId") eventId: Int
    ): Single<Response<BaseResponse<List<EventDto>>>>
}