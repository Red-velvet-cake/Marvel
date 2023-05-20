package com.red_velvet.marvel.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.domain.model.Character
import com.red_velvet.marvel.domain.model.Comic
import com.red_velvet.marvel.domain.model.Event
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MarvelRepository,
) : BaseViewModel(), HomeInteractionListener {

    private val _navigationToComicDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToComicDetails: LiveData<SingleEvent<Int>> = _navigationToComicDetails

    private val _navigationToEventDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToEventDetails: LiveData<SingleEvent<Int>> = _navigationToEventDetails

    private val _navigationToCharacterDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCharacterDetails: LiveData<SingleEvent<Int>> = _navigationToCharacterDetails

    private val _comics = MutableLiveData<List<Comic>>()
    val comicsLiveData: LiveData<List<Comic>> = _comics

    private val _events = MutableLiveData<List<Event>>()
    val eventLiveData: LiveData<List<Event>> = _events

    private val _characters = MutableLiveData<List<Character>>()
    val characterLiveData: LiveData<List<Character>> = _characters

    init {
        repository.refreshComics().subscribe()
        repository.refreshEvents().subscribe()
        repository.refreshCharacters().subscribe()
        getComics()
        getEvents()
        getCharacters()
    }

    private fun getComics() {
        bindObservable(
            repository.getLocalComics(),
            ::onComicsError,
            ::onComicsReceived,
        )
    }

    private fun onComicsReceived(comics: List<Comic>) {
        _comics.postValue(comics)
        Log.d("HomeViewModel", "onComicsReceived: $comics")
    }

    private fun onComicsError(throwable: Throwable) {
        _comics.postValue(emptyList())
    }

    private fun getEvents() {
        bindObservable(
            repository.getLocalEvents(),
            ::onEventsError,
            ::onEventsReceived,
        )
    }

    private fun onEventsReceived(events: List<Event>) {
        _events.postValue(events)
        Log.d("HomeViewModel", "onEventsReceived: $events")
    }

    private fun onEventsError(throwable: Throwable) {
        _events.postValue(emptyList())
    }

    private fun getCharacters() {
        bindObservable(
            repository.getLocalCharacters(),
            ::onCharactersError,
            ::onCharactersReceived,
        )
    }

    private fun onCharactersReceived(characters: List<Character>) {
        _characters.postValue(characters)
        Log.d("HomeViewModel", "onCharactersReceived: $characters")
    }

    private fun onCharactersError(throwable: Throwable) {
        _characters.postValue(emptyList())
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
}