package com.red_velvet.marvel.data.repository

import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.CharactersByEventIdResponse
import com.red_velvet.marvel.data.remote.MarvelService
import com.red_velvet.marvel.data.remote.RetrofitClient
import io.reactivex.rxjava3.core.Single

class MarvelRepositoryImpl(
    private val marvelServiceImpl: MarvelService
) : MarvelRepository {

    override fun getComics(): Single<BaseResponse<ComicsResponse>> {
        return RetrofitClient.apiService.getComics()
    }

    //TODO Add all required filtration query parameters QPs(for search, filter, etc...)


    //TODO Comic details(Comic by id)


    //TODO Comic details(Comic chars by comic id)


    //TODO Comics by Char id
    override fun getComicsByCharacterId(characterId: Int): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getComicsByCharacterId(characterId)
    }

    //TODO Comic creator by comic id


    //TODO Series(use **search starts with** and **contains** QP)


    //TODO Serie details


    //TODO Serie details(Creators by serie id)


    //TODO Events


    //TODO Events(Characters by event id)
    override fun getCharactersByEventId(eventId:Int): Single<BaseResponse<CharactersByEventIdResponse>> {
        return marvelServiceImpl.getCharactersByEventId(eventId)
    }


    //TODO Events(Creators by event id)


    //TODO Stories


    //TODO Story by id


    //TODO Story creators by story id


    //TODO Story comics by story id


    //TODO Characters


    //TODO Character(character comics by char id)


    //TODO Character(char series by char id)

}