package com.red_velvet.marvel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.entity.CharsEntity
import com.red_velvet.marvel.data.entity.ComicsEntity
import com.red_velvet.marvel.data.entity.EventsEntity
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.domain.models.Chars
import com.red_velvet.marvel.domain.models.Comic
import com.red_velvet.marvel.domain.models.Event
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val repository: MarvelRepository)  : BaseViewModel(), HomeInteractionListener {


    private val _navigationToComicDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToComicDetails: LiveData<SingleEvent<Int>> = _navigationToComicDetails

    private val _navigationToEventDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToEventDetails: LiveData<SingleEvent<Int>> = _navigationToEventDetails

    private val _navigationToCharacterDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCharacterDetails: LiveData<SingleEvent<Int>> = _navigationToCharacterDetails


    private val _events = MutableLiveData<List<Event>>()
    val eventLiveData: LiveData<List<Event>> = _events

    private val _characters = MutableLiveData<List<Chars>>()
    val characterLiveData: LiveData<List<Chars>> = _characters

    private val _comics = MutableLiveData<List<Comic>>()
    val comics: LiveData<List<Comic>> get() = _comics

    init {
        getComics()
        getEvents()
        getCharacters()
    }



    fun getComics() {
        repository.getAllComics().subscribeOn(Schedulers.io()) .observeOn(
                AndroidSchedulers
                    .mainThread()
                )
            .subscribe(
                ::onGetComicsSucccess,
                ::onGetComicsFailed
            )
    }



    private fun onGetComicsSucccess(comics: List<Comic>) {
        _comics.postValue(comics)
    }
    private fun onGetComicsFailed(throwable: Throwable) {
        _comics.postValue(emptyList())
    }

    fun getEvents() {
        repository.getAllEvents().subscribeOn(Schedulers.io()) .observeOn(
            AndroidSchedulers
                .mainThread()
        )
            .subscribe(
                ::onGetEventsSuccess,
            ::onGetEventsFailed
            )
    }
    private fun onGetEventsFailed(throwable: Throwable) {
        _events.postValue(emptyList())
    }



    private fun onGetEventsSuccess(eventsEntity: List<Event>) {
        _events.postValue(eventsEntity)
    }

    fun getCharacters() {
        repository.getAllCharacters().subscribeOn(Schedulers.io()) .observeOn(
            AndroidSchedulers
                .mainThread()
        )
            .subscribe(
                ::onGetCharactersState,
                ::onGetCharsFailed
            )
    }

    private fun onGetCharsFailed(throwable: Throwable) {
        _events.postValue(emptyList())
    }

    private fun onGetCharactersState(charsEntity: List<Chars>) {
        _characters.postValue(charsEntity)
    }

    override fun doOnComicClicked(comicId: Int) {
        _navigationToComicDetails.postValue(SingleEvent(comicId))
    }

    override fun doOnEventClicked(eventId: Int) {
        _navigationToEventDetails.postValue(SingleEvent(eventId))
    }

    override fun doOnCharacterClicked(charId: Int) {
        _navigationToCharacterDetails.postValue(SingleEvent(charId))
    }

    companion object {
        private const val UNKNOWN_ERROR = "Unknown error"
    }
}