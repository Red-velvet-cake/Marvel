package com.red_velvet.marvel.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseInteractionListener
import com.red_velvet.marvel.ui.base.BaseViewModel

class EventsViewModel : BaseViewModel(), BaseInteractionListener {

    private val repository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private val _events = MutableLiveData<State<List<EventsResponse>>>()
    val events: LiveData<State<List<EventsResponse>>>
        get() = _events

    init {
        getAllEvents()
    }

    fun getAllEvents(query: String? = null) {
        getData(_events, repository.getEvents(query))
    }


}