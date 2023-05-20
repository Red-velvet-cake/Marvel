package com.red_velvet.marvel.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.domain.models.Event
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    BaseViewModel(), EventsInteractionListener {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = _events

    private val _navigationToEventDetails = MutableLiveData<SingleEvent<Int>>()
    val navigationToEventDetails: LiveData<SingleEvent<Int>> = _navigationToEventDetails

    val searchQuery = MutableLiveData<String>()

    init {
        getAllEvents()
        initSearchObservable()
    }

    private fun getAllEvents(query: String = "") {
        repository.getAllEvents(query)
            .subscribe(
                { if (it.isNotEmpty()) _events.postValue(it) },
                {}
            )
            .addTo(compositeDisposable)
    }

    private fun initSearchObservable() {
        Observable.create { emitter ->
            searchQuery.observeForever { query ->
                emitter.onNext(query)
            }
        }.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { query ->
                getAllEvents(query)
            }.addTo(compositeDisposable)
    }

    override fun doOnEventClicked(eventId: Int) {
        _navigationToEventDetails.postValue(SingleEvent(eventId))
    }

}