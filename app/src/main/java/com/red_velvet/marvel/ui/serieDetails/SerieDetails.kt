package com.red_velvet.marvel.ui.serieDetails


import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentSerieDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.serieDetails.adapter.CreatorsAdapter


class SerieDetails : BaseFragment<FragmentSerieDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_serie_details

    override val viewModel: SerieDetailsViewModel by viewModels()
    override fun setUp() {
        viewModel.getSerie(9)
        viewModel.getCreators(9)
        val creatorsAdapter =CreatorsAdapter(mutableListOf(),viewModel)
        binding.recyclerViewSerieCreators.adapter = creatorsAdapter
    }
}