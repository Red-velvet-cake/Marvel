package com.red_velvet.marvel.data.repository

import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.Characters
import com.red_velvet.marvel.data.model.CharactersByEventIdResponse
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.model.SeriesResponse

import io.reactivex.rxjava3.core.Single

interface MarvelRepository {

    fun getComics(): Single<BaseResponse<ComicsResponse>>

    //TODO Add all required filtration query parameters QPs(for search, filter, etc...)


    //TODO Comic details(Comic by id)
    fun getComicDetail(comicId: Int): Single<BaseResponse<ComicsResponse>>



    //TODO Comic details(Comic chars by comic id)


    //TODO Comics by Char id
    fun getComicsByCharacterId(characterId: Int): Single<BaseResponse<ComicsResponse>>

    //TODO Comic creator by comic id


    //TODO Series(use **search starts with** and **contains** QP)


    fun getSeriesDetails(seriesId: Int): Single<BaseResponse<SeriesResponse>>


    //TODO Serie details(Creators by serie id)


    fun getEvents(): Single<BaseResponse<EventsResponse>>


    //TODO Events(Characters by event id)
    fun getCharactersByEventId(eventId: Int): Single<BaseResponse<CharactersResponse>>


    //TODO Events(Creators by event id)


    //TODO Stories


    //TODO Story by id


    //TODO Story creators by story id


    fun getComicsByStoryId(storyId:Int): Single<BaseResponse<ComicsResponse>>


    //TODO Characters
    fun getCharacters():Single<BaseResponse<Characters>>

    //TODO Character(character comics by char id)


    //TODO Character(char series by char id)

}