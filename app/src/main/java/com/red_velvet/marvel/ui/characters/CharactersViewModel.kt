package com.red_velvet.marvel.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel

class CharactersViewModel : BaseViewModel(), CharacterDetailsInteractionListener {
    private val _characters: MutableLiveData<State<List<CharactersResponse>>> = MutableLiveData()
    val characters: LiveData<State<List<CharactersResponse>>> get() = _characters

    private var _searchQuery = MutableLiveData<String>()
    private val searchQuery: LiveData<String> get() = _searchQuery

    private val _filteredCharacters = MediatorLiveData<State<List<CharactersResponse>>>()
    val filteredCharacters: LiveData<State<List<CharactersResponse>>> get() = _filteredCharacters


    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    init {
        _filteredCharacters.addSource(_characters) { updateFilteredCharacters() }
        _filteredCharacters.addSource(searchQuery) { updateFilteredCharacters() }
        getCharacters()
    }

    private fun updateFilteredCharacters() {
        val query = searchQuery.value.orEmpty()
        if (query.isNotEmpty()) {
            bindStateUpdates(
                repository.searchCharacters(query),
                onNext = ::onGetFilteredCharactersSuccess,
                onError = ::onGetCharactersError
            )
        } else {
            _filteredCharacters.value = _characters.value
        }
    }

    private fun onGetFilteredCharactersSuccess(state: State<List<CharactersResponse>?>) {
        state.toData()?.let { _filteredCharacters.postValue(State.Success(it)) }
    }

    fun getCharacters() {
        bindStateUpdates(
            repository.getCharacters(),
            onNext = ::onGetCharactersSuccess,
            onError = ::onGetCharactersError
        )
    }

    private fun onGetCharactersSuccess(state: State<List<CharactersResponse>?>) {
        _characters.postValue(State.Loading)
        state.toData()?.let { _characters.postValue(State.Success(it)) }
    }

    fun searchCharacters(query: String) {
        _searchQuery.value = query
        bindStateUpdates(
            repository.searchCharacters(query),
            onNext = ::onGetCharactersSuccess,
            onError = ::onGetCharactersError
        )
    }

    private fun onGetCharactersError(error: Throwable) {
        _characters.postValue(State.Failed(error.message.toString()))
    }

    override fun onCharacterSelected(character: CharactersResponse) {
        TODO("Not yet implemented")
    }
}