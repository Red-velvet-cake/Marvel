package com.red_velvet.marvel.ui.serieDetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.CreatorsResponse
import com.red_velvet.marvel.data.model.SeriesResponse
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.MarvelRepository
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.serieDetails.adapter.CreatorListenerInteraction

class SerieDetailsViewModel : BaseViewModel(), CreatorListenerInteraction {

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    private val _serie: MutableLiveData<State<List<SeriesResponse>>> = MutableLiveData()
    val serie: LiveData<State<List<SeriesResponse>>> get() = _serie

    private val _creators: MutableLiveData<State<List<CreatorsResponse>>> = MutableLiveData()
    val creators: LiveData<State<List<CreatorsResponse>>> get() = _creators


    fun getSerie(id: Int) {
        getData(_serie, repository.getSeriesDetails(id))
    }

    fun getCreators(id: Int) {
        getData(_creators, repository.getSerieCreatorsBySeriesId(id))
    }

    override fun onClickCreator(creator: CreatorsResponse) {
    }
}