package com.red_velvet.marvel.ui.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.data.remote.dtos.CharacterDto
import com.red_velvet.marvel.data.remote.dtos.ComicDto
import com.red_velvet.marvel.data.remote.dtos.SeriesDto
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import com.red_velvet.marvel.ui.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    BaseViewModel(), SeriesInteractionListener,
    ComicsInteractionListener {


    private val _characterDtoDetails: MutableLiveData<State<List<CharacterDto>>> = MutableLiveData()
    val characterDtoDetails: LiveData<State<List<CharacterDto>>> = _characterDtoDetails

    private val _comics: MutableLiveData<State<List<ComicDto>>> = MutableLiveData()
    val comics: LiveData<State<List<ComicDto>>> = _comics

    private val _seriesDto: MutableLiveData<State<List<SeriesDto>>> = MutableLiveData()
    val seriesDto: LiveData<State<List<SeriesDto>>> = _seriesDto

    private val _navigationToComicDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToComicDetails: LiveData<SingleEvent<Int>> = _navigationToComicDetails

    private val _navigationToSeriesDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToSeriesDetails: LiveData<SingleEvent<Int>> = _navigationToSeriesDetails

    fun loadCharacterDetails(characterId: Int) {
        getCharacterById(characterId)
        getComicsDyCharacterId(characterId)
        getSeriesByCharacterId(characterId)
    }

    private fun getCharacterById(characterId: Int) {
        bindStateUpdates(
            repository.getCharacterById(characterId),
            onNext = ::onGetCharacterState,
            onError = ::onGetCharacterStateError
        )
    }

    private fun onGetCharacterState(state: State<List<CharacterDto>>) {
        _characterDtoDetails.postValue(state)
    }

    private fun onGetCharacterStateError(error: Throwable) {
        _characterDtoDetails.postValue(State.Failed(error.message.toString()))
    }

    private fun getComicsDyCharacterId(characterId: Int) {
        bindStateUpdates(
            repository.getComicsByCharacterId(characterId),
            onNext = ::onGetComicsByCharacterIdState,
            onError = ::onGetComicsByCharacterIdStateError
        )
    }

    private fun onGetComicsByCharacterIdState(state: State<List<ComicDto>>) {
        _comics.postValue(state)
    }

    private fun onGetComicsByCharacterIdStateError(error: Throwable) {
        _characterDtoDetails.postValue(State.Failed(error.message.toString()))
    }

    private fun getSeriesByCharacterId(characterId: Int) {
        bindStateUpdates(
            repository.getSeriesByCharacterId(characterId),
            onNext = ::onGetSeriesByCharacterIdState,
            onError = ::onGetSeriesByCharacterIdError
        )
    }

    private fun onGetSeriesByCharacterIdState(state: State<List<SeriesDto>>) {
        _seriesDto.postValue(state)
    }

    private fun onGetSeriesByCharacterIdError(error: Throwable) {
        _seriesDto.postValue(State.Failed(error.message.toString()))
    }

    override fun doOnComicClicked(comicId: Int) {
        _navigationToComicDetails.postValue(SingleEvent(comicId))
    }

    override fun doOnSeriesClicked(seriesId: Int) {
        _navigationToSeriesDetails.postValue(SingleEvent(seriesId))
    }

}