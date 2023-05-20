package com.red_velvet.marvel.ui.characters

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentCharactersBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : BaseFragment<FragmentCharactersBinding, CharactersViewModel>() {

    override val layoutIdFragment = R.layout.fragment_characters
    override val viewModel: CharactersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewChars.adapter = CharactersAdapter(emptyList(), viewModel)
        binding.textViewError.setOnClickListener { viewModel.getAllCharacters() }
        initNavigateToCharacterDetails()
    }

    private fun initNavigateToCharacterDetails() {
        viewModel.navigationToCharacterDetails.observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                val directions =
                    CharactersFragmentDirections.actionCharactersFragmentToCharacterFragment(it)
                findNavController().navigate(directions)
            }
        }
    }
}
