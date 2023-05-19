package com.red_velvet.marvel.data.repository


import com.red_velvet.marvel.data.entity.CharsEntity
import com.red_velvet.marvel.data.entity.ComicsEntity
import com.red_velvet.marvel.data.entity.EventsEntity
import com.red_velvet.marvel.data.entity.EventsSearch
import com.red_velvet.marvel.data.entity.SeriesSearch
import com.red_velvet.marvel.data.local.MovieDataBase
import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.CharacterDto
import com.red_velvet.marvel.data.model.ComicDto
import com.red_velvet.marvel.data.model.Creator
import com.red_velvet.marvel.data.model.EventDto
import com.red_velvet.marvel.data.model.Series
import com.red_velvet.marvel.data.model.Story
import com.red_velvet.marvel.domain.mappers.ComicsMapper
import com.red_velvet.marvel.data.remote.MarvelService
import com.red_velvet.marvel.domain.mappers.CharsMapper
import com.red_velvet.marvel.domain.mappers.EventMapper
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val marvelServiceImpl: MarvelService,
    private  val daoMovie : MovieDataBase,
    private val  comicsMapper: ComicsMapper,
    private val  eventMapper: EventMapper,
    private val charsMapper: CharsMapper

) : MarvelRepository {

    override fun getAllComics(
        titleStartsWith: String?,
        dateDescriptor: String?
    ): Observable<State<List<ComicDto>>> {
        return wrapWithState { marvelServiceImpl.getAllComics(titleStartsWith, dateDescriptor) }
    }

    override fun getAllComics(): Observable<List<ComicsEntity>> {
        return daoMovie.movieDao().getAllComics()
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

    override fun getAllEvents(query: String?): Observable<State<List<EventDto>>> {
        return wrapWithState { marvelServiceImpl.getAllEvents(query) }
    }

    override fun getAllEvents(): Observable<List<EventsEntity>> {
        return daoMovie.movieDao().getAllEvents()
    }

    override fun getCreatorByComicId(comicId: Int): Observable<State<List<Creator>>> {
        return wrapWithState { marvelServiceImpl.getCreatorByComicId(comicId) }
    }

    override fun getCharactersByEventId(eventId: Int): Observable<State<List<CharacterDto>>> {
        return wrapWithState { marvelServiceImpl.getCharactersByEventId(eventId) }
    }

    override fun getAllCharacters(nameStartsWith: String?): Observable<State<List<CharacterDto>>> {
        return wrapWithState { marvelServiceImpl.getAllCharacters(nameStartsWith) }
    }

    override fun getAllCharacters(): Observable<List<CharsEntity>> {
       return  daoMovie.movieDao().getAllChars()
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

    override fun refreshComics() {
        marvelServiceImpl.getAllComics().map { response ->
            if (response.isSuccessful)
                response.body()?.body?.results?.map { comic ->
                    daoMovie.movieDao().insertComics(
                        ComicsEntity(
                            comic.id!!, comic.title.toString(),
                            "${comic.thumbnail?.path}.${comic.thumbnail?.extension}"
                        )
                    )

                }
        }
    }



    override fun refreshEvents() {
        marvelServiceImpl.getAllEvents().map { response ->
            if (response.isSuccessful)
                response.body()?.body?.results?.map { event ->
                    daoMovie.movieDao().insertEvents(
                        EventsEntity(
                            event.id!!, event.title.toString(),
                            "${event.thumbnail?.path}.${event.thumbnail?.extension}"
                        )
                    )

                }
        }
    }

    override fun refreshEventsSearch() {
        marvelServiceImpl.getAllEvents().map { response ->
            if (response.isSuccessful)
                response.body()?.body?.results?.map { event ->
                    daoMovie.movieDao().insertEventsSearch(
                        EventsSearch(
                            event.id!!, event.title.toString(),
                            "${event.thumbnail?.path}.${event.thumbnail?.extension}"
                        )
                    )

                }
        }
    }

    override fun refreshSeries() {
        marvelServiceImpl.getAllSeries().map { response ->
            if (response.isSuccessful)
                response.body()?.body?.results?.map { series ->
                    daoMovie.movieDao().insertSeries(
                        SeriesSearch(
                            series.id!!, series.title.toString(),
                            "${series.thumbnail?.path}.${series.thumbnail?.extension}"
                        )
                    )

                }
        }
    }

    override fun getSeries(): Observable<List<SeriesSearch>> {
      return  daoMovie.movieDao().getAllSeries()
    }

    override fun getEventsSearch(): Observable<List<EventsSearch>> {
        return  daoMovie.movieDao().getAllEventsSearch()
    }



    override fun refreshCharacters(){
        marvelServiceImpl.getAllCharacters().map { response ->
            if (response.isSuccessful)
                response.body()?.body?.results?.map { character ->
                    daoMovie.movieDao().insertChars(
                        CharsEntity(
                            character.id!!, character.name.toString(),
                            "${character.thumbnail?.path}.${character.thumbnail?.extension}"
                        )
                    )

                }
        }
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