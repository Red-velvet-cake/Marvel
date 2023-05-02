package com.red_velvet.marvel.ui.serieDetails

import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.BaseResponse
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.MarvelRepository
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class SerieDetailsViewModel : BaseViewModel() {

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private val _serie:MutableLiveData<State<List<SeriesResponse>>> = MutableLiveData()
    val serie:MutableLiveData<State<List<SeriesResponse>>> get() = _serie

    private val _creators:MutableLiveData<State<List<CreatorsResponse>>> = MutableLiveData()
    val creators:MutableLiveData<State<List<CreatorsResponse>>> get() = _creators

    private fun getSerie(id:Int){
        _serie.postValue(State.Loading)
        val disposable = repository.getSeriesDetails(id)
            .doOnError {
                onGetSerieError(it)
            }
            .doOnSuccess{
                onGetSerieSuccess(it)
            }
            .subscribeBy(
                onError = ::onGetSerieError, onSuccess = ::onGetSerieSuccess
            )
        disposable.addTo(compositeDisposable)
    }

    private fun getCreators(id:Int){
        _creators.postValue(State.Loading)
        val disposable = repository.getSerieCreatorsBySeriesId(id)
            .doOnError {
                onGetCreatorsError(it)
            }
            .doOnSuccess {
                onGetCreatorsSuccess(it)
            }
            .subscribeBy (
                onError = ::onGetCreatorsError, onSuccess = ::onGetCreatorsSuccess
                    )
        disposable.addTo(compositeDisposable)
    }

    private fun onGetSerieError(error: Throwable) {
        _serie.postValue(State.Failed(error.message.toString()))
    }
    private fun onGetSerieSuccess(response: BaseResponse<SeriesResponse>) {
        _serie.postValue(State.Success(response.data?.results!!))
    }
    private fun onGetCreatorsError(error: Throwable) {
        _creators.postValue(State.Failed(error.message.toString()))
    }
    private fun onGetCreatorsSuccess(response: BaseResponse<CreatorsResponse>) {
        _creators.postValue(State.Success(response.data?.results!!))
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}