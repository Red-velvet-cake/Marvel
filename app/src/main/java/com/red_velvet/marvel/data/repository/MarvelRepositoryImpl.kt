package com.red_velvet.marvel.data.repository


import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.model.MarvelResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.model.StoryResponse
import com.red_velvet.marvel.data.remote.MarvelService
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.MarvelRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MarvelRepositoryImpl(
    private val marvelServiceImpl: MarvelService
) : MarvelRepository {

    override fun getComics(): Observable<State<List<ComicsResponse>?>> {
        return wrapWithState { marvelServiceImpl.getAllComics() }
    }

    private fun <T> wrapWithState(function: () -> Single<Response<MarvelResponse<T>>>): Observable<State<T?>> {
        return function()
            .map { responseWrapper: Response<MarvelResponse<T>> ->
                if (responseWrapper.isSuccessful) {
                    State.Success(responseWrapper.body()?.body?.results)
                } else {
                    State.Failed(responseWrapper.message())
                }
            }.startWith(Observable.just(State.Loading))
    }

    override fun getComicDetail(comicId: Int): Observable<State<List<ComicsResponse>?>> {
        return wrapWithState { marvelServiceImpl.getComicDetail(comicId) }
    }

    override fun getComicsByCharacterId(characterId: Int): Observable<State<List<ComicsResponse>?>> {
        return wrapWithState { marvelServiceImpl.getComicsByCharacterId(characterId) }
    }


    override fun getAllSeries(): Observable<State<List<SeriesResponse>?>> {
        return wrapWithState { marvelServiceImpl.getAllSeries() }
    }

    override fun getCharsByComicId(comicId: Int): Observable<State<List<CharactersResponse>?>> {
        return wrapWithState { marvelServiceImpl.getCharsByComicId(comicId) }
    }

    override fun getSeriesDetails(seriesId: Int): Observable<State<List<SeriesResponse>?>> {
        return wrapWithState { marvelServiceImpl.getSerieDetails(seriesId) }
    }

    override fun getEvents(query: String?): Observable<State<List<EventsResponse>?>> {
        return wrapWithState { marvelServiceImpl.getAllEvents(query) }

    }

    override fun getComicCreatorByComicId(comicId: Int): Observable<State<List<CreatorsResponse>?>> {
        return wrapWithState { marvelServiceImpl.getComicCreatorByComicId(comicId) }
    }

    override fun getCharactersByEventId(eventId: Int): Observable<State<List<CharactersResponse>?>> {
        return wrapWithState { marvelServiceImpl.getCharactersByEventId(eventId) }
    }

    override fun getCharacters(): Observable<State<List<CharactersResponse>?>> {
        return wrapWithState { marvelServiceImpl.getCharacters() }
    }

    override fun getCharacterByCharacterId(characterId: Int): Observable<State<List<CharactersResponse>?>> {
        return wrapWithState { marvelServiceImpl.getCharacterByCharacterId(characterId) }
    }


    override fun getCreatorsByEventId(eventId: Int): Observable<State<List<CreatorsResponse>?>> {
        return wrapWithState { marvelServiceImpl.getCreatorsByEventId(eventId) }
    }

    override fun getStories(): Observable<State<List<StoryResponse>?>> {
        return wrapWithState { marvelServiceImpl.getAllStories() }
    }

    override fun getStory(storyId: Int): Observable<State<List<StoryResponse>?>> {
        return wrapWithState { marvelServiceImpl.getStory(storyId) }
    }

    override fun getStoryCreatorsByStoryId(storyId: Int): Observable<State<List<CreatorsResponse>?>> {
        return wrapWithState { marvelServiceImpl.getStoryCreatorsByStoryId(storyId) }
    }

    override fun getComicsByStoryId(storyId: Int): Observable<State<List<ComicsResponse>?>> {
        return wrapWithState { marvelServiceImpl.getComicsByStoryId(storyId) }
    }

    override fun getSeriesByCharacterId(
        characterId: Int
    ): Observable<State<List<SeriesResponse>?>> {
        return wrapWithState { marvelServiceImpl.getSeriesByCharacterId(characterId) }
    }

    override fun getSerieCreatorsBySeriesId(seriesId: Int): Observable<State<List<CreatorsResponse>?>> {
        return wrapWithState { marvelServiceImpl.getSerieCreatorsBySeriesId(seriesId) }
    }
}
