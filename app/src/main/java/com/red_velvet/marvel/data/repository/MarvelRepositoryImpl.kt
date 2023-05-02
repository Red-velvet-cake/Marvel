package com.red_velvet.marvel.data.repository


import com.red_velvet.marvel.data.model.BaseResponse

import com.red_velvet.marvel.data.model.charsbycomicid.CharsByComicIdResponse
import com.red_velvet.marvel.data.model.Characters
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.model.StoryResponse
import com.red_velvet.marvel.data.remote.MarvelService
import com.red_velvet.marvel.data.util.applySchedulers
import com.red_velvet.marvel.ui.MarvelRepository
import io.reactivex.rxjava3.core.Single

class MarvelRepositoryImpl(
    private val marvelServiceImpl: MarvelService
) : MarvelRepository {

    override fun getComics(): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getAllComics()
            .applySchedulers()
    }

    override fun getComicDetail(comicId: Int): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getComicDetail(comicId)
            .applySchedulers()
    }

    override fun getComicsByCharacterId(characterId: Int): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getComicsByCharacterId(characterId)
            .applySchedulers()
    }

    override fun getComicCreatorByComicId(comicId: Int): Single<BaseResponse<CreatorsResponse>> {
        return marvelServiceImpl.getComicCreatorByComicId(comicId)
            .applySchedulers()
    }

    override fun getAllSeries(): Single<BaseResponse<SeriesResponse>> {
        return marvelServiceImpl.getAllSeries()
            .applySchedulers()
    }

    override fun getCharsByComicId(comicId: Int): Single<BaseResponse<Characters>> {
        return marvelServiceImpl.getCharsByComicId(comicId)
    }
    override fun getSeriesDetails(seriesId: Int): Single<BaseResponse<SeriesResponse>> {
        return marvelServiceImpl.getSerieDetails(seriesId)
            .applySchedulers()
    }

    override fun getEvents(): Single<BaseResponse<EventsResponse>> {
        return marvelServiceImpl.getAllEvents()
            .applySchedulers()

    }

    override fun getCharactersByEventId(eventId: Int): Single<BaseResponse<CharactersResponse>> {
        return marvelServiceImpl.getCharactersByEventId(eventId)
            .applySchedulers()
    }

    override fun getCharacters(): Single<BaseResponse<Characters>> {
        return marvelServiceImpl.getCharacters()
            .applySchedulers()
    }

    override fun getCreatorsByEventId(eventId: Int): Single<BaseResponse<CreatorsResponse>> {
        return marvelServiceImpl.getCreatorsByEventId(eventId)
            .applySchedulers()
    }

    override fun getStories(): Single<BaseResponse<StoryResponse>> {
        return marvelServiceImpl.getAllStories()
            .applySchedulers()
    }

    override fun getStory(storyId: Int): Single<BaseResponse<StoryResponse>> {
        return marvelServiceImpl.getStory(storyId)
            .applySchedulers()
    }

    override fun getStoryCreatorsByStoryId(storyId: Int): Single<BaseResponse<CreatorsResponse>> {
        return marvelServiceImpl.getStoryCreatorsByStoryId(storyId)
            .applySchedulers()
    }

    override fun getComicsByStoryId(storyId: Int): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getComicsByStoryId(storyId)
            .applySchedulers()
    }

    override fun getSeriesByCharacterId(
        characterId: Int
    ): Single<BaseResponse<BaseResponse<SeriesResponse>>> {
        return marvelServiceImpl.getSeriesByCharacterId(characterId)
            .applySchedulers()
    }

    override fun getSerieCreatorsBySeriesId(seriesId: Int): Single<BaseResponse<CreatorsResponse>> {
        return marvelServiceImpl.getSerieCreatorsBySeriesId(seriesId)
            .applySchedulers()
    }
}
