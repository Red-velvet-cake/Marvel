package com.red_velvet.marvel.ui.home

import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.BaseResponse
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
        getComics()
    }

    private fun getComics() {
        _comics.postValue(State.Loading)
        try {
            repository.getComics()
                .doOnError(::onGetComicsError)
                .doOnSuccess(::onGetComicsSuccess)
                .subscribeBy(
                    onError = ::onGetComicsError,
                    onSuccess = ::onGetComicsSuccess
                )
                .addTo(compositeDisposable)
        } catch (e: Exception) {
            onGetComicsError(e)
        }
    }

    private fun onGetComicsError(error: Throwable) {
        _comics.postValue(State.Failed(error.message.toString()))
    }

    private fun onGetComicsSuccess(response: BaseResponse<ComicsResponse>) {
        _comics.postValue(State.Success(response.data?.results!!))
    }

}