package com.red_velvet.marvel.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseInteractionListener
import com.red_velvet.marvel.ui.base.BaseViewModel
;
import java.util.concurrent.TimeUnit

class EventsViewModel : BaseViewModel(), BaseInteractionListener {

    private val repository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private val _events = MutableLiveData<State<List<EventsResponse>>>()
    val events: LiveData<State<List<EventsResponse>>>
        get() = _events

//    val searchResults: LiveData<State<List<EventsResponse>>> = Transformations.switchMap(searchQuery) { query ->
//        repository.getEvents(query)
//    }

    val searchQuery = MutableLiveData<String>()

    init {
        getAllEvents()
        searchResult()
    }

    private fun search(query: String? = null) {
        bindStateUpdates(
            repository.getEvents(query),
            ::onGetAllEventsError,
            ::onGetAllEventsSuccess)
    }

    private fun getAllEvents() {
        bindStateUpdates(
            repository.getEvents(),
            ::onGetAllEventsError,
            ::onGetAllEventsSuccess)
    }

    private fun onGetAllEventsSuccess(eventsResponse: State<List<EventsResponse>?>){
        _events.postValue(State.Loading)
        eventsResponse.toData()?.let { _events.postValue(State.Success(it)) }
    }

    private fun onGetAllEventsError(e:Throwable){
        _events.postValue(State.Loading)
        _events.postValue(State.Failed(e.message.toString()))
    }
    private fun searchResult(){

        searchQuery.observeForever { query ->
            if (query == null || query.isEmpty()) {
                getAllEvents()
            } else {
                search(query)
            }
        }
    }


}