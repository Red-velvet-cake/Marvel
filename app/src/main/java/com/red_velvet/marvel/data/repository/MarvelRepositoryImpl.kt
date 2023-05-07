package com.red_velvet.marvel.data.repository


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

    override fun getComics(
        titleStartsWith: String?,
        dateDescriptor: String?
    ): Observable<State<List<Comic>?>> {
        return wrapWithState { marvelServiceImpl.getAllComics(titleStartsWith, dateDescriptor) }
    }

    private fun <T> wrapWithState(function: () -> Single<Response<BaseResponse<T>>>): Observable<State<T?>> {
        val responseObservable = function()
            .map { responseWrapper: Response<BaseResponse<T>> ->
                if (responseWrapper.isSuccessful) {
                    State.Success(responseWrapper.body()?.body?.results)
                } else {
                    State.Failed(responseWrapper.message())
                }
            }.startWith(Observable.just(State.Loading))

        return responseObservable
    }

    override fun getComicDetail(comicId: Int): Observable<State<List<Comic>?>> {
        return wrapWithState { marvelServiceImpl.getComicDetail(comicId) }
    }

    override fun getComicsByCharacterId(characterId: Int): Observable<State<List<Comic>?>> {
        return wrapWithState { marvelServiceImpl.getComicsByCharacterId(characterId) }
    }


    override fun getAllSeries(
        startYear: Int?,
        contains: String?
    ): Observable<State<List<Series>?>> {
        return wrapWithState { marvelServiceImpl.getAllSeries(contains = contains) }
    }

    override fun getCharsByComicId(comicId: Int): Observable<State<List<Character>?>> {
        return wrapWithState { marvelServiceImpl.getCharsByComicId(comicId) }
    }

    override fun getSeriesDetails(seriesId: Int): Observable<State<List<Series>?>> {
        return wrapWithState { marvelServiceImpl.getSerieDetails(seriesId) }
    }

    override fun getEvents(query: String?): Observable<State<List<Event>?>> {
        return wrapWithState { marvelServiceImpl.getAllEvents(query) }

    }

    override fun getComicCreatorByComicId(comicId: Int): Observable<State<List<Creator>?>> {
        return wrapWithState { marvelServiceImpl.getComicCreatorByComicId(comicId) }
    }

    override fun getCharactersByEventId(eventId: Int): Observable<State<List<Character>?>> {
        return wrapWithState { marvelServiceImpl.getCharactersByEventId(eventId) }
    }

    override fun getCharacters(): Observable<State<List<Character>?>> {
        return wrapWithState { marvelServiceImpl.getCharacters() }
    }

    override fun getCharacterByCharacterId(characterId: Int): Observable<State<List<Character>?>> {
        return wrapWithState { marvelServiceImpl.getCharacterByCharacterId(characterId) }
    }


    override fun getCreatorsByEventId(eventId: Int): Observable<State<List<Creator>?>> {
        return wrapWithState { marvelServiceImpl.getCreatorsByEventId(eventId) }
    }

    override fun getStories(): Observable<State<List<Story>?>> {
        return wrapWithState { marvelServiceImpl.getAllStories() }
    }

    override fun getStory(storyId: Int): Observable<State<List<Story>?>> {
        return wrapWithState { marvelServiceImpl.getStory(storyId) }
    }

    override fun getStoryCreatorsByStoryId(storyId: Int): Observable<State<List<Creator>?>> {
        return wrapWithState { marvelServiceImpl.getStoryCreatorsByStoryId(storyId) }
    }

    override fun getComicsByStoryId(storyId: Int): Observable<State<List<Comic>?>> {
        return wrapWithState { marvelServiceImpl.getComicsByStoryId(storyId) }
    }

    override fun getSeriesByCharacterId(
        characterId: Int
    ): Observable<State<List<Series>?>> {
        return wrapWithState { marvelServiceImpl.getSeriesByCharacterId(characterId) }
    }


    override fun getEventDetails(
        eventId: Int
    ): Observable<State<List<Event>?>> {
        return wrapWithState { marvelServiceImpl.getEventDetails(eventId) }
    }

    override fun getSerieCreatorsBySeriesId(seriesId: Int): Observable<State<List<Creator>?>> {
        return wrapWithState { marvelServiceImpl.getSerieCreatorsBySeriesId(seriesId) }
    }

    override fun searchCharacters(nameStartsWith: String?): Observable<State<List<Character>?>> {
        return wrapWithState { marvelServiceImpl.searchCharacters(nameStartsWith) }
    }
}
