package com.red_velvet.marvel.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.domain.model.Character
import com.red_velvet.marvel.domain.model.Comic
import com.red_velvet.marvel.domain.model.Event
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State
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

    private val _events = MutableLiveData<State<List<Event>>>(State.Loading)
    val eventLiveData: LiveData<State<List<Event>>> = _events

    private val _characters = MutableLiveData<State<List<Character>>>(State.Loading)
    val characterLiveData: LiveData<State<List<Character>>> = _characters

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