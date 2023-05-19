package com.red_velvet.marvel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.entity.CharsEntity
import com.red_velvet.marvel.data.entity.ComicsEntity
import com.red_velvet.marvel.data.entity.EventsEntity
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
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


    private val _events = MutableLiveData<List<EventsEntity>>()
    val eventLiveData: LiveData<List<EventsEntity>> = _events

    private val _characters = MutableLiveData<List<CharsEntity>>()
    val characterLiveData: LiveData<List<CharsEntity>> = _characters

    private val _comics = MutableLiveData<List<ComicsEntity>>()
    val comics: LiveData<List<ComicsEntity>> get() = _comics

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
                ::onGetComicsState
            )
    }



    private fun onGetComicsState(comics: List<ComicsEntity>) {
        _comics.postValue(comics)
    }

    fun getEvents() {
        repository.getAllEvents().subscribeOn(Schedulers.io()) .observeOn(
            AndroidSchedulers
                .mainThread()
        )
            .subscribe(
                ::onGetEventsState
            )
    }



    private fun onGetEventsState(eventsEntity: List<EventsEntity>) {
        _events.postValue(eventsEntity)
    }

    fun getCharacters() {
        repository.getAllCharacters().subscribeOn(Schedulers.io()) .observeOn(
            AndroidSchedulers
                .mainThread()
        )
            .subscribe(
                ::onGetCharactersState
            )
    }



    private fun onGetCharactersState(charsEntity: List<CharsEntity>) {
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