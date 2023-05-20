package com.red_velvet.marvel.domain.repository


import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.Series
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.data.remote.dtos.CharacterDto
import com.red_velvet.marvel.data.remote.dtos.ComicDto
import com.red_velvet.marvel.data.remote.dtos.EventDto
import com.red_velvet.marvel.domain.models.Charcter
import com.red_velvet.marvel.domain.models.Comic
import com.red_velvet.marvel.domain.models.Event
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {

    fun getAllComics(): Observable<List<Comic>>

    fun refreshComics(): Single<Unit>

    fun getComicById(comicId: Int): Observable<State<List<ComicDto>>>

    fun getComicsByCharacterId(characterId: Int): Observable<State<List<ComicDto>>>

    fun getAllSeries(
        titleStartsWith: String? = null,
        contains: String? = null
    ): Observable<State<List<Series>>>

    fun getCreatorByComicId(comicId: Int): Observable<State<List<Creator>>>

    fun getSeriesById(seriesId: Int): Observable<State<List<Series>>>

    fun getAllEvents(query: String? = null): Observable<List<Event>>

    fun refreshEvents(): Single<Unit>

    fun getCharactersByEventId(eventId: Int): Observable<State<List<CharacterDto>>>

    fun getCreatorsByEventId(eventId: Int): Observable<State<List<Creator>>>

    fun getAllStories(): Observable<State<List<Story>>>

    fun getStoryById(storyId: Int): Observable<State<List<Story>>>

    fun getCreatorsByStoryId(storyId: Int): Observable<State<List<Creator>>>

    fun getComicsByStoryId(storyId: Int): Observable<State<List<ComicDto>>>

    fun getCharactersByComicId(comicId: Int): Observable<State<List<CharacterDto>>>

    fun getEventById(eventId: Int): Observable<State<List<EventDto>>>

    fun getAllCharacters(nameStartsWith: String? = null): Observable<List<Charcter>>

    fun refreshCharacters(): Single<Unit>

    fun getCharacterById(characterId: Int): Observable<State<List<CharacterDto>>>

    fun getCreatorsBySeriesId(seriesId: Int): Observable<State<List<Creator>>>

    fun getSeriesByCharacterId(characterId: Int): Observable<State<List<Series>>>
}

