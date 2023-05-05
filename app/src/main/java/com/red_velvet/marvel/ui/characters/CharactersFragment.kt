package com.red_velvet.marvel.ui.characters

import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentCharactersBinding
import com.red_velvet.marvel.ui.base.BaseFragment


class CharactersFragment : BaseFragment<FragmentCharactersBinding>() {
    override val layoutIdFragment = R.layout.fragment_characters
    override val viewModel: CharactersViewModel by viewModels()
    override fun setUp() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.getCharacters()
        binding.recyclerViewChars.adapter = CharactersAdapter(mutableListOf(), viewModel)
    }
}