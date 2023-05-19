package com.red_velvet.marvel.data.repository


import com.red_velvet.marvel.data.local.MarvelDatabase
import com.red_velvet.marvel.data.local.entities.CharacterEntity
import com.red_velvet.marvel.data.local.entities.ComicEntity
import com.red_velvet.marvel.data.local.entities.EventEntity
import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.Character
import com.red_velvet.marvel.data.model.Comic
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.Event
import com.red_velvet.marvel.data.model.Series
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.data.remote.MarvelService
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MarvelRepositoryImpl(
    private val marvelServiceImpl: MarvelService
) : MarvelRepository {
    val localDatabase = MarvelDatabase.getInstanceWithoutContext().MarvelDao()
    override fun getAllComics(
        titleStartsWith: String?,
        dateDescriptor: String?,
    ): Observable<State<List<Comic>>> {
        return wrapWithState { marvelServiceImpl.getAllComics(titleStartsWith, dateDescriptor) }
    }

    override fun getAllComics(): Observable<ComicEntity> {
        return localDatabase.getAllComics()
    }

    override fun getAllEvents(): Observable<EventEntity> {
        return localDatabase.getAllEvents()
    }

    override fun getAllCharacters(): Observable<CharacterEntity> {
        return localDatabase.getAllCharacters()
    }


    override fun refreshComics() {
        marvelServiceImpl.getAllComics().map { response ->
            if (response.isSuccessful)
                response.body()?.body?.results?.map { comic ->
                    localDatabase.insertComic(
                        ComicEntity(
                            1, comic.title.toString(),
                            comic.description.toString(),
                            "${comic.thumbnail?.path}.${comic.thumbnail?.extension}"
                        )
                    )

                }
        }

    }

    override fun refreshCharacters() {
        marvelServiceImpl.getAllCharacters().map { response ->
            if (response.isSuccessful)
                response.body()?.body?.results?.map { character ->
                    localDatabase.insertComic(
                        ComicEntity(
                            1, character.name.toString(),
                            character.description.toString(),
                            "${character.thumbnail?.path}.${character.thumbnail?.extension}"
                        )
                    )

                }
        }
    }

    override fun refreshEvents() {
        marvelServiceImpl.getAllEvents().map { response ->
            if (response.isSuccessful)
                response.body()?.body?.results?.map { event ->
                    localDatabase.insertComic(
                        ComicEntity(
                            1, event.title.toString(),
                            event.description.toString(),
                            "${event.thumbnail?.path}.${event.thumbnail?.extension}"
                        )
                    )

                }
        }
    }

    override fun getComicById(comicId: Int): Observable<State<List<Comic>>> {
        return wrapWithState { marvelServiceImpl.getComicDetailById(comicId) }
    }

    override fun getComicsByCharacterId(characterId: Int): Observable<State<List<Comic>>> {
        return wrapWithState { marvelServiceImpl.getComicsByCharacterId(characterId) }
    }

    override fun getAllSeries(
        titleStartsWith: String?,
        contains: String?,
    ): Observable<State<List<Series>>> {
        return wrapWithState { marvelServiceImpl.getAllSeries(titleStartsWith, contains) }
    }

    override fun getCharactersByComicId(comicId: Int): Observable<State<List<Character>>> {
        return wrapWithState { marvelServiceImpl.getCharactersByComicId(comicId) }
    }

    override fun getSeriesById(seriesId: Int): Observable<State<List<Series>>> {
        return wrapWithState { marvelServiceImpl.getSeriesById(seriesId) }
    }

    override fun getAllEvents(query: String?): Observable<State<List<Event>>> {
        return wrapWithState { marvelServiceImpl.getAllEvents(query) }
    }


    override fun getCreatorByComicId(comicId: Int): Observable<State<List<Creator>>> {
        return wrapWithState { marvelServiceImpl.getCreatorByComicId(comicId) }
    }

    override fun getCharactersByEventId(eventId: Int): Observable<State<List<Character>>> {
        return wrapWithState { marvelServiceImpl.getCharactersByEventId(eventId) }
    }

    override fun getAllCharacters(nameStartsWith: String?): Observable<State<List<Character>>> {
        return wrapWithState { marvelServiceImpl.getAllCharacters(nameStartsWith) }
    }


    override fun getCharacterById(characterId: Int): Observable<State<List<Character>>> {
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

    override fun getComicsByStoryId(storyId: Int): Observable<State<List<Comic>>> {
        return wrapWithState { marvelServiceImpl.getComicsByStoryId(storyId) }
    }

    override fun getSeriesByCharacterId(
        characterId: Int
    ): Observable<State<List<Series>>> {
        return wrapWithState { marvelServiceImpl.getSeriesByCharacterId(characterId) }
    }

    override fun getEventById(
        eventId: Int
    ): Observable<State<List<Event>>> {
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