package com.red_velvet.marvel.ui.comics

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentComicsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.utils.Navigation
import com.red_velvet.marvel.ui.utils.onBackPressed

class ComicsFragment : BaseFragment<FragmentComicsBinding>() {

    override val layoutIdFragment = R.layout.fragment_comics

    override val viewModel: ComicsViewModel by viewModels()

    override fun setUp() {
        val comicsAdapter = ComicsScreenAdapter(mutableListOf(), viewModel)
        binding.recyclerComics.adapter = comicsAdapter

        onBackPressed {
            viewModel.navigateBack()
        }

        viewModel.navigation.observe(viewLifecycleOwner) {
            when (it) {
                Navigation.Back -> {
                    findNavController().navigateUp()
                }

                is Navigation.Direction -> {
                    findNavController().navigate(it.direction)
                }
            }
        }

    }

}