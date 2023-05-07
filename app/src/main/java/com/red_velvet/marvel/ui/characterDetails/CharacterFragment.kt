package com.red_velvet.marvel.ui.characterDetails

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentCharacterBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class CharacterFragment : BaseFragment<FragmentCharacterBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_character

    override val viewModel: CharacterViewModel by viewModels()

    private val args: CharacterFragmentArgs by navArgs()

    override fun setUp() {
        val characterId = args.characterId
        viewModel.getCharacterDetails(characterId)
        viewModel.getComicsDyCharacterId(characterId)
        viewModel.getSeriesDyCharacterId(characterId)
        val comicsAdapter = ComicsByCharacterAdapter(mutableListOf(), viewModel)
        binding.comicsRecyclerView.adapter = comicsAdapter

        val serieAdapter = CharacterSeriesAdapter(mutableListOf(), viewModel)
        binding.seriesRecyclerView.adapter = serieAdapter
    }


}