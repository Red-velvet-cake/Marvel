package com.red_velvet.marvel.domain.repository


import android.util.Log
import com.red_velvet.marvel.data.local.MarvelDao
import com.red_velvet.marvel.data.local.entity.SearchQueryEntity
import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.data.remote.MarvelService
import com.red_velvet.marvel.data.remote.dtos.CharacterDto
import com.red_velvet.marvel.data.remote.dtos.ComicDto
import com.red_velvet.marvel.data.remote.dtos.EventDto
import com.red_velvet.marvel.data.remote.dtos.SeriesDto
import com.red_velvet.marvel.domain.mappers.CharacterEntityMapper
import com.red_velvet.marvel.domain.mappers.CharacterMapper
import com.red_velvet.marvel.domain.mappers.ComicEntityMapper
import com.red_velvet.marvel.domain.mappers.ComicMapper
import com.red_velvet.marvel.domain.mappers.EventEntityMapper
import com.red_velvet.marvel.domain.mappers.EventMapper
import com.red_velvet.marvel.domain.mappers.SearchQueryMapper
import com.red_velvet.marvel.domain.mappers.SeriesEntityMapper
import com.red_velvet.marvel.domain.mappers.SeriesMapper
import com.red_velvet.marvel.domain.models.Character
import com.red_velvet.marvel.domain.models.Comic
import com.red_velvet.marvel.domain.models.Event
import com.red_velvet.marvel.domain.models.SearchQuery
import com.red_velvet.marvel.domain.models.Series
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import java.util.Date
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val marvelServiceImpl: MarvelService,
    private val marvelDao: MarvelDao,
    private val comicEntityMapper: ComicEntityMapper,
    private val comicMapper: ComicMapper,
    private val characterEntityMapper: CharacterEntityMapper,
    private val characterMapper: CharacterMapper,
    private val eventEntityMapper: EventEntityMapper,
    private val eventMapper: EventMapper,
    private val seriesEntityMapper: SeriesEntityMapper,
    private val seriesMapper: SeriesMapper,
    private val searchQueryMapper: SearchQueryMapper
) : MarvelRepository {

    override fun getAllComics(): Observable<List<Comic>> {
        return marvelDao.getAllComics().map {
            it.map { comicEntity ->
                comicMapper.map(comicEntity)
            }
        }
    }

    override fun refreshComics() =
        marvelServiceImpl.getAllComics()
            .observeOn(Schedulers.io())
            .map { responseWrapper ->
                if (responseWrapper.isSuccessful) {
                    responseWrapper.body()?.body?.results?.map {
                        comicEntityMapper.map(it)
                    }?.let {
                        marvelDao.insertComics(it)
                    }
                }
            }.subscribeOn(AndroidSchedulers.mainThread())

    override fun getComicById(comicId: Int): Observable<State<List<ComicDto>>> {
        return wrapWithState { marvelServiceImpl.getComicDetailById(comicId) }
    }

    override fun getComicsByCharacterId(characterId: Int): Observable<State<List<ComicDto>>> {
        return wrapWithState { marvelServiceImpl.getComicsByCharacterId(characterId) }
    }

    override fun getAllSeries(
        titleStartsWith: String, contains: String
    ): Observable<List<Series>> {
        return marvelDao.getAllSeries("%$titleStartsWith%")
            .observeOn(Schedulers.io())
            .map { seriesEntities ->
                if (seriesEntities.isEmpty()) {
                    val responseWrapper = marvelServiceImpl.getSeriesByTitle(titleStartsWith)
                        .blockingGet()
                    if (responseWrapper.isSuccessful) {
                        val seriesEntitiesList =
                            responseWrapper.body()?.body?.results?.map { eventDto ->
                                seriesEntityMapper.map(eventDto)
                            }
                        seriesEntitiesList?.let {
                            marvelDao.insertSeries(it)
                        }
                    } else {
                        Log.d("TAG", "getAllSeries: ${responseWrapper.message()}")
                    }
                }
                seriesEntities.map { eventEntity ->
                    seriesMapper.map(eventEntity)
                }
            }
    }

    override fun getCharactersByComicId(comicId: Int): Observable<State<List<CharacterDto>>> {
        return wrapWithState { marvelServiceImpl.getCharactersByComicId(comicId) }
    }

    override fun getSeriesById(seriesId: Int): Observable<State<List<SeriesDto>>> {
        return wrapWithState { marvelServiceImpl.getSeriesById(seriesId) }
    }

    override fun getAllEvents(query: String): Observable<List<Event>> {
        return marvelDao.getAllEvents("%$query%")
            .observeOn(Schedulers.io())
            .map { eventEntities ->
                if (eventEntities.isEmpty()) {
                    val responseWrapper = marvelServiceImpl.getEventsByTitle(query)
                        .blockingGet()
                    if (responseWrapper.isSuccessful) {
                        val eventsEntitiesList =
                            responseWrapper.body()?.body?.results?.map { eventDto ->
                                eventEntityMapper.map(eventDto)
                            }
                        eventsEntitiesList?.let {
                            marvelDao.insertEvents(it)
                        }
                    } else {
                        Log.d("TAG", "getAllCharacters: ${responseWrapper.message()}")
                    }
                }
                eventEntities.map { eventEntity ->
                    eventMapper.map(eventEntity)
                }
            }
    }

    override fun refreshEvents() =
        marvelServiceImpl.getAllEvents()
            .observeOn(Schedulers.io())
            .map { responseWrapper ->
                if (responseWrapper.isSuccessful) {
                    responseWrapper.body()?.body?.results?.map {
                        eventEntityMapper.map(it)
                    }?.let {
                        marvelDao.insertEvents(it)
                    }
                }
            }.subscribeOn(AndroidSchedulers.mainThread())

    override fun getCreatorByComicId(comicId: Int): Observable<State<List<Creator>>> {
        return wrapWithState { marvelServiceImpl.getCreatorByComicId(comicId) }
    }

    override fun getCharactersByEventId(eventId: Int): Observable<State<List<CharacterDto>>> {
        return wrapWithState { marvelServiceImpl.getCharactersByEventId(eventId) }
    }

    override fun getAllCharacters(titleStartsWith: String): Observable<List<Character>> {
        return marvelDao.getAllCharacters("%$titleStartsWith%")
            .observeOn(Schedulers.io())
            .map { characterEntities ->
                if (characterEntities.isEmpty()) {
                    val responseWrapper = marvelServiceImpl.getCharactersByTitle(titleStartsWith)
                        .blockingGet()
                    if (responseWrapper.isSuccessful) {
                        val characterEntitiesList =
                            responseWrapper.body()?.body?.results?.map { characterDto ->
                                characterEntityMapper.map(characterDto)
                            }
                        characterEntitiesList?.let {
                            marvelDao.insertCharacters(it)
                        }
                    } else {
                        Log.d("TAG", "getAllCharacters: ${responseWrapper.message()}")
                    }
                }
                characterEntities.map { characterEntity ->
                    characterMapper.map(characterEntity)
                }
            }
    }

    override fun refreshCharacters() =
        marvelServiceImpl.getAllCharacters()
            .observeOn(Schedulers.io())
            .map { responseWrapper ->
                if (responseWrapper.isSuccessful) {
                    responseWrapper.body()?.body?.results?.map {
                        characterEntityMapper.map(it)
                    }?.let {
                        marvelDao.insertCharacters(it)
                    }
                }
            }.subscribeOn(AndroidSchedulers.mainThread())


    override fun getCharacterById(characterId: Int): Observable<State<List<CharacterDto>>> {
        return wrapWithState { marvelServiceImpl.getCharacterById(characterId) }
    }

    override fun getCreatorsByEventId(eventId: Int): Observable<State<List<Creator>>> {
        return wrapWithState { marvelServiceImpl.getCreatorsByEventId(eventId) }
    }

    override fun getAllStories(): Observable<State<List<Story>>> {
        return wrapWithState { marvelServiceImpl.getAllStories() }
    }

    override fun getStoryById(storyId: Int): Observable<State<List<Story>>> {
        return wrapWithState { marvelServiceImpl.getStoryById(storyId) }
    }

    override fun getCreatorsByStoryId(storyId: Int): Observable<State<List<Creator>>> {
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

    override fun getCreatorsBySeriesId(seriesId: Int): Observable<State<List<Creator>>> {
        return wrapWithState { marvelServiceImpl.getCreatorsBySeriesId(seriesId) }
    }

    private fun <T> wrapWithState(function: () -> Single<Response<BaseResponse<T>>>): Observable<State<T>> {
        return function().map<State<T>> { response ->
            if (response.isSuccessful) {
                State.Success(response.body()?.body?.results!!)
            } else {
                State.Failed(response.message())
            }
        }.onErrorReturn { State.Failed(it.message ?: "Unknown error") }
            .startWith(Observable.just(State.Loading))
    }

    override fun getSearchQueries(): Observable<List<SearchQuery>> {
        return marvelDao.getAllSearchQueries()
            .map { it.map { searchQueryEntity -> searchQueryMapper.map(searchQueryEntity) } }
    }

    override fun insertSearchQuery(query: String): Completable {
        return Completable.fromAction {
            marvelDao.insertSearchQuery(SearchQueryEntity(query = query, timestamp = Date().time))
        }.onErrorResumeNext { Completable.complete() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteSearchQuery(id: Int): Completable {
        return Completable.fromAction {
            marvelDao.deleteSearchQuery(id)
        }.onErrorResumeNext { Completable.complete() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}