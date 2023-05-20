package com.red_velvet.marvel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.domain.models.Character
import com.red_velvet.marvel.domain.models.Comic
import com.red_velvet.marvel.domain.models.Event
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    BaseViewModel(),
    HomeInteractionListener {

    private val _navigationToComicDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToComicDetails: LiveData<SingleEvent<Int>> = _navigationToComicDetails

    private val _navigationToEventDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToEventDetails: LiveData<SingleEvent<Int>> = _navigationToEventDetails

    private val _navigationToCharacterDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCharacterDetails: LiveData<SingleEvent<Int>> = _navigationToCharacterDetails

    private val _comics = MutableLiveData<List<Comic>>()
    val comicsLiveData: LiveData<List<Comic>> = _comics

    private val _events = MutableLiveData<List<Event>>()
    val eventsLiveData: LiveData<List<Event>> = _events

    private val _characters = MutableLiveData<List<Character>>()
    val charactersLiveData: LiveData<List<Character>> = _characters

    init {
        repository.refreshComics().subscribe({}, {}).addTo(compositeDisposable)
        repository.refreshCharacters().subscribe({}, {}).addTo(compositeDisposable)
        repository.refreshEvents().subscribe({}, {}).addTo(compositeDisposable)
        getComics()
        getEvents()
        getCharacters()
    }

    private fun getComics() {
        repository.getAllComics()
            .subscribe(::onGetComicsState, ::onGetComicsFailure)
            .addTo(compositeDisposable)
    }

    private fun onGetComicsFailure(throwable: Throwable) {
    }

    private fun onGetComicsState(state: List<Comic>) {
        _comics.postValue(state)
    }

    private fun getEvents() {
        repository.getAllEvents()
            .subscribe(::onGetEventsState, ::onGetEventsFailure)
            .addTo(compositeDisposable)
    }

    private fun onGetEventsFailure(throwable: Throwable) {
    }

    private fun onGetEventsState(state: List<Event>) {
        _events.postValue(state)
    }

    private fun getCharacters() {
        repository.getAllCharacters()
            .subscribe(::onGetCharactersState, ::onGetCharactersFailure)
            .addTo(compositeDisposable)
    }

    private fun onGetCharactersFailure(throwable: Throwable) {
    }

    private fun onGetCharactersState(state: List<Character>) {
        _characters.postValue(state)
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