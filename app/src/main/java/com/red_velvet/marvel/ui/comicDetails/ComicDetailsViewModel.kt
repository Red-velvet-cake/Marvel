package com.red_velvet.marvel.ui.comicDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.model.*
import com.red_velvet.marvel.data.remote.RetrofitClient
import com.red_velvet.marvel.data.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.data.util.State
import com.red_velvet.marvel.ui.MarvelRepository
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.base.LiveDataObservablePair
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy

class ComicDetailsViewModel : BaseViewModel() {
    private val comicId = 1308

    private val _comicsDetails: MutableLiveData<State<List<ComicsResponse>>> = MutableLiveData()
    val comicsDetails: LiveData<State<List<ComicsResponse>>> get() = _comicsDetails

    private val _creators: MutableLiveData<State<List<CreatorsResponse>>> = MutableLiveData()
    val creators: LiveData<State<List<CreatorsResponse>>> get() = _creators

    private val _characters: MutableLiveData<State<List<CharactersResponse>>> = MutableLiveData()
    val characters: LiveData<State<List<CharactersResponse>>> get() = _characters

    private val repository: MarvelRepository = MarvelRepositoryImpl(RetrofitClient.apiService)

    init {
        fetchData(comicId)
    }

    private fun fetchData(comicId: Int) {
        return getDataForMultipleRequests(
            LiveDataObservablePair(_comicsDetails, repository.getComicDetail(comicId)),
            LiveDataObservablePair(_creators, repository.getComicCreatorByComicId(comicId)),
            LiveDataObservablePair(_characters, repository.getCharsByComicId(comicId))
        )
    }

}