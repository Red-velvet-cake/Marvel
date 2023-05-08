package com.red_velvet.marvel.ui.characters

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentCharactersBinding
import com.red_velvet.marvel.ui.base.BaseFragment

class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {
    override val layoutIdFragment = R.layout.fragment_characters
    override val viewModel: CharactersViewModel by viewModels()
    override fun setUp() {
        binding.recyclerViewChars.adapter = CharactersAdapter(emptyList(), viewModel)
        navigateToCharacterDetails()
    }

    private fun navigateToCharacterDetails() {
        viewModel.navigationToCharacterDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    CharactersFragmentDirections.actionCharactersFragmentToCharacterFragment(it)
                findNavController().navigate(directions)
            }
        }
    }
}
