package com.red_velvet.marvel.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.EventsResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit

class EventsViewModel : BaseViewModel(), EventsInteractionListener {

    private val repository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private val _events = MutableLiveData<State<List<EventsResponse>>>()
    val events: LiveData<State<List<EventsResponse>>>
        get() = _events

    val searchQuery = MutableLiveData<String>()

    init {
        getAllEvents()
        initSearchObservable()
    }

    private fun getAllEvents() {
        bindStateUpdates(
            repository.getEvents(),
            ::onGetAllEventsError,
            ::onGetAllEventsSuccess)
    }

    private fun searchEvents(query: String? = null) {
        bindStateUpdates(
            repository.getEvents(query),
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

    private fun initSearchObservable() {
        Observable.create { emitter ->
            searchQuery.observeForever { query ->
                emitter.onNext(query)
            }
        }.debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { query ->
                if (query.isEmpty()) {
                    getAllEvents()
                } else {
                    searchEvents(query)
                }
            }.addTo(compositeDisposable)
    }


}