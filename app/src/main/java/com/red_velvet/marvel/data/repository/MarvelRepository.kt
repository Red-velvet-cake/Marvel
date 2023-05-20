package com.red_velvet.marvel.data.repository


import com.red_velvet.marvel.data.remote.dto.CharacterDto
import com.red_velvet.marvel.data.remote.dto.ComicDto
import com.red_velvet.marvel.data.remote.dto.CreatorDto
import com.red_velvet.marvel.data.remote.dto.EventDto
import com.red_velvet.marvel.data.remote.dto.SeriesDto
import com.red_velvet.marvel.data.remote.dto.StoryDto
import com.red_velvet.marvel.domain.model.Character
import com.red_velvet.marvel.domain.model.Comic
import com.red_velvet.marvel.domain.model.Event
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Completable
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
    ): Observable<State<List<SeriesDto>>>

    fun getCreatorByComicId(comicId: Int): Observable<State<List<CreatorDto>>>

    fun getSeriesById(seriesId: Int): Observable<State<List<SeriesDto>>>

    fun getAllEvents(query: String? = null): Observable<State<List<EventDto>>>

    fun getCharactersByEventId(eventId: Int): Observable<State<List<CharacterDto>>>

    fun getCreatorsByEventId(eventId: Int): Observable<State<List<CreatorDto>>>

    fun getAllStories(): Observable<State<List<StoryDto>>>

    fun getStoryById(storyId: Int): Observable<State<List<StoryDto>>>

    fun getCreatorsByStoryId(storyId: Int): Observable<State<List<CreatorDto>>>

    fun getComicsByStoryId(storyId: Int): Observable<State<List<ComicDto>>>

    fun getCharactersByComicId(comicId: Int): Observable<State<List<CharacterDto>>>

    fun getEventById(eventId: Int): Observable<State<List<EventDto>>>

    fun getAllCharacters(nameStartsWith: String? = null): Observable<State<List<CharacterDto>>>

    fun getCharacterById(characterId: Int): Observable<State<List<CharacterDto>>>

    fun getCreatorsBySeriesId(seriesId: Int): Observable<State<List<CreatorDto>>>

    fun getSeriesByCharacterId(characterId: Int): Observable<State<List<SeriesDto>>>

    fun refreshComics(): Completable

    fun getLocalComics(
        titleStartsWith: String? = null,
        contains: String? = null
    ): Observable<List<Comic>>

    fun refreshEvents(): Completable

    fun getLocalEvents(query: String? = null): Observable<List<Event>>

    fun refreshCharacters(): Completable

    fun getLocalCharacters(nameStartsWith: String? = null): Observable<List<Character>>

}

