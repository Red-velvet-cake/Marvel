package com.red_velvet.marvel.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.Character
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit

class CharactersViewModel : BaseViewModel(), CharacterDetailsInteractionListener {
    private val _characters: MutableLiveData<State<List<Character>>> = MutableLiveData()
    val characters: LiveData<State<List<Character>>> get() = _characters

    val searchQuery = MutableLiveData<String>()

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    init {
        getCharacters()
        searchResult()
    }

    private fun searchResult() {
        Observable.create { emitter ->
            searchQuery.observeForever { query ->
                if (query != null) {
                    emitter.onNext(query)
                }
            }
        }.debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { query ->
                if (query.isEmpty()) {
                    getCharacters()
                } else {
                    searchCharacters(query)
                }
            }.addTo(compositeDisposable)
    }

    private fun getCharacters() {
        bindStateUpdates(
            repository.getCharacters(),
            onNext = ::onGetCharactersSuccess,
            onError = ::onGetCharactersError
        )
    }

    private fun onGetCharactersSuccess(state: State<List<Character>?>) {
        _characters.postValue(State.Loading)
        state.toData()?.let { _characters.postValue(State.Success(it)) }
    }

    private fun onGetCharactersError(error: Throwable) {
        _characters.postValue(State.Failed(error.message.toString()))
    }

    private fun searchCharacters(query: String) {
        if (query.isEmpty()) {
            getCharacters()
        } else {
            bindStateUpdates(
                repository.searchCharacters(query),
                onNext = ::onGetCharactersSuccess,
                onError = ::onGetCharactersError
            )
        }
    }

    override fun onCharacterSelected(character: Character) {
        TODO("Not yet implemented")
    }
}