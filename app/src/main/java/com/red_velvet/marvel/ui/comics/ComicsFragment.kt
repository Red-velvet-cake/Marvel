package com.red_velvet.marvel.ui.comics

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentComicsBinding
import com.red_velvet.marvel.ui.MainViewModel
import com.red_velvet.marvel.ui.base.BaseFragment

class ComicsFragment : BaseFragment<FragmentComicsBinding>() {

    override val layoutIdFragment = R.layout.fragment_comics

    override val viewModel: ComicsViewModel by viewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun setUp() {
        val comicsAdapter = ComicsScreenAdapter(mutableListOf(), viewModel)
        binding.recyclerComics.adapter = comicsAdapter

        mainViewModel.navigateTo(ComicsFragmentDirections.actionComicsFragmentToComicDetailsFragment())

    }



}

