package com.red_velvet.marvel.data.repository


import com.red_velvet.marvel.data.local.entities.CharacterEntity
import com.red_velvet.marvel.data.local.entities.ComicEntity
import com.red_velvet.marvel.data.local.entities.EventEntity
import com.red_velvet.marvel.data.model.Character
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.Event
import com.red_velvet.marvel.data.model.Series
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Observable

interface MarvelRepository {

    fun getAllComics(
        titleStartsWith: String? = null,
        dateDescriptor: String? = null,
    ): Observable<State<List<Comic>>>

    fun getComicById(comicId: Int): Observable<State<List<Comic>>>

    fun getComicsByCharacterId(characterId: Int): Observable<State<List<Comic>>>

    fun getAllSeries(
        titleStartsWith: String? = null,
        contains: String? = null
    ): Observable<State<List<Series>>>

    fun getCreatorByComicId(comicId: Int): Observable<State<List<Creator>>>

    fun getSeriesById(seriesId: Int): Observable<State<List<Series>>>

    fun getAllEvents(query: String? = null): Observable<State<List<Event>>>

    fun getCharactersByEventId(eventId: Int): Observable<State<List<Character>>>

    fun getCreatorsByEventId(eventId: Int): Observable<State<List<Creator>>>

    fun getAllStories(): Observable<State<List<Story>>>

    fun getStoryById(storyId: Int): Observable<State<List<Story>>>

    fun getCreatorsByStoryId(storyId: Int): Observable<State<List<Creator>>>

    fun getComicsByStoryId(storyId: Int): Observable<State<List<Comic>>>

    fun getCharactersByComicId(comicId: Int): Observable<State<List<Character>>>

    fun getEventById(eventId: Int): Observable<State<List<Event>>>

    fun getAllCharacters(nameStartsWith: String? = null): Observable<State<List<Character>>>

    fun getCharacterById(characterId: Int): Observable<State<List<Character>>>

    fun getCreatorsBySeriesId(seriesId: Int): Observable<State<List<Creator>>>

    fun getSeriesByCharacterId(characterId: Int): Observable<State<List<Series>>>
    fun refreshComics()
    fun refreshCharacters()
    fun refreshEvents()
    fun getAllComics(): Observable<ComicEntity>
    fun getAllEvents(): Observable<EventEntity>
    fun getAllCharacters(): Observable<CharacterEntity>
}

