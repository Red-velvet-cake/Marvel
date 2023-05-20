package com.red_velvet.marvel.domain.repository


import com.red_velvet.marvel.data.local.MarvelDatabase
import com.red_velvet.marvel.data.local.daos.MarvelDao
import com.red_velvet.marvel.data.remote.dtos.BaseResponse
import com.red_velvet.marvel.data.remote.dtos.CharacterDto
import com.red_velvet.marvel.data.remote.dtos.ComicDto
import com.red_velvet.marvel.data.remote.dtos.Creator
import com.red_velvet.marvel.data.remote.dtos.EventDto
import com.red_velvet.marvel.data.remote.dtos.Series
import com.red_velvet.marvel.data.remote.dtos.Story
import com.red_velvet.marvel.data.remote.MarvelService
import com.red_velvet.marvel.domain.mapper.CharacterEntityMapper
import com.red_velvet.marvel.domain.mapper.CharacterMapper
import com.red_velvet.marvel.domain.mapper.ComicEntityMapper
import com.red_velvet.marvel.domain.mapper.ComicMapper
import com.red_velvet.marvel.domain.mapper.EventEntityMapper
import com.red_velvet.marvel.domain.mapper.EventMapper
import com.red_velvet.marvel.domain.model.Character
import com.red_velvet.marvel.domain.model.Comic
import com.red_velvet.marvel.domain.model.Event
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class MarvelRepositoryImpl(
    private val marvelServiceImpl: MarvelService,
    private val marvelDao: MarvelDao,
    private val comicMapper: ComicMapper,
    private val comicEntityMapper: ComicEntityMapper,
    private val characterMapper: CharacterMapper,
    private val characterEntityMapper: CharacterEntityMapper,
    private val eventMapper: EventMapper,
    private val eventEntityMapper: EventEntityMapper
) : MarvelRepository {
    override fun getAllComics(
        titleStartsWith: String?,
        dateDescriptor: String?
    ): Observable<List<Comic>> {
        return marvelDao.getAllComics().map {
            it.map { comicEntity ->
                comicMapper.map(comicEntity )
            }
        }
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
    ): Observable<State<List<Series>>> {
        return wrapWithState { marvelServiceImpl.getAllSeries(titleStartsWith, contains) }
    }

    override fun getCharactersByComicId(comicId: Int): Observable<State<List<CharacterDto>>> {
        return wrapWithState { marvelServiceImpl.getCharactersByComicId(comicId) }
    }

    override fun getSeriesById(seriesId: Int): Observable<State<List<Series>>> {
        return wrapWithState { marvelServiceImpl.getSeriesById(seriesId) }
    }

    override fun getAllEvents(query: String?): Observable<List<Event>> {
        return marvelDao
            .getAllEvents()
            .map {
                it.map {
                    eventMapper.map(it)
            }
        }
    }

    override fun getCreatorByComicId(comicId: Int): Observable<State<List<Creator>>> {
        return wrapWithState { marvelServiceImpl.getCreatorByComicId(comicId) }
    }

    override fun getCharactersByEventId(eventId: Int): Observable<State<List<CharacterDto>>> {
        return wrapWithState { marvelServiceImpl.getCharactersByEventId(eventId) }
    }

    override fun getAllCharacters(nameStartsWith: String?): Observable<List<Character>> {
        return marvelDao.getAllCharacters().map {
            it.map { characterEntity ->
                characterMapper.map(characterEntity)
            }
        }
    }

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
    ): Observable<State<List<Series>>> {
        return wrapWithState { marvelServiceImpl.getSeriesByCharacterId(characterId) }
    }

    override fun refreshComics() =
        marvelServiceImpl.getAllComics()
            .observeOn(Schedulers.io())
            .map { responseWrapper ->
                if (responseWrapper.isSuccessful) {
                    val comicEntities = responseWrapper.body()?.body?.results?.map {
                        comicEntityMapper.map(it)
                    }
                    marvelDao.insertComics(comicEntities!!)
                }
            }.subscribeOn(AndroidSchedulers.mainThread())

    override fun refreshEvents() =
        marvelServiceImpl.getAllEvents()
            .observeOn(Schedulers.io())
            .map { responseWrapper ->
                if (responseWrapper.isSuccessful) {
                    val eventEntities = responseWrapper.body()?.body?.results?.map {
                        eventEntityMapper.map(it)
                    }
                    marvelDao.insertEvents(eventEntities!!)
                }
            }.subscribeOn(AndroidSchedulers.mainThread())

    override fun refreshCharacters() =
        marvelServiceImpl.getAllCharacters()
            .observeOn(Schedulers.io())
            .map { responseWrapper ->
                if (responseWrapper.isSuccessful) {
                    val characterEntities = responseWrapper.body()?.body?.results?.map {
                        characterEntityMapper.map(it)
                    }
                    marvelDao.insertCharacters(characterEntities!!)
                }
            }.subscribeOn(AndroidSchedulers.mainThread())

    override fun getEventById(
        eventId: Int
    ): Observable<State<List<EventDto>>> {
        return wrapWithState { marvelServiceImpl.getEventById(eventId) }
    }

    override fun getCreatorsBySeriesId(seriesId: Int): Observable<State<List<Creator>>> {
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
}