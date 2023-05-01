package com.red_velvet.marvel.data.remote

import com.red_velvet.marvel.data.model.GetComicsResponse
import com.red_velvet.marvel.data.model.Stories
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("comics")
    fun getComics(
        @Query("titleStartsWith") titleStartsWith: String? = null,
        @Query("dateDescriptor") dateDescriptor: String? = null
    ): Single<GetComicsResponse>

    //TODO Add all required filtration query parameters QPs(for search, filter, etc...)


    //TODO Comic details(Comic by id)


    //TODO Comic details(Comic chars by comic id)


    //TODO Comics by Char id


    //TODO Comic creator by comic id


    //TODO Series(use **search starts with** and **contains** QP)


    //TODO Serie details


    //TODO Serie details(Creators by serie id)


    //TODO Events


    //TODO Events(Characters by event id)


    //TODO Events(Creators by event id)

    @Get("stories")
    fun getStories(
        @Query("events") events: Int? = null,
        @Query("comics") comics: Int? = null,
        @Query("characters") characters: Int? = null,
        @Query("creators") creators: Int? = null,
        @Query("series") series: Int? = null
    ): Single<Stories>


    //TODO Story by id


    //TODO Story creators by story id


    //TODO Story comics by story id


    //TODO Characters


    //TODO Character(character comics by char id)


    //TODO Character(char series by char id)

}