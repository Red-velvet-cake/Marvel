package com.red_velvet.marvel.data.remote

import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.CharactersByEventIdResponse
import com.red_velvet.marvel.data.model.ComicCreatorResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("comics")
    fun getComics(
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("dateDescriptor") dateDescriptor: String? = null
    ): Single<BaseResponse<ComicsResponse>>

    //TODO Add all required filtration query parameters QPs(for search, filter, etc...)


    //TODO Comic details(Comic by id)


    //TODO Comic details(Comic chars by comic id)


    //TODO Comics by Char id


    //TODO Comic creator by comic id
    @GET("comics/{comicId}/creators")
    fun getComicCreatorByComicId(
        @Path("comicId") comicId:Int?=null
    ): Single<BaseResponse<ComicCreatorResponse>>

    //TODO Series(use **search starts with** and **contains** QP)


    //TODO Serie details


    //TODO Serie details(Creators by serie id)


    //TODO Events


    //TODO Events(Characters by event id)
    @GET("events/{eventId}/characters")
    fun getCharactersByEventId(
        @Path("eventId") eventId: Int
    ): Single<BaseResponse<CharactersByEventIdResponse>>


    //TODO Events(Creators by event id)


    //TODO Stories


    //TODO Story by id


    //TODO Story creators by story id


    //TODO Story comics by story id


    //TODO Characters


    //TODO Character(character comics by char id)


    //TODO Character(char series by char id)

}