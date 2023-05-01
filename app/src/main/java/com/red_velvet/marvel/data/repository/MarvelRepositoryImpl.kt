package com.red_velvet.marvel.data.repository

import com.red_velvet.marvel.data.model.GetComicDetailResponse
import com.red_velvet.marvel.data.model.GetComicsResponse
import com.red_velvet.marvel.data.remote.MarvelService
import com.red_velvet.marvel.data.remote.RetrofitClient
import io.reactivex.rxjava3.core.Single

class MarvelRepositoryImpl(
    private val marvelServiceImpl: MarvelService
) : MarvelRepository {

    override fun getComics(): Single<GetComicsResponse> {
        return RetrofitClient.apiService.getComics()
    }

    //TODO Add all required filtration query parameters QPs(for search, filter, etc...)


    //TODO Comic details(Comic by id)
    override fun getComicDetail(comicId: Int): Single<GetComicDetailResponse> {
        return marvelServiceImpl.getComicDetail(comicId)
    }


    //TODO Comic details(Comic chars by comic id)


    //TODO Comics by Char id


    //TODO Comic creator by comic id


    //TODO Series(use **search starts with** and **contains** QP)


    //TODO Serie details


    //TODO Serie details(Creators by serie id)


    //TODO Events


    //TODO Events(Characters by event id)


    //TODO Events(Creators by event id)


    //TODO Stories


    //TODO Story by id


    //TODO Story creators by story id


    //TODO Story comics by story id


    //TODO Characters


    //TODO Character(character comics by char id)


    //TODO Character(char series by char id)

}