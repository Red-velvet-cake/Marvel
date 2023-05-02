package com.red_velvet.marvel.ui


import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.Characters
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.model.StoryResponse
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {

    fun getComics(): Single<BaseResponse<ComicsResponse>>

    fun getComicDetail(comicId: Int): Single<BaseResponse<ComicsResponse>>

    fun getComicsByCharacterId(characterId: Int): Single<BaseResponse<ComicsResponse>>

    fun getComicCreatorByComicId(comicId: Int): Single<BaseResponse<CreatorsResponse>>

    fun getAllSeries(): Single<BaseResponse<SeriesResponse>>

    fun getSeriesDetails(seriesId: Int): Single<BaseResponse<SeriesResponse>>

    fun getEvents(): Single<BaseResponse<EventsResponse>>

    fun getCharactersByEventId(eventId: Int): Single<BaseResponse<CharactersResponse>>

    fun getCreatorsByEventId(eventId: Int): Single<BaseResponse<CreatorsResponse>>

    fun getStories(): Single<BaseResponse<StoryResponse>>

    fun getStory(storyId: Int): Single<BaseResponse<StoryResponse>>

    fun getStoryCreatorsByStoryId(storyId: Int): Single<BaseResponse<CreatorsResponse>>

    fun getComicsByStoryId(storyId:Int): Single<BaseResponse<ComicsResponse>>
    fun getCharsByComicId(
        comicId:Int
    ):Single<BaseResponse<Characters>>
    fun getCharacters():Single<BaseResponse<Characters>>

    fun getSerieCreatorsBySeriesId(seriesId: Int): Single<BaseResponse<CreatorsResponse>>

    fun getSeriesByCharacterId(
        characterId: Int
    ): Single<BaseResponse<BaseResponse<SeriesResponse>>>

}