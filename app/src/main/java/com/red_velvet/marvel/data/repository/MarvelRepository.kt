package com.red_velvet.marvel.data.repository

import com.red_velvet.marvel.data.model.GetComicsResponse
import com.red_velvet.marvel.data.model.GetSeriesDetails
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {

    fun getComics(): Single<GetComicsResponse>

    //TODO Add all required filtration query parameters QPs(for search, filter, etc...)


    //TODO Comic details(Comic by id)


    //TODO Comic details(Comic chars by comic id)


    //TODO Comics by Char id


    //TODO Comic creator by comic id


    //TODO Series(use **search starts with** and **contains** QP)


    //TODO Serie details


    //TODO Serie details(Creators by serie id)
    fun getSeriesDetailsBySeriesId(seriesId:Int): Single<GetSeriesDetails>

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