package com.red_velvet.marvel.ui.character

import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.databinding.FragmentCharacterBinding

class CharacterFragment : BaseFragment<FragmentCharacterBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_character

    override val viewModel: CharacterViewModel by viewModels()

    override fun setUp() {
        viewModel.getCharacterDetails(1017100)
        viewModel.getComicsDyCharacterId(1017100)
        viewModel.getSeriesDyCharacterId(1017100)
        val comicsAdapter = ComicsByCharacterAdapter(mutableListOf(), viewModel)
        binding.comicsRecyclerView.adapter = comicsAdapter

        val serieAdapter = CharacterSeriesAdapter(mutableListOf(), viewModel)
        binding.seriesRecyclerView.adapter = serieAdapter
    }


}