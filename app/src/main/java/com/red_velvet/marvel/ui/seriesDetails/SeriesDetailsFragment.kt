package com.red_velvet.marvel.ui.seriesDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.red_velvet.marvel.R
import com.red_velvet.marvel.databinding.FragmentSeriesDetailsBinding
import com.red_velvet.marvel.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesDetailsFragment : BaseFragment<FragmentSeriesDetailsBinding, SeriesDetailsViewModel>() {

    override val layoutIdFragment = R.layout.fragment_series_details

    private val args: SeriesDetailsFragmentArgs by navArgs()

    override val viewModel: SeriesDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadSeriesDetails(args.seriesId)
        val creatorsAdapter = CreatorsAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSerieCreators.adapter = creatorsAdapter
        binding.textViewError.setOnClickListener { viewModel.loadSeriesDetails(args.seriesId) }
    }
}