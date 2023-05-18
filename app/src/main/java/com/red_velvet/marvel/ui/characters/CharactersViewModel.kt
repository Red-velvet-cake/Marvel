package com.red_velvet.marvel.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.dto.CharacterDto
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit

class CharactersViewModel : BaseViewModel(), CharacterDetailsInteractionListener {
    private val _characters: MutableLiveData<State<List<CharacterDto>>> = MutableLiveData()
    val characters: LiveData<State<List<CharacterDto>>> = _characters

    val searchQuery = MutableLiveData<String>()

    private val repository: MarvelRepository by lazy {
        MarvelRepositoryImpl(RetrofitClient.apiService)
    }

    private val _navigationToCharacterDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCharacterDetails: LiveData<SingleEvent<Int>> = _navigationToCharacterDetails

    init {
        getAllCharacters()
        initSearchObservable()
    }

    private fun initSearchObservable() {
        Observable.create { emitter ->
            searchQuery.observeForever { query ->
                emitter.onNext(query)
            }
        }.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { query ->
                if (query.isEmpty()) {
                    getAllCharacters()
                } else {
                    getAllCharacters(query)
                }
            }.addTo(compositeDisposable)
    }

    fun getAllCharacters(nameStartsWith: String? = null) {
        bindStateUpdates(
            repository.getAllCharacters(nameStartsWith),
            onNext = ::onGetCharactersState,
            onError = ::onGetCharactersError
        )
    }

    private fun onGetCharactersState(state: State<List<CharacterDto>>) {
        _characters.postValue(state)
    }

    private fun onGetCharactersError(error: Throwable) {
        _characters.postValue(State.Failed(error.message.toString()))
    }

    override fun doOnCharacterClicked(characterId: Int) {
        _navigationToCharacterDetails.postValue(SingleEvent(characterId))
    }
}