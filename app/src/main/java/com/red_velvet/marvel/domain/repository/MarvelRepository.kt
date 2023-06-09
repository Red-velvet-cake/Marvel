package com.red_velvet.marvel.domain.repository


import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.data.remote.dtos.CharacterDto
import com.red_velvet.marvel.data.remote.dtos.ComicDto
import com.red_velvet.marvel.data.remote.dtos.EventDto
import com.red_velvet.marvel.data.remote.dtos.SeriesDto
import com.red_velvet.marvel.domain.models.Character
import com.red_velvet.marvel.domain.models.Comic
import com.red_velvet.marvel.domain.models.Event
import com.red_velvet.marvel.domain.models.SearchQuery
import com.red_velvet.marvel.domain.models.Series
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {

    fun getAllComics(): Observable<List<Comic>>

    fun refreshComics(): Single<Unit>

    fun getComicById(comicId: Int): Observable<State<List<ComicDto>>>

    fun getComicsByCharacterId(characterId: Int): Observable<State<List<ComicDto>>>

    fun getAllSeries(
        titleStartsWith: String = "", contains: String = ""
    ): Observable<List<Series>>

    fun getCreatorByComicId(comicId: Int): Observable<State<List<Creator>>>

    fun getSeriesById(seriesId: Int): Observable<State<List<SeriesDto>>>

    fun getAllEvents(query: String = ""): Observable<List<Event>>

    fun refreshEvents(): Single<Unit>

    fun getCharactersByEventId(eventId: Int): Observable<State<List<CharacterDto>>>

    fun getCreatorsByEventId(eventId: Int): Observable<State<List<Creator>>>

    fun getAllStories(): Observable<State<List<Story>>>

    fun getStoryById(storyId: Int): Observable<State<List<Story>>>

    fun getCreatorsByStoryId(storyId: Int): Observable<State<List<Creator>>>

    fun getComicsByStoryId(storyId: Int): Observable<State<List<ComicDto>>>

    fun getCharactersByComicId(comicId: Int): Observable<State<List<CharacterDto>>>

    fun getEventById(eventId: Int): Observable<State<List<EventDto>>>

    fun getAllCharacters(titleStartsWith: String = ""): Observable<List<Character>>

    fun refreshCharacters(): Single<Unit>

    fun getCharacterById(characterId: Int): Observable<State<List<CharacterDto>>>

    fun getCreatorsBySeriesId(seriesId: Int): Observable<State<List<Creator>>>

    fun getSeriesByCharacterId(characterId: Int): Observable<State<List<SeriesDto>>>

    fun getSearchQueries(): Observable<List<SearchQuery>>

    fun insertSearchQuery(query: String): Completable

    fun deleteSearchQuery(id: Int): Completable
}

