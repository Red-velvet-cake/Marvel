package com.red_velvet.marvel.ui.serieDetails

import androidx.fragment.app.viewModels
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentSeriesDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import com.red_velvet.marvel.ui.serieDetails.adapter.CreatorsAdapter

class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding>() {

    override val layoutIdFragment = R.layout.fragment_series_details

    override val viewModel: SeriesDetailsViewModel by viewModels()
    override fun setUp() {
        viewModel.getSeries(632)
        viewModel.getCreators(632)
        val creatorsAdapter = CreatorsAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSerieCreators.adapter = creatorsAdapter
    }
}