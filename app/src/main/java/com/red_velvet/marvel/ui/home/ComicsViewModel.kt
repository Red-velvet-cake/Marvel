package com.red_velvet.marvel.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.ComicsResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.MarvelRepository
import com.red_velvet.marvel.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class ComicsViewModel : BaseViewModel() {
    private val _comics: MutableLiveData<State<List<ComicsResponse>>> = MutableLiveData()
    val comics: MutableLiveData<State<List<ComicsResponse>>> get() = _comics

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    init {
        getAllComics()
    }

    fun getAllComics() {
        //Upstream/Downstream
//        _comics.postValue(State.Loading)
        try {
            repository.getComics()
                .doOnError(::onGetComicsError)
                .doOnNext(::onGetComicsNextState)
                .subscribeBy(
                    onError = ::onGetComicsError,
                    onNext = ::onGetComicsNextState,
                )
                .addTo(compositeDisposable)
        } catch (e: Exception) {
            onGetComicsError(e)
        }
        //DRY
    }

    private fun onGetComicsError(error: Throwable) {
        Log.d("SADEQMHANA", "onGetComicsError: ${error.message}")
        _comics.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetComicsNextState(state: State<List<ComicsResponse>?>) {
        when (state) {
            is State.Success -> _comics.postValue(State.Success(state.data!!))
            is State.Failed -> _comics.postValue(State.Failed(state.error))
            is State.Loading -> _comics.postValue(State.Loading)
        }
    }

}