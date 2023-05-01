package com.red_velvet.marvel.data.repository

import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.model.StoryResponse
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
    override fun getComicDetail(comicId: Int): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getComicDetail(comicId)
    }


    //TODO Comic details(Comic chars by comic id)


    //TODO Comics by Char id
    override fun getComicsByCharacterId(characterId: Int): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getComicsByCharacterId(characterId)
    }

    //TODO Comic creator by comic id


    //TODO Series(use **search starts with** and **contains** QP)
    override fun getAllSeries(): Single<BaseResponse<SeriesResponse>> {
        return marvelServiceImpl.getAllSeries()
    }

    override fun getSeriesDetails(seriesId: Int): Single<BaseResponse<SeriesResponse>> {
        return marvelServiceImpl.getSeriesDetails(seriesId)
    }


    //TODO Serie details(Creators by serie id)


    override fun getEvents(): Single<BaseResponse<EventsResponse>> {
        return marvelServiceImpl.getEvents()
    }


    //TODO Events(Characters by event id)
    override fun getCharactersByEventId(eventId: Int): Single<BaseResponse<CharactersResponse>> {
        return marvelServiceImpl.getCharactersByEventId(eventId)
    }




    //TODO Events(Creators by event id)


    //TODO Stories


    //TODO Story by id
    override fun getStory(storyId: Int): Single<BaseResponse<StoryResponse>> {
        return marvelServiceImpl.getStory(storyId)
    }


    //TODO Story creators by story id
    override fun getStoryCreatorsByStoryId(storyId: Int): Single<BaseResponse<CreatorsResponse>> {
        return marvelServiceImpl.getStoryCreatorsByStoryId(storyId)
    }

    override fun getComicsByStoryId(storyId: Int): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getComicsByStoryId(storyId)
    }


    //TODO Characters


    //TODO Character(character comics by char id)


    //TODO Character(char series by char id)

}