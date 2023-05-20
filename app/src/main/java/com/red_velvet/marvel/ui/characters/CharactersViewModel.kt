package com.red_velvet.marvel.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.red_velvet.marvel.domain.models.Character
import com.red_velvet.marvel.domain.repository.MarvelRepositoryImpl
import com.red_velvet.marvel.ui.base.BaseViewModel
import com.red_velvet.marvel.ui.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: MarvelRepositoryImpl) :
    BaseViewModel(), CharacterDetailsInteractionListener {
    private val _characters: MutableLiveData<List<Character>> = MutableLiveData()
    val characters: LiveData<List<Character>> = _characters

    val searchQuery = MutableLiveData<String>()

    private val _navigationToCharacterDetails: MutableLiveData<SingleEvent<Int>> = MutableLiveData()
    val navigationToCharacterDetails: LiveData<SingleEvent<Int>> = _navigationToCharacterDetails

    init {
        getAllCharacters()
        initSearchObservable()
    }

    private fun initSearchObservable() {
        Observable.create { emitter ->
            searchQuery.observeForever { query ->
                emitter.onNext(query)
            }
        }.debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { query ->
                getAllCharacters(query)
            }.addTo(compositeDisposable)
    }

    private fun getAllCharacters(titleStartsWith: String = "") {
        repository.getAllCharacters(titleStartsWith)
            .subscribe(
                { _characters.postValue(it) },
                {}
            )
            .addTo(compositeDisposable)
    }

    override fun doOnCharacterClicked(characterId: Int) {
        _navigationToCharacterDetails.postValue(SingleEvent(characterId))
    }
}