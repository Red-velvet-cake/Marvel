package com.red_velvet.marvel.ui.characterDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentCharacterBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class CharacterDetailsFragment :
    BaseFragment<FragmentCharacterBinding, CharacterDetailsViewModel>() {

    override val layoutIdFragment: Int = R.layout.fragment_character

    override val viewModel: CharacterDetailsViewModel by viewModels()

    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadCharacterDetails(args.characterId)

        val comicsAdapter = ComicsByCharacterAdapter(mutableListOf(), viewModel)
        val seriesAdapter = CharacterSeriesAdapter(mutableListOf(), viewModel)

        binding.apply {
            comicsRecyclerView.adapter = comicsAdapter
            seriesRecyclerView.adapter = seriesAdapter
        }

        binding.textViewError.setOnClickListener { viewModel.loadCharacterDetails(args.characterId) }

        initNavigateToComicDetails()
        initNavigateToSeriesDetails()
    }

    private fun initNavigateToComicDetails() {
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

    private fun initNavigateToSeriesDetails() {
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