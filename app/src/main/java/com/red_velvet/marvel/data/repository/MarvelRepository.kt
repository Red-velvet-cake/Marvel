package com.red_velvet.marvel.data.repository


import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.model.StoryResponse
import com.red_velvet.marvel.data.util.State
import io.reactivex.rxjava3.core.Observable

interface MarvelRepository {

    fun getComics(): Observable<State<List<ComicsResponse>?>>

    fun getComicDetail(comicId: Int): Observable<State<List<ComicsResponse>?>>

    fun getComicsByCharacterId(characterId: Int): Observable<State<List<ComicsResponse>?>>

    fun getAllSeries(
        startYear: Int? = null,
        contains: String? = null
    ): Observable<State<List<SeriesResponse>?>>

    fun getComicCreatorByComicId(comicId: Int): Observable<State<List<CreatorsResponse>?>>

    fun getSeriesDetails(seriesId: Int): Observable<State<List<SeriesResponse>?>>

    fun getEvents(query: String? = null): Observable<State<List<EventsResponse>?>>

    fun getCharactersByEventId(eventId: Int): Observable<State<List<CharactersResponse>?>>

    fun getCreatorsByEventId(eventId: Int): Observable<State<List<CreatorsResponse>?>>

    fun getStories(): Observable<State<List<StoryResponse>?>>

    fun getStory(storyId: Int): Observable<State<List<StoryResponse>?>>

    fun getStoryCreatorsByStoryId(storyId: Int): Observable<State<List<CreatorsResponse>?>>

    fun getComicsByStoryId(storyId: Int): Observable<State<List<ComicsResponse>?>>

    fun getCharsByComicId(comicId: Int): Observable<State<List<CharactersResponse>?>>

    fun getCharacters(): Observable<State<List<CharactersResponse>?>>

    fun getCharacterByCharacterId(characterId: Int): Observable<State<List<CharactersResponse>?>>

    fun getSerieCreatorsBySeriesId(seriesId: Int): Observable<State<List<CreatorsResponse>?>>

    fun getSeriesByCharacterId(characterId: Int): Observable<State<List<SeriesResponse>?>>

}