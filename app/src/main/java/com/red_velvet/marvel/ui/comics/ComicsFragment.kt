package com.red_velvet.marvel.ui.comics

import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentComicsBinding
import com.red_velvet.marvel.ui.MainViewModel
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.utils.Navigation

class ComicsFragment : BaseFragment<FragmentComicsBinding>() {

    override val layoutIdFragment = R.layout.fragment_comics

    override val viewModel: ComicsViewModel by viewModels()

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun setUp() {
        val comicsAdapter = ComicsScreenAdapter(mutableListOf(), viewModel)
        binding.recyclerComics.adapter = comicsAdapter

        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()) {
            viewModel.navigateBack()
        }
        viewModel.navigation.observe(this) {
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

