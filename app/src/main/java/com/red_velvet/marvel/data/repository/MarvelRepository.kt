package com.red_velvet.marvel.data.repository


import com.red_velvet.marvel.data.entity.CharsEntity
import com.red_velvet.marvel.data.entity.ComicsEntity
import com.red_velvet.marvel.data.entity.EventsEntity
import com.red_velvet.marvel.data.entity.EventsSearch
import com.red_velvet.marvel.data.entity.SeriesSearch
import com.red_velvet.marvel.data.model.CharacterDto
import com.red_velvet.marvel.data.model.ComicDto
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.EventDto
import com.red_velvet.marvel.data.model.Series
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.domain.models.Chars
import com.red_velvet.marvel.domain.models.Comic
import com.red_velvet.marvel.domain.models.Event
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Observable

interface MarvelRepository {


    fun getAllComics(
        titleStartsWith: String? = null,
        dateDescriptor: String? = null,
    ): Observable<State<List<ComicDto>>>

    fun getComicById(comicId: Int): Observable<State<List<ComicDto>>>

    fun getComicsByCharacterId(characterId: Int): Observable<State<List<ComicDto>>>

    fun getAllSeries(
        titleStartsWith: String? = null,
        contains: String? = null
    ): Observable<State<List<Series>>>

    fun getCreatorByComicId(comicId: Int): Observable<State<List<Creator>>>

    fun getSeriesById(seriesId: Int): Observable<State<List<Series>>>

    fun getAllEvents(query: String? = null): Observable<State<List<EventDto>>>

    fun getCharactersByEventId(eventId: Int): Observable<State<List<CharacterDto>>>

    fun getCreatorsByEventId(eventId: Int): Observable<State<List<Creator>>>

    fun getAllStories(): Observable<State<List<Story>>>

    fun getStoryById(storyId: Int): Observable<State<List<Story>>>

    fun getCreatorsByStoryId(storyId: Int): Observable<State<List<Creator>>>

    fun getComicsByStoryId(storyId: Int): Observable<State<List<ComicDto>>>

    fun getCharactersByComicId(comicId: Int): Observable<State<List<CharacterDto>>>

    fun getEventById(eventId: Int): Observable<State<List<EventDto>>>

    fun getAllCharacters(nameStartsWith: String? = null): Observable<State<List<CharacterDto>>>

    fun getCharacterById(characterId: Int): Observable<State<List<CharacterDto>>>

    fun getCreatorsBySeriesId(seriesId: Int): Observable<State<List<Creator>>>

    fun getSeriesByCharacterId(characterId: Int): Observable<State<List<Series>>>

    fun refreshComics()
    fun refreshCharacters()
    fun refreshEvents()
    fun refreshEventsSearch()

    fun getEventsSearch(): Observable<List<EventsSearch>>

    fun getAllComics(): Observable<List<Comic>>
    fun getAllEvents(): Observable<List<Event>>
    fun getAllCharacters(): Observable<List<Chars>>
}

