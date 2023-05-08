package com.red_velvet.marvel.data.repository


import com.red_velvet.marvel.data.model.Character
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.Event
import com.red_velvet.marvel.data.model.Series
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Observable

interface MarvelRepository {

    fun getComics(
        titleStartsWith: String? = null,
        dateDescriptor: String? = null,
    ): Observable<State<List<Comic>>>

    fun getComicDetail(comicId: Int): Observable<State<List<Comic>>>

    fun getComicsByCharacterId(characterId: Int): Observable<State<List<Comic>>>

    fun getAllSeries(
        startYear: Int? = null,
        contains: String? = null
    ): Observable<State<List<Series>>>

    fun getComicCreatorByComicId(comicId: Int): Observable<State<List<Creator>>>

    fun getSeriesDetails(seriesId: Int): Observable<State<List<Series>>>

    fun getEvents(query: String? = null): Observable<State<List<Event>>>

    fun getCharactersByEventId(eventId: Int): Observable<State<List<Character>>>

    fun getCreatorsByEventId(eventId: Int): Observable<State<List<Creator>>>

    fun getStories(): Observable<State<List<Story>>>

    fun getStory(storyId: Int): Observable<State<List<Story>>>

    fun getStoryCreatorsByStoryId(storyId: Int): Observable<State<List<Creator>>>

    fun getComicsByStoryId(storyId: Int): Observable<State<List<Comic>>>

    fun getCharsByComicId(comicId: Int): Observable<State<List<Character>>>

    fun getEventDetails(eventId: Int): Observable<State<List<Event>>>

    fun getCharacters(): Observable<State<List<Character>>>

    fun getCharacterByCharacterId(characterId: Int): Observable<State<List<Character>>>

    fun getSeriesCreatorsBySeriesId(seriesId: Int): Observable<State<List<Creator>>>

    fun getSeriesByCharacterId(characterId: Int): Observable<State<List<Series>>>

    fun searchCharacters(nameStartsWith: String?): Observable<State<List<Character>>>
}

