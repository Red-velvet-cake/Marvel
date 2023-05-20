package com.red_velvet.marvel.data.repository


import com.red_velvet.marvel.data.local.database.MarvelDatabase
import com.red_velvet.marvel.data.local.mapper.CharacterEntityMapper
import com.red_velvet.marvel.data.local.mapper.ComicEntityMapper
import com.red_velvet.marvel.data.local.mapper.EventEntityMapper
import com.red_velvet.marvel.data.remote.dto.BaseResponse
import com.red_velvet.marvel.data.remote.dto.CharacterDto
import com.red_velvet.marvel.data.remote.dto.ComicDto
import com.red_velvet.marvel.data.remote.dto.CreatorDto
import com.red_velvet.marvel.data.remote.dto.EventDto
import com.red_velvet.marvel.data.remote.dto.SeriesDto
import com.red_velvet.marvel.data.remote.dto.StoryDto
import com.red_velvet.marvel.data.remote.service.MarvelService
import com.red_velvet.marvel.domain.mapper.LocalCharacterMapper
import com.red_velvet.marvel.domain.mapper.LocalComicMapper
import com.red_velvet.marvel.domain.mapper.LocalEventMapper
import com.red_velvet.marvel.domain.model.Character
import com.red_velvet.marvel.domain.model.Comic
import com.red_velvet.marvel.domain.model.Event
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val marvelServiceImpl: MarvelService,
    private val marvelDatabase: MarvelDatabase,
    private val ComicEntityMapper: ComicEntityMapper,
    private val localComicMapper: LocalComicMapper,
    private val eventEntityMapper: EventEntityMapper,
    private val localEventMapper: LocalEventMapper,
    private val characterEntityMapper: CharacterEntityMapper,
    private val localCharacterMapper: LocalCharacterMapper,
) : MarvelRepository {

    override fun getAllComics(
        titleStartsWith: String?,
        dateDescriptor: String?
    ): Observable<State<List<ComicDto>>> {
        return wrapWithState { marvelServiceImpl.getAllComics(titleStartsWith, dateDescriptor) }
    }

    override fun getComicById(comicId: Int): Observable<State<List<ComicDto>>> {
        return wrapWithState { marvelServiceImpl.getComicDetailById(comicId) }
    }

    override fun getComicsByCharacterId(characterId: Int): Observable<State<List<ComicDto>>> {
        return wrapWithState { marvelServiceImpl.getComicsByCharacterId(characterId) }
    }

    override fun getAllSeries(
        titleStartsWith: String?,
        contains: String?
    ): Observable<State<List<SeriesDto>>> {
        return wrapWithState { marvelServiceImpl.getAllSeries(titleStartsWith, contains) }
    }

    override fun getCharactersByComicId(comicId: Int): Observable<State<List<CharacterDto>>> {
        return wrapWithState { marvelServiceImpl.getCharactersByComicId(comicId) }
    }

    override fun getSeriesById(seriesId: Int): Observable<State<List<SeriesDto>>> {
        return wrapWithState { marvelServiceImpl.getSeriesById(seriesId) }
    }

    override fun getAllEvents(query: String?): Observable<State<List<EventDto>>> {
        return wrapWithState { marvelServiceImpl.getAllEvents(query) }
    }

    override fun getCreatorByComicId(comicId: Int): Observable<State<List<CreatorDto>>> {
        return wrapWithState { marvelServiceImpl.getCreatorByComicId(comicId) }
    }

    override fun getCharactersByEventId(eventId: Int): Observable<State<List<CharacterDto>>> {
        return wrapWithState { marvelServiceImpl.getCharactersByEventId(eventId) }
    }

    override fun getAllCharacters(nameStartsWith: String?): Observable<State<List<CharacterDto>>> {
        return wrapWithState { marvelServiceImpl.getAllCharacters(nameStartsWith) }
    }

    override fun getCharacterById(characterId: Int): Observable<State<List<CharacterDto>>> {
        return wrapWithState { marvelServiceImpl.getCharacterById(characterId) }
    }

    override fun getCreatorsByEventId(eventId: Int): Observable<State<List<CreatorDto>>> {
        return wrapWithState { marvelServiceImpl.getCreatorsByEventId(eventId) }
    }

    override fun getAllStories(): Observable<State<List<StoryDto>>> {
        return wrapWithState { marvelServiceImpl.getAllStories() }
    }

    override fun getStoryById(storyId: Int): Observable<State<List<StoryDto>>> {
        return wrapWithState { marvelServiceImpl.getStoryById(storyId) }
    }

    override fun getCreatorsByStoryId(storyId: Int): Observable<State<List<CreatorDto>>> {
        return wrapWithState { marvelServiceImpl.getCreatorsByStoryId(storyId) }
    }

    override fun getComicsByStoryId(storyId: Int): Observable<State<List<ComicDto>>> {
        return wrapWithState { marvelServiceImpl.getComicsByStoryId(storyId) }
    }

    override fun getSeriesByCharacterId(
        characterId: Int
    ): Observable<State<List<SeriesDto>>> {
        return wrapWithState { marvelServiceImpl.getSeriesByCharacterId(characterId) }
    }

    override fun getEventById(
        eventId: Int
    ): Observable<State<List<EventDto>>> {
        return wrapWithState { marvelServiceImpl.getEventById(eventId) }
    }

    override fun getCreatorsBySeriesId(seriesId: Int): Observable<State<List<CreatorDto>>> {
        return wrapWithState { marvelServiceImpl.getCreatorsBySeriesId(seriesId) }
    }

    private fun <T> wrapWithState(function: () -> Single<Response<BaseResponse<T>>>): Observable<State<T>> {
        return function()
            .map<State<T>> { response ->
                if (response.isSuccessful) {
                    State.Success(response.body()?.body?.results!!)
                } else {
                    State.Failed(response.message())
                }
            }
            .onErrorReturn { State.Failed(it.message ?: "Unknown error") }
            .startWith(Observable.just(State.Loading))
    }

    override fun getLocalComics(
        titleStartsWith: String?,
        contains: String?
    ): Observable<List<Comic>> {
        return marvelDatabase.comicDao().getAll()
            .map { comics -> comics.map { localComicMapper.map(it) } }
    }

    override fun getLocalEvents(query: String?): Observable<List<Event>> {
        return marvelDatabase.eventDao().getAll()
            .map { events -> events.map { localEventMapper.map(it) } }
    }

    override fun getLocalCharacters(nameStartsWith: String?): Observable<List<Character>> {
        return marvelDatabase.characterDao().getAll()
            .map { characters -> characters.map { localCharacterMapper.map(it) } }
    }

    override fun refreshComics(): Completable {
        return marvelServiceImpl.getAllComics()
            .flatMapCompletable { responseWrapper ->
                val comics = responseWrapper.body()?.body?.results
                val comicsEntities = comics?.map { ComicEntityMapper.map(it) }
                comicsEntities?.let {
                    Completable.fromAction { marvelDatabase.comicDao().insertAll(it) }
                        .subscribeOn(Schedulers.io())
                } ?: Completable.complete()
            }
            .onErrorResumeNext {
                Completable.complete()
            }
    }

    override fun refreshEvents(): Completable {
        return marvelServiceImpl.getAllEvents()
            .flatMapCompletable { responseWrapper ->
                val events = responseWrapper.body()?.body?.results
                val eventsEntities = events?.map { eventEntityMapper.map(it) }
                eventsEntities?.let {
                    Completable.fromAction { marvelDatabase.eventDao().insertAll(it) }
                        .subscribeOn(Schedulers.io())
                } ?: Completable.complete()
            }
            .onErrorResumeNext {
                Completable.complete()
            }
    }

    override fun refreshCharacters(): Completable {
        return marvelServiceImpl.getAllCharacters()
            .flatMapCompletable { responseWrapper ->
                val characters = responseWrapper.body()?.body?.results
                val charactersEntities = characters?.map { characterEntityMapper.map(it) }
                charactersEntities?.let {
                    Completable.fromAction { marvelDatabase.characterDao().insertAll(it) }
                        .subscribeOn(Schedulers.io())
                } ?: Completable.complete()
            }
            .onErrorResumeNext {
                Completable.complete()
            }
    }
}