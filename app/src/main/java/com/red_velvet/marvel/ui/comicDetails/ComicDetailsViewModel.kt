package com.red_velvet.marvel.ui.comicDetails

import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.*
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.MarvelRepository
import com.red_velvet.marvel.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class ComicDetailsViewModel : BaseViewModel() {
    private val comicId = 82967


    private val _comicsDetails: MutableLiveData<State<ComicsResponse>> = MutableLiveData()
    val comicsDetails: MutableLiveData<State<ComicsResponse>> get() = _comicsDetails

    private val _creators: MutableLiveData<State<List<CreatorsResponse>>> = MutableLiveData()
    val creators: MutableLiveData<State<List<CreatorsResponse>>> get() = _creators

    private val _characters: MutableLiveData<State<List<CharactersResponse>>> = MutableLiveData()
    val characters: MutableLiveData<State<List<CharactersResponse>>> get() = _characters


    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    init {
        getComicsDetails(comicId)
        getCreators(comicId)
        getCharacters()
    }

    private fun getComicsDetails(comicId: Int) {

        _comicsDetails.postValue(State.Loading)

        val disposable = repository.getComicDetail(comicId)
            .doOnError {
                onGetComicDetailsError(it)
            }
            .doOnSuccess {
                onGetComicDetailsSuccess(it)
            }
            .subscribeBy(
                onError = ::onGetComicDetailsError, onSuccess = ::onGetComicDetailsSuccess
            )

        disposable.addTo(compositeDisposable)

    }

    private fun getCreators(comicId: Int) {
        _creators.postValue(State.Loading)

        val disposable = repository.getComicCreatorByComicId(comicId)
            .doOnError {
                onGetCreatorsError(it)
            }
            .doOnSuccess {
                onGetCreatorsSuccess(it)
            }
            .subscribeBy(
                onError = ::onGetCreatorsError, onSuccess = ::onGetCreatorsSuccess
            )

        disposable.addTo(compositeDisposable)

    }

    private fun getCharacters() {
        _creators.postValue(State.Loading)

        val disposable = repository.getCh(comicId)
            .doOnError {
                onGetCreatorsError(it)
            }
            .doOnSuccess {
                onGetCreatorsSuccess(it)
            }
            .subscribeBy(
                onError = ::onGetCreatorsError, onSuccess = ::onGetCreatorsSuccess
            )

        disposable.addTo(compositeDisposable)



    }


    private fun onGetComicDetailsError(error: Throwable) {
        _comicsDetails.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetCreatorsError(error: Throwable) {
        _creators.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetCharactersError(error: Throwable) {
        _characters.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetComicDetailsSuccess(response: BaseResponse<ComicsResponse>) {
        _comicsDetails.postValue(State.Success(response.data?.results?.first()!!))
    }

    private fun onGetCreatorsSuccess(response: BaseResponse<CreatorsResponse>) {
        _creators.postValue(State.Success(response.data?.results!!))
    }

    private fun onGetCharactersSuccess(response: BaseResponse<CharactersResponse>) {
        _characters.postValue(State.Success(response.data?.results!!))
    }


}