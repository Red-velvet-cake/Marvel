package com.red_velvet.marvel.ui.characters

import androidx.lifecycle.LiveData
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

    private val _filteredCharacters: MutableLiveData<List<CharactersResponse>> = MutableLiveData()
    val filteredCharacters: LiveData<List<CharactersResponse>> get() = _filteredCharacters

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    fun getCharacters() {
        bindStateUpdates(
            repository.getCharacters(),
            onNext = ::onGetCharactersSuccess,
            onError = ::onGetCharactersError)
    }

    private fun onGetCharactersSuccess(state: State<List<CharactersResponse>?>) {
        _characters.postValue(State.Loading)
        state.toData()?.let { _characters.postValue(State.Success(it)) }
    }

    private fun onGetCharactersError(error: Throwable) {
        _characters.postValue(State.Failed(error.message.toString()))
    }

    fun filterCharacters(query: String) {
        _characters.value?.toData()?.let { allCharacters ->
            val filteredList = allCharacters.filter { character ->
                character.name?.contains(query, ignoreCase = true) == true
            }
            _filteredCharacters.postValue(filteredList)
        }
    }

    override fun onCharacterSelected(character: CharactersResponse) {
        TODO("Not yet implemented")
    }
}