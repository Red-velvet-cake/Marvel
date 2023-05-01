package com.red_velvet.marvel.data.repository


import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.Characters
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.model.StoryResponse
import com.red_velvet.marvel.data.remote.MarvelService
import io.reactivex.rxjava3.core.Single

class MarvelRepositoryImpl(
    private val marvelServiceImpl: MarvelService
) : MarvelRepository {

    override fun getComics(): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getAllComics()
    }
    override fun getComicDetail(comicId: Int): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getComicDetail(comicId)
    }

    override fun getComicsByCharacterId(characterId: Int): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getComicsByCharacterId(characterId)
    }

    override fun getComicCreatorByComicId(comicId: Int): Single<BaseResponse<CreatorsResponse>> {
        return marvelServiceImpl.getComicCreatorByComicId(comicId)
    }

    override fun getAllSeries(): Single<BaseResponse<SeriesResponse>> {
        return marvelServiceImpl.getAllSeries()
    }

    override fun getSeriesDetails(seriesId: Int): Single<BaseResponse<SeriesResponse>> {
        return marvelServiceImpl.getSerieDetails(seriesId)
    }

    override fun getEvents(): Single<BaseResponse<EventsResponse>> {
        return marvelServiceImpl.getAllEvents()
    }

    override fun getCharactersByEventId(eventId: Int): Single<BaseResponse<CharactersResponse>> {
        return marvelServiceImpl.getCharactersByEventId(eventId)
    }

    override fun getCharacters(): Single<BaseResponse<Characters>> {
        return marvelServiceImpl.getCharacters()
    }

    override fun getCreatorsByEventId(eventId: Int): Single<BaseResponse<CreatorsResponse>> {
        return marvelServiceImpl.getCreatorsByEventId(eventId)
    }

    override fun getStories(): Single<BaseResponse<StoryResponse>> {
        return marvelServiceImpl.getAllStories()
    }

    override fun getStory(storyId: Int): Single<BaseResponse<StoryResponse>> {
        return marvelServiceImpl.getStory(storyId)
    }

    override fun getStoryCreatorsByStoryId(storyId: Int): Single<BaseResponse<CreatorsResponse>> {
        return marvelServiceImpl.getStoryCreatorsByStoryId(storyId)
    }

    override fun getComicsByStoryId(storyId: Int): Single<BaseResponse<ComicsResponse>> {
        return marvelServiceImpl.getComicsByStoryId(storyId)
    }

    override fun getSeriesByCharacterId(
        characterId: Int
    ): Single<BaseResponse<BaseResponse<SeriesResponse>>> {
        return marvelServiceImpl.getSeriesByCharacterId(characterId)
    }

    override fun getSerieCreatorsBySeriesId(seriesId: Int): Single<BaseResponse<CreatorsResponse>> {
        return marvelServiceImpl.getSerieCreatorsBySeriesId(seriesId)
    }

}