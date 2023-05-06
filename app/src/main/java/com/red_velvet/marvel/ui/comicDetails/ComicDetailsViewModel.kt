package com.red_velvet.marvel.ui.comicDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.CharactersResponse
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepository
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.comicDetails.adapter.ComicDetailsCharacterListenerInteraction
import com.red_velvet.marvel.ui.comicDetails.adapter.ComicDetailsCreatorListenerInteraction

class ComicDetailsViewModel : BaseViewModel(), ComicDetailsCreatorListenerInteraction,
    ComicDetailsCharacterListenerInteraction {

    private val _comicsDetails: MutableLiveData<State<List<ComicsResponse>>> = MutableLiveData()
    val comicsDetails: LiveData<State<List<ComicsResponse>>> get() = _comicsDetails

    private val _creators: MutableLiveData<State<List<CreatorsResponse>>> = MutableLiveData()
    val creators: LiveData<State<List<CreatorsResponse>>> get() = _creators

    private val _characters: MutableLiveData<State<List<CharactersResponse>>> = MutableLiveData()
    val characters: LiveData<State<List<CharactersResponse>>> get() = _characters

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    fun fetchData(comicId: Int) {
        fetchComicDetailsData(comicId)
        fetchCreatorsByComicIDData(comicId)
        fetchCharactersByComicIDData(comicId)
    }

    override fun onClickCharacter(creator: CreatorsResponse) {
        TODO("Not yet implemented")
    }

    override fun onClickCreator(creator: CreatorsResponse) {
        TODO("Not yet implemented")
    }


    private fun fetchComicDetailsData(comicId: Int) {
        bindStateUpdates(
            repository.getComicDetail(comicId),
            onNext = ::onGetComicDetailsDataSuccess,
            onError = ::onGetComicDetailsDataError
        )
    }


    private fun fetchCreatorsByComicIDData(comicId: Int) {
        bindStateUpdates(
            repository.getComicCreatorByComicId(comicId),
            onNext = ::onGetCreatorsByComicIDDataSuccess,
            onError = ::onGetCreatorsByComicIDDataError
        )
    }


    private fun fetchCharactersByComicIDData(comicId: Int) {
        bindStateUpdates(
            repository.getCharsByComicId(comicId),
            onNext = ::onGetCharactersByComicIDDataSuccess,
            onError = ::onGetCharactersByComicIDDataError
        )
    }


    private fun onGetComicDetailsDataSuccess(state: State<List<ComicsResponse>?>) {
        _comicsDetails.postValue(State.Loading)
        state.toData()?.let {
            _comicsDetails.postValue(State.Success(it))
        }
    }

    private fun onGetComicDetailsDataError(e: Throwable) {
        _comicsDetails.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetCreatorsByComicIDDataSuccess(state: State<List<CreatorsResponse>?>) {
        state.toData()?.let {
            _creators.postValue(State.Success(it))
        }
    }

    private fun onGetCreatorsByComicIDDataError(e: Throwable) {
        _creators.postValue(State.Failed(e.message.toString()))
    }

    private fun onGetCharactersByComicIDDataSuccess(state: State<List<CharactersResponse>?>) {
        state.toData()?.let {
            _characters.postValue(State.Success(it))
        }
    }

    private fun onGetCharactersByComicIDDataError(e: Throwable) {
        _characters.postValue(State.Failed(e.message.toString()))
    }


}