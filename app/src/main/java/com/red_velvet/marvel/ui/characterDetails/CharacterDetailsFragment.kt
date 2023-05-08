package com.red_velvet.marvel.ui.characterDetails

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentCharacterBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class CharacterDetailsFragment : BaseFragment<FragmentCharacterBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_character

    override val viewModel: CharacterDetailsViewModel by viewModels()

    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val characterId = args.characterId
        viewModel.getCharacterDetails(characterId)
        viewModel.getComicsDyCharacterId(characterId)
        viewModel.getSeriesDyCharacterId(characterId)
    }

    override fun setUp() {
        val comicsAdapter = ComicsByCharacterAdapter(mutableListOf(), viewModel)
        binding.comicsRecyclerView.adapter = comicsAdapter

        val seriesAdapter = CharacterSeriesAdapter(mutableListOf(), viewModel)
        binding.seriesRecyclerView.adapter = seriesAdapter

        navigateToComicDetails()
        navigateToSeriesDetails()
    }

    private fun navigateToComicDetails() {
        viewModel.navigationToComicDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    CharacterDetailsFragmentDirections.actionCharacterFragmentToComicDetailsFragment(
                        it
                    )
                findNavController().navigate(directions)
            }

        }
    }

    private fun navigateToSeriesDetails() {
        viewModel.navigationToSeriesDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    CharacterDetailsFragmentDirections.actionCharacterFragmentToSeriesDetailsFragment2(
                        it
                    )
                findNavController().navigate(directions)
            }

        }
    }


}