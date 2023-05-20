package com.red_velvet.marvel.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.domain.models.Character
import com.red_velvet.marvel.domain.models.SearchQuery
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.SearchInteractionListener
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    BaseViewModel(), CharacterDetailsInteractionListener, SearchInteractionListener {
    private val _characters: MutableLiveData<List<Character>> = MutableLiveData()
    val characters: LiveData<List<Character>> = _characters

    val searchQuery = MutableLiveData<String>()

    private val _navigationToCharacterDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCharacterDetails: LiveData<SingleEvent<Int>> = _navigationToCharacterDetails

    private val _searchQueries: MutableLiveData<List<SearchQuery>> = MutableLiveData()
    val searchQueries: LiveData<List<SearchQuery>> = _searchQueries

    init {
        getAllCharacters()
        initSearchObservable()
        getSearchedQueries()
    }

    private fun initSearchObservable() {
        Observable.create { emitter ->
            searchQuery.observeForever { query ->
                emitter.onNext(query)
            }
        }.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { query ->
                repository.insertSearchQuery(query).subscribe()
                getAllCharacters(query)
            }.addTo(compositeDisposable)
    }

    private fun getAllCharacters(titleStartsWith: String = "") {
        repository.getAllCharacters(titleStartsWith)
            .subscribe(
                { if (it.isNotEmpty()) _characters.postValue(it) },
                {}
            )
            .addTo(compositeDisposable)
    }

    override fun doOnCharacterClicked(characterId: Int) {
        _navigationToCharacterDetails.postValue(SingleEvent(characterId))
    }

    private fun getSearchedQueries() {
        repository.getSearchQueries().subscribe { queries ->
            _searchQueries.postValue(queries)
        }.addTo(compositeDisposable)
    }

    override fun doOnSearchQueryClicked(query: String) {
        searchQuery.postValue(query)
    }

    override fun doOnSearchQueryDeleteClicked(id: Int) {
        repository.deleteSearchQuery(id).subscribe()
    }

}